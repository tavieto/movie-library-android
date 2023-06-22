package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class InvalidCpfException(
    override val message: String? = "Invalid Cpf!",
    override val code: String? = null
) : CodeThrowable()
