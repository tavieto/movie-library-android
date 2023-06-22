package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class InvalidDistrictException(
    override val message: String? = null,
    override val code: String? = null
) : CodeThrowable()
