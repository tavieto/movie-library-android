package dev.tavieto.movielibrary.repository.model

import dev.tavieto.movielibrary.domain.movie.model.MovieDomain

data class MovieData(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavorite: Boolean
) {
    fun mapToDomain(): MovieDomain {
        return MovieDomain(
            id = this.id,
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title,
            voteAverage = this.voteAverage,
            isFavorite = this.isFavorite
        )
    }
}
