package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmptyCityException(
    override val message: String? = null
) : CodeThrowable()
