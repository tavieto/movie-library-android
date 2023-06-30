package dev.tavieto.movielibrary.domain.movie.model

data class MovieDomain(
    val id: Int,
    val adult: Boolean,
    val genreIds: List<Int>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)