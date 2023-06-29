package dev.tavieto.movielibrary.data.server.model


import com.google.gson.annotations.SerializedName
import dev.tavieto.movielibrary.data.remote.model.MovieResponse

data class MovieServerResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
) {
    fun mapToRemote(): MovieResponse {
        return MovieResponse(
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