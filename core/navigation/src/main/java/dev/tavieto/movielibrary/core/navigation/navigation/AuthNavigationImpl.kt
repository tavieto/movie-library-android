package dev.tavieto.movielibrary.core.navigation.navigation

import dev.tavieto.movielibrary.core.navigation.core.NavigationManager
import dev.tavieto.movielibrary.core.navigation.routes.AuthRoutes
import dev.tavieto.movielibrary.feature.auth.ui.introduction.IntroductionNavigation

internal class AuthNavigationImpl(
    private val navigationManager: NavigationManager
) : IntroductionNavigation {
    override fun navigateToSignIn() {
        navigationManager.navigate(AuthRoutes.SignIn.createRoute())
    }

    override fun navigateToSignUp() {
        navigationManager.navigate(AuthRoutes.SignUp.createRoute())
    }
}
