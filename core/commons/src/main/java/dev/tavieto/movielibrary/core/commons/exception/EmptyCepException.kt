package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmptyCepException(
    override val message: String? = null
) : CodeThrowable()
