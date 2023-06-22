package dev.tavieto.movielibrary.core.commons.base

import java.net.UnknownHostException

private const val NETWORK_ERROR_CODE = "DPS-005"

open class CodeThrowable(
    override val message: String? = null,
    override val cause: Throwable? = null,
    open val code: String? = null
) : Throwable()

fun Throwable.toHavaThrowable(): CodeThrowable = CodeThrowable(
    message = this.message,
    cause = this,
    code = if (this is UnknownHostException) NETWORK_ERROR_CODE else null
)
