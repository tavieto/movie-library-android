package dev.tavieto.movielibrary.core.commons.extension


import java.net.UnknownHostException

fun <T> kotlin.Throwable.setMessage(message: String): T = Throwable(
    message = message,
    cause = this.cause
) as T

fun Throwable?.isNetworkError(): Boolean = this?.cause is UnknownHostException
