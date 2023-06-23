package dev.tavieto.movielibrary.repository.datasource.remote

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.repository.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    suspend fun signIn(email: String, password: String): Flow<Either<UserData>>
    suspend fun signUp()
}
