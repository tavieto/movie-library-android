package dev.tavieto.movielibrary.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.tavieto.movielibrary.core.navigation.routes.AuthRoutes
import dev.tavieto.movielibrary.feature.auth.ui.introduction.IntroductionScreen
import dev.tavieto.movielibrary.feature.auth.ui.signin.SignInScreen
import dev.tavieto.movielibrary.feature.auth.ui.signup.SignUpScreen
import org.koin.androidx.compose.getViewModel

internal fun NavGraphBuilder.addAuthGraph() {
    navigation(
        route = AuthRoutes.branch.route,
        startDestination = AuthRoutes.Introduction.createRoute()
    ) {
        addIntroductionScreen()
        addSignInScreen()
        addSignUpScreen()
    }
}

private fun NavGraphBuilder.addIntroductionScreen() {
    composable(
        route = AuthRoutes.Introduction.createRoute()
    ) {
        IntroductionScreen(getViewModel())
    }
}

private fun NavGraphBuilder.addSignInScreen() {
    composable(
        route = AuthRoutes.SignIn.createRoute()
    ) {
        SignInScreen(getViewModel())
    }
}

private fun NavGraphBuilder.addSignUpScreen() {
    composable(
        route = AuthRoutes.SignUp.createRoute()
    ) {
        SignUpScreen()
    }
}
