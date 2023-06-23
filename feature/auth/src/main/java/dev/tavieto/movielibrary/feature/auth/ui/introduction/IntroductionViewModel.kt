package dev.tavieto.movielibrary.feature.auth.ui.introduction

import androidx.lifecycle.ViewModel

class IntroductionViewModel(
    private val navigation: IntroductionNavigation
) : ViewModel() {

    fun navigateToSignIn() {
        navigation.navigateToSignIn()
    }

    fun navigateToSignUp() {
        navigation.navigateToSignUp()
    }
}
