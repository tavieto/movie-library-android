package dev.tavieto.movielibrary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.repository.model.MovieData

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "genre_ids")
    val genreIds: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
) {
    fun mapToRepository(): MovieData {
        val gson = Gson()
        return MovieData(
            id = this.id,
            adult = this.adult,
            genreIds = gson.fromJson(this.genreIds, Array<Int>::class.java).toList(),
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title,
            voteAverage = this.voteAverage
        )
    }
}

internal fun List<MovieEntity>.mapToRepository() = this.map { it.mapToRepository() }

internal fun MovieData.mapToEntity(type: MovieListType): MovieEntity {
    val gson = Gson()
    return MovieEntity(
        id = this.id,
        type = type.id,
        adult = this.adult,
        genreIds = gson.toJson(this.genreIds),
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}
