package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class InvalidCvvException(
    override val message: String? = "Invalid security code!",
    override val code: String? = null
) : CodeThrowable()
