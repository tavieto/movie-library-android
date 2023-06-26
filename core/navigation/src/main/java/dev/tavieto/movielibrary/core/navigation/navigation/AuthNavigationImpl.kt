package dev.tavieto.movielibrary.core.navigation.navigation

import dev.tavieto.movielibrary.core.navigation.manager.NavigationManager
import dev.tavieto.movielibrary.core.navigation.routes.AuthRoutes
import dev.tavieto.movielibrary.core.navigation.routes.MainRoutes
import dev.tavieto.movielibrary.feature.auth.ui.introduction.IntroductionNavigation
import dev.tavieto.movielibrary.feature.auth.ui.signin.SignInNavigation

internal class AuthNavigationImpl(
    private val navigationManager: NavigationManager
) : IntroductionNavigation,
    SignInNavigation {
    override fun navigateToSignIn() {
        navigationManager.navigate(AuthRoutes.SignIn.createRoute())
    }

    override fun navigateToSignUp() {
        navigationManager.navigate(AuthRoutes.SignUp.createRoute())
    }

    override fun navigateToHome() {
        navigationManager.navigate(MainRoutes.Home.createRoute())
    }

    override fun popBackStack() {
        navigationManager.popBackStack()
    }
}
