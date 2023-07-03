package dev.tavieto.movielibrary.core.navigation.navigation

import dev.tavieto.movielibrary.core.navigation.manager.NavigationManager
import dev.tavieto.movielibrary.core.navigation.routes.MainRoutes
import dev.tavieto.movielibrary.feature.main.model.MovieModel
import dev.tavieto.movielibrary.feature.main.ui.details.DetailsNavigation
import dev.tavieto.movielibrary.feature.main.ui.home.HomeNavigation

internal class MainNavigationImpl(
    private val navManager: NavigationManager
) : HomeNavigation,
    DetailsNavigation {
    override fun navigateToDetails(movieModel: MovieModel) {
        navManager.navigate(route = MainRoutes.Details.createRoute(movieModel))
    }

    override fun popBackStack() {
        navManager.popBackStack()
    }
}