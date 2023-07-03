package dev.tavieto.movielibrary.feature.auth.ui.signin

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
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
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.title),
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.height(16.dp))
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
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.performSignIn() },
                    enabled = state.isLoading.not()
                ) {
                    Text(text = stringResource(id = R.string.button_sign_in))
                }
            }
            AnimatedVisibility(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
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
