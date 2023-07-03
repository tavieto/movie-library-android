package dev.tavieto.movielibrary.feature.main.ui.home


import dev.tavieto.movielibrary.feature.main.model.MovieListModel

data class HomeViewState(
    val userName: String = "",
    val tmdbRequestToken: String? = null,
    val nowPlayingMovies: MovieListModel = emptyList(),
    val movies: MovieListModel = emptyList(),
    val favoritesMovies: MovieListModel = emptyList(),
    val error: Throwable? = null
)
