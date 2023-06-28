package dev.tavieto.movielibrary.domain.movie.model

import dev.tavieto.movielibrary.core.commons.enums.MovieListType

data class MovieParams(
    val type: MovieListType,
    val page: Int
)
