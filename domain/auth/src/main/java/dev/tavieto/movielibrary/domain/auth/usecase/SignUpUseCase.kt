package dev.tavieto.movielibrary.domain.auth.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.core.commons.delegate.runCatchExceptions
import dev.tavieto.movielibrary.core.commons.exception.EmptyEmailException
import dev.tavieto.movielibrary.core.commons.exception.EmptyNameException
import dev.tavieto.movielibrary.core.commons.exception.EmptyPasswordException
import dev.tavieto.movielibrary.core.commons.exception.InvalidEmailException
import dev.tavieto.movielibrary.core.commons.exception.InvalidPasswordMinCharException
import dev.tavieto.movielibrary.core.commons.exception.MismatchPasswordException
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.core.commons.extension.hasNotMinCharPassword
import dev.tavieto.movielibrary.core.commons.extension.isNotEmail
import dev.tavieto.movielibrary.domain.auth.model.SignUpDataDomain
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(
    scope: CoroutineScope,
    private val repository: AuthRepository
) : UseCase<SignUpDataDomain, Unit>(scope) {
    override suspend fun run(params: SignUpDataDomain?): Flow<Either<Unit>> {
        return if (params == null) throw MissingParamsException()
        else runCatchExceptions(
            conditions = listOf(
                params.name.isBlank() to EmptyNameException(),
                params.email.isBlank() to EmptyEmailException(),
                params.email.isNotEmail() to InvalidEmailException(),
                params.password.isBlank() to EmptyPasswordException(),
                params.password.hasNotMinCharPassword() to InvalidPasswordMinCharException(),
                (params.confirmPassword == params.password).not() to MismatchPasswordException()
            ),
            result = {
                repository.signUp(
                    name = params.name,
                    email = params.email,
                    password = params.password
                )
            }
        )
    }
}
