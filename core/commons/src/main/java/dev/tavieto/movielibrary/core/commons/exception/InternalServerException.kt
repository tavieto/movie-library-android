package dev.tavieto.movielibrary.core.commons.exception



class InternalServerException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable()
