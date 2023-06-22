package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class InvalidCreditCardException(
    override val message: String? = "Invalid Credit Card!",
    override val code: String? = null
) : CodeThrowable()
