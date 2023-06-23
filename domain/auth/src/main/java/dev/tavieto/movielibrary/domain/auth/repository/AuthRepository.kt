package dev.tavieto.movielibrary.domain.auth.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(email: String, password: String): Flow<Either<Unit>>
    fun signUp(name: String, email: String, password: String): Flow<Either<Unit>>
    fun signOut(): Flow<Either<Unit>>
}
