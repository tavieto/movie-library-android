package dev.tavieto.movielibrary.domain.auth.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetUserNameUseCase(
    scope: CoroutineScope,
    private val repository: AuthRepository
) : UseCase<Unit, String>(scope) {
    override suspend fun run(params: Unit?): Flow<Either<String>> {
        return repository.getUserName()
    }
}