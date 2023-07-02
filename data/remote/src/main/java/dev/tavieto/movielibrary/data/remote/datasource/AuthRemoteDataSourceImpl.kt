package dev.tavieto.movielibrary.data.remote.datasource

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.data.remote.service.AuthService
import dev.tavieto.movielibrary.data.remote.service.TmbdService
import dev.tavieto.movielibrary.repository.datasource.remote.AuthRemoteDataSource
import dev.tavieto.movielibrary.repository.model.UserData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

internal class AuthRemoteDataSourceImpl(
    private val service: AuthService,
    private val tmdb: TmbdService
) : AuthRemoteDataSource {

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Either<UserData>> = channelFlow {
        service.signIn(email, password).collectLatest {
            trySend(
                it.mapCatching { user ->
                    user.mapToRepository()
                }
            )
        }
        awaitClose()
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Flow<Either<UserData>> = channelFlow {
        service.signUp(name, email, password).collectLatest {
            trySend(it.mapCatching { user -> user.mapToRepository() })
        }
        awaitClose()
    }

    override suspend fun signOut(): Flow<Either<Unit>> {
        return service.signOut()
    }

    override suspend fun getRequestToken(): Flow<Either<String>> {
        return tmdb.getRequestToken()
    }

    override suspend fun getSessionId(requestToken: String): Flow<Either<String>> = channelFlow {
        tmdb.getSessionId(requestToken = requestToken).collectLatest { result ->
            if (result is Either.Success) {
                service.saveSessionId(result.data).collectLatest {
                    trySend(
                        when (it) {
                            is Either.Success -> Either.Success(result.data)
                            is Either.Failure -> it
                        }
                    )
                }
            } else {
                trySend(result)
            }
        }
        awaitClose()
    }
}
