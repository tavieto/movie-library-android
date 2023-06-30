package dev.tavieto.movielibrary.feature.auth.ui.signup

import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.commons.exception.DuplicatedEmailException
import dev.tavieto.movielibrary.core.commons.exception.EmptyEmailException
import dev.tavieto.movielibrary.core.commons.exception.EmptyNameException
import dev.tavieto.movielibrary.core.commons.exception.EmptyPasswordException
import dev.tavieto.movielibrary.core.commons.exception.InvalidEmailException
import dev.tavieto.movielibrary.core.commons.exception.InvalidPasswordMinCharException
import dev.tavieto.movielibrary.core.commons.exception.ListException
import dev.tavieto.movielibrary.core.commons.exception.MismatchPasswordException
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.model.SignUpDataDomain
import dev.tavieto.movielibrary.domain.auth.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class SignUpViewModel(
    private val navigation: SignUpNavigation
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(SignUpViewState())
    val state = _state.asStateFlow()

    private val signUpUseCase: SignUpUseCase by useCase()

    fun setName(value: String) {
        _state.update {
            it.copy(
                name = value,
                nameError = null
            )
        }
    }

    fun setEmail(value: String) {
        _state.update {
            it.copy(
                email = value,
                emailError = null
            )
        }
    }

    fun setPassword(value: String) {
        _state.update {
            it.copy(
                password = value,
                passwordError = null
            )
        }
    }

    fun setConfirmPassword(value: String) {
        _state.update {
            it.copy(
                confirmPassword = value,
                confirmPasswordError = null
            )
        }
    }

    fun setPasswordVisibility(value: Boolean) {
        _state.update { it.copy(isPasswordVisible = value) }
    }

    fun setConfirmPasswordVisibility(value: Boolean) {
        _state.update { it.copy(isConfirmPasswordVisible = value) }
    }

    private fun isLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }

    fun signUp() {
        isLoading(true)
        cleanErrors()
        _state.value.run {
            signUpUseCase(
                params = SignUpDataDomain(
                    name = name,
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword
                ),
                onSuccess = {
                    isLoading(false)
                },
                onFailure = { throwable ->
                    isLoading(false)

                    if (throwable is ListException) {
                        throwable.listException.forEach { errorItem ->
                            _state.update {
                                when (errorItem) {
                                    is EmptyNameException -> it.copy(nameError = errorItem)
                                    is EmptyEmailException -> it.copy(emailError = errorItem)
                                    is InvalidEmailException -> it.copy(emailError = errorItem)
                                    is EmptyPasswordException -> it.copy(passwordError = errorItem)
                                    is InvalidPasswordMinCharException -> it.copy(passwordError = errorItem)
                                    is MismatchPasswordException -> it.copy(confirmPasswordError = errorItem)
                                    else -> it.copy(error = errorItem)
                                }
                            }
                        }
                    } else {
                        _state.update {
                            when (throwable) {
                                is DuplicatedEmailException -> it.copy(emailError = throwable)
                                else -> it.copy(error = throwable)
                            }
                        }
                    }
                }
            )
        }
    }

    private fun cleanErrors() {
        _state.update {
            it.copy(
                nameError = null,
                emailError = null,
                passwordError = null,
                confirmPasswordError = null,
                error = null,
            )
        }
    }

    fun navigateBack() {
        navigation.popBackStack()
    }
}
