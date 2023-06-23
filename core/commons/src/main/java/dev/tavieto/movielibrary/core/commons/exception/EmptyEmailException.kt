package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmptyEmailException(
    override val message: String? = null,
    override val code: String? = null
) : CodeThrowable()
