package dev.tavieto.movielibrary.feature.auth.ui.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import dev.tavieto.movielibrary.core.uikit.R.string
import dev.tavieto.movielibrary.core.uikit.components.EmailTextField
import dev.tavieto.movielibrary.core.uikit.components.PasswordTextField
import dev.tavieto.movielibrary.feature.auth.R

@Composable
fun SignInScreen(viewModel: SignInViewModel) {
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Text(
                text = stringResource(id = R.string.title)
            )
            EmailTextField(
                value = state.email,
                onValueChange = { email -> viewModel.setEmail(email) },
                label = stringResource(id = string.email_label)
            )
            PasswordTextField(
                value = state.password,
                onValueChange = { password -> viewModel.setPassword(password) },
                label = stringResource(id = string.password_label),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.performSignIn()
                    }
                )
            )
            Button(onClick = { viewModel.performSignIn() }) {
                Text(text = stringResource(id = R.string.button_sign_in))
            }
        }
    }

    LaunchedEffect(state.error) {
        if (state.error != null && state.error?.localizedMessage != null) {
            Toast.makeText(context, state.error?.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
