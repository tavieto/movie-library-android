package dev.tavieto.movielibrary.core.commons.exception

class MissingBodyResponseException(
    override val message: String? = null
) : Throwable(message)
