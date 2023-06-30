package dev.tavieto.movielibrary.core.commons.exception

class UnknownUserException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(message)
