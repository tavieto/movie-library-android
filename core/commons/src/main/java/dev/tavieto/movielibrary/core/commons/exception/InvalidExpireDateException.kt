package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class InvalidExpireDateException(
    override val message: String? = "Invalid expire Date",
    override val code: String? = null
) : CodeThrowable(message)
