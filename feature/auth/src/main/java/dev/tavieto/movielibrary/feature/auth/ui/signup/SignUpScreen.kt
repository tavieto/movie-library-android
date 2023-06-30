package dev.tavieto.movielibrary.feature.auth.ui.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.tavieto.movielibrary.core.commons.exception.DuplicatedEmailException
import dev.tavieto.movielibrary.core.commons.exception.EmptyEmailException
import dev.tavieto.movielibrary.core.commons.exception.EmptyNameException
import dev.tavieto.movielibrary.core.commons.exception.EmptyPasswordException
import dev.tavieto.movielibrary.core.commons.exception.InvalidEmailException
import dev.tavieto.movielibrary.core.commons.exception.InvalidPasswordMinCharException
import dev.tavieto.movielibrary.core.commons.exception.MismatchPasswordException
import dev.tavieto.movielibrary.core.commons.extension.isNotNull
import dev.tavieto.movielibrary.core.uikit.R.string
import dev.tavieto.movielibrary.core.uikit.components.EmailTextField
import dev.tavieto.movielibrary.core.uikit.components.NameTextField
import dev.tavieto.movielibrary.core.uikit.components.PasswordTextField
import dev.tavieto.movielibrary.feature.auth.R

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            IconButton(onClick = { viewModel.navigateBack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.description_navigation_button)
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.signup_screen_title),
                    style = MaterialTheme.typography.h5
                )
                NameTextField(
                    label = stringResource(id = string.name_label),
                    value = state.name,
                    onValueChange = { viewModel.setName(it) },
                    isError = state.nameError.isNotNull(),
                    errorMessage = state.nameError.getMessage()
                )
                EmailTextField(
                    label = stringResource(id = string.email_label),
                    value = state.email,
                    onValueChange = { viewModel.setEmail(it) },
                    isError = state.emailError.isNotNull(),
                    errorMessage = state.emailError.getMessage()
                )
                PasswordTextField(
                    label = stringResource(id = string.password_label),
                    value = state.password,
                    onValueChange = { viewModel.setPassword(it) },
                    isError = state.passwordError.isNotNull(),
                    errorMessage = state.passwordError?.getMessage(),
                    isPasswordVisible = state.isPasswordVisible,
                    onPasswordVisibilityChange = {
                        viewModel.setPasswordVisibility(state.isPasswordVisible.not())
                    }
                )
                PasswordTextField(
                    label = stringResource(id = string.confirm_password_label),
                    value = state.confirmPassword,
                    onValueChange = { viewModel.setConfirmPassword(it) },
                    isError = state.confirmPasswordError.isNotNull(),
                    errorMessage = state.confirmPasswordError.getMessage(),
                    isPasswordVisible = state.isConfirmPasswordVisible,
                    onPasswordVisibilityChange = {
                        viewModel.setConfirmPasswordVisibility(state.isConfirmPasswordVisible.not())
                    }
                )
                Button(onClick = { viewModel.signUp() }) {
                    Text(
                        text = stringResource(id = R.string.signup_screen_sing_button),
                        style = MaterialTheme.typography.button
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentSize(),
                visible = state.isLoading
            ) {
                CircularProgressIndicator()
            }
        }
    }

    LaunchedEffect(state.error) {
        if (state.error != null && state.error?.localizedMessage != null) {
            Toast.makeText(context, state.error?.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
private fun Throwable?.getMessage(): String {
    return stringResource(
        id = when (this) {
            is EmptyNameException -> R.string.signup_screen_error_empty_name
            is EmptyEmailException -> R.string.signup_screen_error_empty_email
            is InvalidEmailException -> R.string.signup_screen_error_invalid_email
            is EmptyPasswordException -> R.string.signup_screen_error_empty_password
            is InvalidPasswordMinCharException -> R.string.signup_screen_error_invalid_password_min_char
            is MismatchPasswordException -> R.string.signup_screen_error_invalid_password_mismatch
            is DuplicatedEmailException -> R.string.signup_screen_error_duplicated_email
            else -> R.string.signup_screen_error_unknown
        }
    )
}
