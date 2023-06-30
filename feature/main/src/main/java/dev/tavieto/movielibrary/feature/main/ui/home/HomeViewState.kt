package dev.tavieto.movielibrary.feature.main.ui.home

import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.feature.main.model.MovieListModel

data class HomeViewState(
    val userName: String = "Otávio",
    val isInitialized: Boolean = false,
    val movies: List<Pair<MovieListType, MovieListModel>> = emptyList()
)
