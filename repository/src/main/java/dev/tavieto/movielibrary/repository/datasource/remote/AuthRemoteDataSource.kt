package dev.tavieto.movielibrary.repository.datasource.remote

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.repository.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    suspend fun signIn(email: String, password: String): Flow<Either<UserData>>
    suspend fun signUp(name: String, email: String, password: String): Flow<Either<UserData>>
    suspend fun signOut(): Flow<Either<Unit>>
    suspend fun getRequestToken(): Flow<Either<String>>
    suspend fun getSessionId(requestToken: String): Flow<Either<String>>
    suspend fun getAccountId(data: String): Flow<Either<Int>>
}
