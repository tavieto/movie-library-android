package dev.tavieto.movielibrary.data.remote.model

import dev.tavieto.movielibrary.repository.model.MovieData

data class MovieResponse(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    fun mapToRepository(): MovieData {
        return MovieData(
            adult = this.adult,
            backdropPath = this.backdropPath,
            genreIds = this.genreIds,
            id = this.id,
            originalLanguage = originalLanguage,
            originalTitle = this.originalTitle,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title,
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount,
        )
    }
}