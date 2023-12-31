package dev.tavieto.movielibrary.domain.auth.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.core.commons.delegate.runCatchExceptions
import dev.tavieto.movielibrary.core.commons.exception.EmptyEmailException
import dev.tavieto.movielibrary.core.commons.exception.EmptyPasswordException
import dev.tavieto.movielibrary.core.commons.exception.InvalidEmailException
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.core.commons.extension.isNotEmail
import dev.tavieto.movielibrary.domain.auth.model.SignInCredentialsDomain
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SignInUseCase(
    scope: CoroutineScope,
    private val repository: AuthRepository
) : UseCase<SignInCredentialsDomain, Unit>(scope) {
    override suspend fun run(params: SignInCredentialsDomain?): Flow<Either<Unit>> {
        if (params == null) throw MissingParamsException()
        return runCatchExceptions(
            conditions = listOf(
                params.email.isBlank() to EmptyEmailException(),
                params.email.isNotEmail() to InvalidEmailException(),
                params.password.isBlank() to EmptyPasswordException(),
            ),
            result = {
                repository.signIn(email = params.email, password = params.password)
            }
        )
    }
}
