package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmailDoesNotMatchException(
    override val message: String? = null
) : CodeThrowable()
