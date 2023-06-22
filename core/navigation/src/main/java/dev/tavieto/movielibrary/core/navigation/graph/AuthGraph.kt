package dev.tavieto.movielibrary.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.tavieto.movielibrary.core.navigation.routes.AuthRoutes
import dev.tavieto.movielibrary.feature.auth.ui.signin.SignInScreen
import dev.tavieto.movielibrary.feature.auth.ui.signup.SignUpScreen

internal fun NavGraphBuilder.addAuthGraph() {
    navigation(
        route = AuthRoutes.branch.route,
        startDestination = AuthRoutes.SignIn.createRoute()
    ) {
        addSignInScreen()
        addSignUpScreen()
    }
}

private fun NavGraphBuilder.addSignInScreen() {
    composable(
        route = AuthRoutes.SignIn.createRoute()
    ) {
        SignInScreen()
    }
}

private fun NavGraphBuilder.addSignUpScreen() {
    composable(
        route = AuthRoutes.SignUp.createRoute()
    ) {
        SignUpScreen()
    }
}