package dev.tavieto.movielibrary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.tavieto.movielibrary.repository.model.MovieData

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
) {
    fun mapToRepository(): MovieData {
        return MovieData(
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

internal fun List<MovieEntity>.mapToRepository() = this.map { it.mapToRepository() }

internal fun MovieData.mapToEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        isFavorite = this.isFavorite
    )
}
