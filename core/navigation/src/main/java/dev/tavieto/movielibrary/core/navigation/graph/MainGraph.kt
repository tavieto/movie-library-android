package dev.tavieto.movielibrary.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.core.navigation.routes.MainRoutes
import dev.tavieto.movielibrary.feature.main.model.MovieModel
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
        val movie = it.arguments?.getParcelable<MovieModel>(MainRoutes.MOVIE_ARG)
            ?: throw MissingParamsException("DetailsScreen require movie arg")
        DetailsScreen(
            movie = movie,
            viewModel = getViewModel()
        )
    }
}