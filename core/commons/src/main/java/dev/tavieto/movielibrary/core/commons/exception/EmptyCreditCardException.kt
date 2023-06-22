package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmptyCreditCardException(
    override val message: String? = null
) : CodeThrowable()
