package dev.tavieto.movielibrary.repository.model

import dev.tavieto.movielibrary.domain.movie.model.MovieDomain

data class MovieData(
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavorite: Boolean
) {
    fun mapToDomain(): MovieDomain {
        return MovieDomain(
            adult = this.adult,
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

internal fun MovieDomain.mapToRepository(): MovieData {
    return MovieData(
        id = this.id,
        adult = this.adult,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        isFavorite = this.isFavorite
    )
}