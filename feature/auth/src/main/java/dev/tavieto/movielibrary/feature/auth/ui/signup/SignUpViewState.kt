package dev.tavieto.movielibrary.feature.auth.ui.signup

data class SignUpViewState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val confirmPassword: String = "",
    val isConfirmPasswordVisible: Boolean = false,
    val nameError: Throwable? = null,
    val emailError: Throwable? = null,
    val passwordError: Throwable? = null,
    val confirmPasswordError: Throwable? = null,
    val error: Throwable? = null
)
