package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmptyCpfException(
    override val message: String? = null
) : CodeThrowable()
