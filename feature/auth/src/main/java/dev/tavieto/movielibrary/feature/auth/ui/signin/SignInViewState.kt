package dev.tavieto.movielibrary.feature.auth.ui.signin



data class SignInViewState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
