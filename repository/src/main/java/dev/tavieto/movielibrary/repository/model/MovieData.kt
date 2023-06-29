package dev.tavieto.movielibrary.repository.model

import dev.tavieto.movielibrary.domain.movie.model.MovieDomain

data class MovieData(
    val id: Int,
    val adult: Boolean,
    val genreIds: List<Int>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) {
    fun mapToDomain(): MovieDomain {
        return MovieDomain(
            adult = this.adult,
            genreIds = this.genreIds,
            id = this.id,
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title,
            voteAverage = this.voteAverage
        )
    }
}
