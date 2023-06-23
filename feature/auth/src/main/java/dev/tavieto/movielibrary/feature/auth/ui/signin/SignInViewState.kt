package dev.tavieto.movielibrary.feature.auth.ui.signin

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

data class SignInViewState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: CodeThrowable? = null
)
