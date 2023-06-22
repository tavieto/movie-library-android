package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class InvalidDataException(
    override val message: String? = null,
    override val cause: Throwable? = null,
    override val code: String? = null
) : CodeThrowable()
