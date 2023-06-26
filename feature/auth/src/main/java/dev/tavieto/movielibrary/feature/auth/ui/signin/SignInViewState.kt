package dev.tavieto.movielibrary.feature.auth.ui.signin



data class SignInViewState(
    val email: String = "otavio.gabriel202020@gmail.com",
    val password: String = "123456",
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
