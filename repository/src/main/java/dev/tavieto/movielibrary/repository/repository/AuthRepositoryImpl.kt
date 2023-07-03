package dev.tavieto.movielibrary.repository.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.exception.EmptyNameException
import dev.tavieto.movielibrary.core.commons.exception.UserAlreadyHasSessionIdException
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import dev.tavieto.movielibrary.repository.datasource.local.AuthLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.local.MovieLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.local.SessionLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.AuthRemoteDataSource
import dev.tavieto.movielibrary.repository.model.SessionData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

internal class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource,
    private val session: SessionLocalDataSource,
    private val localMovies: MovieLocalDataSource
) : AuthRepository {

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Either<Unit>> = channelFlow {
        remote.signIn(email, password).collectLatest { result ->
            if (result is Either.Success) {
                local.saveUser(result.data)
                session.saveSession(SessionData(result.data.id))
            }
            trySend(result.mapCatching { })
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Flow<Either<Unit>> = channelFlow {
        remote.signUp(name, email, password).collectLatest { result ->
            if (result is Either.Success) {
                local.saveUser(result.data)
                session.saveSession(SessionData(result.data.id))
            }
            trySend(result.mapCatching { })
        }
    }

    override suspend fun signOut(): Flow<Either<Unit>> {
        return remote.signOut().also {
            local.deleteUser()
            session.deleteSession()
            localMovies.deleteAll()
        }
    }

    override suspend fun getRequestToken(): Flow<Either<String>> = channelFlow {
        local.getUser().collectLatest {
            if (it?.tmdbSessionId.isNullOrBlank()) {
                remote.getRequestToken().collectLatest { requestToken ->
                    trySend(requestToken)
                }
            } else {
                trySend(Either.Failure(UserAlreadyHasSessionIdException()))
            }
        }
        awaitClose()
    }

    override suspend fun getTmbdAccountInfo(requestToken: String): Flow<Either<Unit>> =
        channelFlow {
            remote.getSessionId(requestToken = requestToken).collectLatest { sessionIdResult ->
                when (sessionIdResult) {
                    is Either.Success -> {
                        local.saveSessionId(sessionIdResult.data)
                        remote.getAccountId(sessionIdResult.data).collectLatest { accountIdResult ->
                            when (accountIdResult) {
                                is Either.Failure -> trySend(accountIdResult)
                                is Either.Success -> {
                                    local.saveAccountId(accountIdResult.data)
                                    trySend(Either.Success(Unit))
                                }
                            }
                        }
                    }

                is Either.Failure -> {
                    trySend(sessionIdResult)
                }
            }
        }
        awaitClose()
    }

    override suspend fun getUserName(): Flow<Either<String>> = channelFlow {
        local.getUser().collectLatest {
            if (it != null) {
                trySend(Either.Success(it.name))
            } else {
                trySend(Either.Failure(EmptyNameException()))
            }
        }
        awaitClose()
    }
}
