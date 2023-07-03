package dev.tavieto.movielibrary.data.remote.service

import dev.tavieto.movielibrary.core.commons.base.Either
import kotlinx.coroutines.flow.Flow

interface TmbdService {
    suspend fun getRequestToken(): Flow<Either<String>>
    suspend fun getSessionId(requestToken: String): Flow<Either<String>>
    suspend fun getAccountId(sessionId: String): Flow<Either<Int>>
}
