package dev.tavieto.movielibrary.core.commons.exception



class FailDependencyException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable()
