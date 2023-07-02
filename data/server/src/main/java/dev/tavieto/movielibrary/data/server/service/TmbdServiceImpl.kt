package dev.tavieto.movielibrary.data.server.service

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.runCatchSuspendData
import dev.tavieto.movielibrary.data.remote.service.TmbdService
import dev.tavieto.movielibrary.data.server.core.NetworkWrapper
import dev.tavieto.movielibrary.data.server.model.RequestTokenServerRequest
import dev.tavieto.movielibrary.data.server.retrofit.TmbdAuthRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TmbdServiceImpl(
    private val service: TmbdAuthRetrofitService
) : TmbdService {
    override suspend fun getRequestToken(): Flow<Either<String>> = flow {
        val result = runCatchSuspendData {
            NetworkWrapper {
                service.getRequestToken()
            }
        }
        if (result is Either.Success && result.data.success) {
            emit(Either.Success(result.data.requestToken))
        } else {
            emit(
                when (result) {
                    is Either.Success -> Either.Failure(Throwable("failed to get request token"))
                    is Either.Failure -> result
                }
            )
        }
    }

    override suspend fun getSessionId(requestToken: String): Flow<Either<String>> = flow {
        val result = runCatchSuspendData {
            NetworkWrapper {
                service.getSessionId(RequestTokenServerRequest(requestToken))
            }
        }
        if (result is Either.Success && result.data.success) {
            emit(Either.Success(result.data.sessionId))
        } else {
            emit(
                when (result) {
                    is Either.Success -> Either.Failure(Throwable("failed to get session id"))
                    is Either.Failure -> result
                }
            )
        }
    }
}
