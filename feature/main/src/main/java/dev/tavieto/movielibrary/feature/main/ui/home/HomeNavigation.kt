package dev.tavieto.movielibrary.feature.main.ui.home

import dev.tavieto.movielibrary.feature.main.model.MovieModel

interface HomeNavigation {
    fun navigateToDetails(movieModel: MovieModel)
}
