package dev.tavieto.movielibrary.feature.auth.ui.introduction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun IntroductionScreen(viewModel: IntroductionViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { viewModel.navigateToSignIn() }) {
            Text(text = "Navigate to sign-in")
        }
        Button(onClick = { viewModel.navigateToSignUp() }) {
            Text(text = "Navigate to sign-up")
        }
    }
}