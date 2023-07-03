package dev.tavieto.movielibrary.core.commons.exception

class MismatchPasswordException(
    override val message: String? = null
) : Throwable(message)