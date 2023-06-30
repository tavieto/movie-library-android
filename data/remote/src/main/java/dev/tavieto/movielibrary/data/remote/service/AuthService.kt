package dev.tavieto.movielibrary.data.remote.service

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.data.remote.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface AuthService {
    suspend fun signIn(email: String, password: String): Flow<Either<UserResponse>>
    suspend fun signUp(name: String, email: String, password: String): Flow<Either<UserResponse>>
    suspend fun signOut(): Flow<Either<Unit>>
}
