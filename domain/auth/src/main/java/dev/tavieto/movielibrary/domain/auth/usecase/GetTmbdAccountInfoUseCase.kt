package dev.tavieto.movielibrary.domain.auth.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetTmbdAccountInfoUseCase(
    scope: CoroutineScope,
    private val repository: AuthRepository
) : UseCase<String, Unit>(scope) {
    override suspend fun run(params: String?): Flow<Either<Unit>> {
        return if (params == null) throw MissingParamsException()
        else repository.getTmbdAccountInfo(params)
    }
}