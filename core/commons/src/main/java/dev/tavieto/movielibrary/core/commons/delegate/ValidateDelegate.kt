package dev.tavieto.movielibrary.core.commons.delegate

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.exception.ListException
import kotlinx.coroutines.flow.Flow

suspend fun <T> runCatchExceptions(
    conditions: List<Pair<Boolean, Throwable>>,
    result: suspend () -> Flow<Either<T>>
): Flow<Either<T>> {
    val exceptions = Validator.getExceptions(conditions)

    return when {
        exceptions.listException.isNotEmpty() -> throw exceptions
        else -> result.invoke()
    }
}

private object Validator {
    fun getExceptions(
        conditions: List<Pair<Boolean, Throwable>>
    ): ListException {

        val errorList = ListException()
        conditions.forEach {
            if (it.first) errorList.listException.add(it.second)
        }
        return errorList
    }
}
