package dev.tavieto.movielibrary.core.commons.exception



class PasswordDoesNotMatchException(
    override val message: String? = null
) : Throwable(message)
