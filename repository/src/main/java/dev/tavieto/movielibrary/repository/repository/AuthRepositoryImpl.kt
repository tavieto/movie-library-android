package dev.tavieto.movielibrary.repository.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import dev.tavieto.movielibrary.repository.datasource.local.AuthLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

internal class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource
) : AuthRepository {
    override fun signIn(
        email: String,
        password: String
    ): Flow<Either<Unit>> = flow {
        remote.signIn(email, password).collectLatest { result ->
            if (result is Either.Success) {
                local.saveUser(result.data)
            }
        }
    }

    override fun signUp(
        name: String,
        email: String,
        password: String
    ): Flow<Either<Unit>> {
        TODO("Not yet implemented")
    }

    override fun signOut(): Flow<Either<Unit>> {
        TODO("Not yet implemented")
    }
}