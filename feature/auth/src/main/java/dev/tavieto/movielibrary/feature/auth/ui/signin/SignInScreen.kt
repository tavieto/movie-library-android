package dev.tavieto.movielibrary.feature.auth.ui.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.tavieto.movielibrary.feature.auth.R

@ExperimentalMaterial3Api
@Composable
fun SignInScreen(viewModel: SignInViewModel) {
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = stringResource(id = R.string.description_navigation_button)
        )
        Text(
            text = stringResource(id = R.string.title)
        )
        TextField(
            value = state.email,
            onValueChange = { viewModel.setEmail(it) },
            label = {
                Text(text = stringResource(id = R.string.label_email))
            }
        )
        TextField(
            value = state.password,
            onValueChange = { viewModel.setPassword(it) },
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(text = stringResource(id = R.string.label_password))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                autoCorrect = false,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { viewModel.performSignIn() }
            )
        )
        Button(onClick = { viewModel.performSignIn() }) {
            Text(text = stringResource(id = R.string.button_sign_in))
        }
    }
}
