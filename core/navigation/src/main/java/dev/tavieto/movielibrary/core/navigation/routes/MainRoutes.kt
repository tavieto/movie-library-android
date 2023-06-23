package dev.tavieto.movielibrary.core.navigation.routes

import dev.tavieto.movielibrary.core.navigation.core.ParcelableNavType
import dev.tavieto.movielibrary.core.navigation.core.Routes
import dev.tavieto.movielibrary.core.navigation.destination.BranchDestination
import dev.tavieto.movielibrary.core.navigation.destination.LeafDestination
import dev.tavieto.movielibrary.core.navigation.destination.NavArg
import dev.tavieto.movielibrary.feature.main.model.MovieModel

object MainRoutes : Routes {
    override val branch: BranchDestination = BranchDestination.Main
    const val MOVIE_ARG = "movie_arg"

    object Home : LeafDestination(root = branch, route = "home")
    object Details : LeafDestination(
        root = branch,
        route = "home",
        args = listOf(
            NavArg(id = MOVIE_ARG, type = ParcelableNavType(MovieModel::class.java))
        )
    ) {
        fun createRoute(movie: MovieModel): String {
            return putArgs(MOVIE_ARG to movie)
        }
    }
}
