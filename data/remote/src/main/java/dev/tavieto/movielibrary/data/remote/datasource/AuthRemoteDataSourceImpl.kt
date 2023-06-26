package dev.tavieto.movielibrary.data.remote.datasource

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.data.remote.service.AuthService
import dev.tavieto.movielibrary.repository.datasource.remote.AuthRemoteDataSource
import dev.tavieto.movielibrary.repository.model.UserData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

internal class AuthRemoteDataSourceImpl(
    private val service: AuthService
): AuthRemoteDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Either<UserData>> {
        return service.signIn(email, password).mapLatest {
            it.mapCatching { user ->
                UserData(
                    id = user.id,
                    name = user.name,
                    email = user.email,
                    imageURL = user.imageURL,
                    favoriteMoviesID = user.favoriteMoviesID
                )
            }
        }
    }

    override suspend fun signUp() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Flow<Either<Unit>> {
        return service.signOut()
    }

}
