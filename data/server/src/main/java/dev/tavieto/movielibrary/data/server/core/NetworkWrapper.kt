package dev.tavieto.movielibrary.data.server.core

import dev.tavieto.movielibrary.core.commons.exception.BadRequestException
import dev.tavieto.movielibrary.core.commons.exception.MissingBodyResponseException
import dev.tavieto.movielibrary.core.commons.exception.NotAuthorizedException
import dev.tavieto.movielibrary.core.commons.exception.NotFoundException
import dev.tavieto.movielibrary.core.commons.exception.TimeOutException
import dev.tavieto.movielibrary.core.commons.exception.UnknownCodeException
import retrofit2.Call
import retrofit2.Response

object NetworkWrapper {
    suspend operator fun <T> invoke(
        request: suspend () -> Call<T>
    ): T = request().execute().let { response ->
        return when {
            response.isSuccessful.not() -> throw handleException(response)
            else -> response.body() ?: throw MissingBodyResponseException()
        }
    }

    private fun handleException(response: Response<*>): Throwable {
        return when (response.code()) {
            StatusCode.BAD_REQUEST -> BadRequestException()
            StatusCode.NOT_AUTHORIZED -> NotAuthorizedException()
            StatusCode.NOT_FOUND -> NotFoundException()
            StatusCode.TIMEOUT -> TimeOutException()
            else -> UnknownCodeException()
        }
    }

    private object StatusCode {
        const val BAD_REQUEST = 400
        const val NOT_AUTHORIZED = 401
        const val NOT_FOUND = 404
        const val TIMEOUT = 408
    }
}
