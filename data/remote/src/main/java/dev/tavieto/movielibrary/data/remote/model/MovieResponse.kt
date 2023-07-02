package dev.tavieto.movielibrary.data.remote.model

import dev.tavieto.movielibrary.repository.model.MovieData

data class MovieResponse(
    val id: Int,
    val adult: Boolean,
    val genreIds: List<Int>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavorite: Boolean
) {
    fun mapToRepository(): MovieData {
        return MovieData(
            adult = this.adult,
            genreIds = this.genreIds,
            id = this.id,
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title,
            voteAverage = this.voteAverage,
            isFavorite = isFavorite
        )
    }
}