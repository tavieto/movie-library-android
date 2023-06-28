package dev.tavieto.movielibrary.feature.main.ui.home

import dev.tavieto.movielibrary.domain.movie.model.MoviesDomain

data class HomeViewState(
    val movies: MoviesDomain = emptyList()
)
