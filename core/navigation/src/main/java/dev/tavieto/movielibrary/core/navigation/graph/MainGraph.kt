package dev.tavieto.movielibrary.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.tavieto.movielibrary.core.navigation.routes.MainRoutes
import dev.tavieto.movielibrary.feature.main.ui.details.DetailsScreen
import dev.tavieto.movielibrary.feature.main.ui.home.HomeScreen
import org.koin.androidx.compose.getViewModel

internal fun NavGraphBuilder.addMainGraph() {
    navigation(
        route = MainRoutes.branch.route,
        startDestination = MainRoutes.Home.createRoute()
    ) {
        addHomeScreen()
        addDetailsScreen()
    }
}

private fun NavGraphBuilder.addHomeScreen() {
    composable(
        route = MainRoutes.Home.createRoute()
    ) {
        HomeScreen(getViewModel())
    }
}

private fun NavGraphBuilder.addDetailsScreen() {
    composable(
        route = MainRoutes.Details.createRoute(),
        arguments = MainRoutes.Details.arguments
    ) {
        DetailsScreen()
    }
}