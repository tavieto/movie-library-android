package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class EmptyBirthdateException(
    override val message: String? = null
) : CodeThrowable()
