package dev.tavieto.movielibrary.repository.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import dev.tavieto.movielibrary.repository.datasource.local.SessionLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.AuthRemoteDataSource
import dev.tavieto.movielibrary.repository.model.SessionData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

internal class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    /*private val local: AuthLocalDataSource,*/
    private val session: SessionLocalDataSource
) : AuthRepository {

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Either<Unit>> = channelFlow {
        remote.signIn(email, password).collectLatest { result ->
            if (result is Either.Success) {
//                local.saveUser(result.data)
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
                session.saveSession(SessionData(result.data.id))
            }
            trySend(result.mapCatching { })
        }
    }

    override suspend fun signOut(): Flow<Either<Unit>> {
        return remote.signOut().also {
            session.deleteSession()
        }
    }
}