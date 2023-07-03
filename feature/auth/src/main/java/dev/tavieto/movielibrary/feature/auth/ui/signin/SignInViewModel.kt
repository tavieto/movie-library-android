package dev.tavieto.movielibrary.feature.auth.ui.signin

import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.model.SignInCredentialsDomain
import dev.tavieto.movielibrary.domain.auth.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class SignInViewModel(
    private val navigation: SignInNavigation
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(SignInViewState())
    val state = _state.asStateFlow()

    private val signInUseCase: SignInUseCase by useCase()

    fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun setPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun performSignIn() {
        setLoading(true)
        signInUseCase(
            params = SignInCredentialsDomain(
                email = _state.value.email,
                password = _state.value.password
            ),
            onSuccess = {
                navigation.navigateToHome()
                setLoading(false)
            },
            onFailure = {
                setError(it)
                setLoading(false)
            }
        )
    }

    fun navigateBack() {
        navigation.popBackStack()
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    private fun setError(error: Throwable?) {
        _state.update { it.copy(error = error) }
    }
}
