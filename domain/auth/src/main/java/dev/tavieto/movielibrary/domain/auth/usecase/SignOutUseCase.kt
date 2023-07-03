package dev.tavieto.movielibrary.domain.auth.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SignOutUseCase(
    scope: CoroutineScope,
    private val repository: AuthRepository
) : UseCase<Unit, Unit>(scope) {
    override suspend fun run(params: Unit?): Flow<Either<Unit>> {
        return repository.signOut()
    }
}
