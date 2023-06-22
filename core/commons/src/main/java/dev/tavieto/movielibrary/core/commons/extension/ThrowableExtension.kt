package dev.tavieto.movielibrary.core.commons.extension

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable
import java.net.UnknownHostException

fun <T> Throwable.setMessage(message: String): T = Throwable(
    message = message,
    cause = this.cause
) as T

fun CodeThrowable?.isNetworkError(): Boolean = this?.cause is UnknownHostException
