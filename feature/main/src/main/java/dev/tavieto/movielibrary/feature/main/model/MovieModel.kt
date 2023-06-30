package dev.tavieto.movielibrary.feature.main.model

import android.os.Parcelable
import dev.tavieto.movielibrary.domain.movie.model.MovieDomain
import dev.tavieto.movielibrary.feature.main.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val adult: Boolean,
    val genreIds: List<Int>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) : Parcelable

internal fun MovieDomain.mapFromDomain(): MovieModel {
    return MovieModel(
        id = this.id,
        adult = this.adult,
        genreIds = this.genreIds,
        overview = this.overview,
        posterPath = BuildConfig.TMDB_API_BASE_URL_IMAGES + this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}
