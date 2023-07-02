package dev.tavieto.movielibrary.domain.movie.model

data class FavoriteMovieParams(
    val movieId: Int,
    val isFavorite: Boolean
)
