package dev.tavieto.movielibrary.core.commons.exception



class MissingParamsException(
    override val message: String? = null
) : Throwable(message)
