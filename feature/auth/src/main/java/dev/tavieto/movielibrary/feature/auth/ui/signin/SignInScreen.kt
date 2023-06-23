package dev.tavieto.movielibrary.feature.auth.ui.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignInScreen(viewModel: SignInViewModel) {
    Content()
}

@Composable
private fun Content() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Sign-in screen")
    }
}
