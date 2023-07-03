package dev.tavieto.movielibrary.feature.auth.ui.introduction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IntroductionScreen(viewModel: IntroductionViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { viewModel.navigateToSignIn() }) {
            Text(text = "Navigate to sign-in")
        }
        Button(onClick = { viewModel.navigateToSignUp() }) {
            Text(text = "Navigate to sign-up")
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}