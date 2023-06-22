package dev.tavieto.movielibrary.feature.auth.ui.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SignInScreen() {
    Content()
}

@Composable
private fun Content() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "é o tal do logas", modifier = Modifier.align(Alignment.Center))
    }
}
