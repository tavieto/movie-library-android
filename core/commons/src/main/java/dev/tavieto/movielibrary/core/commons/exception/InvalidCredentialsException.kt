package dev.tavieto.movielibrary.core.commons.exception



class InvalidCredentialsException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable()
