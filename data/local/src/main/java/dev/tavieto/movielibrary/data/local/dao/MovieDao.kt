package dev.tavieto.movielibrary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.tavieto.movielibrary.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(vararg movies: MovieEntity)

    @Query(value = "SELECT * FROM movies")
    fun getMovies(): List<MovieEntity>

    @Query(value = "SELECT * FROM movies WHERE is_favorite")
    fun getFavoriteMovies(): List<MovieEntity>

    @Query(value = "SELECT * FROM movies WHERE now_playing")
    fun getNowPlayingMovies(): List<MovieEntity>

    @Query(value = "UPDATE movies SET is_favorite = :isFavorite WHERE id = :movieId")
    fun updateFavoriteMovies(movieId: Int, isFavorite: Boolean)

    @Query(value = "UPDATE movies SET now_playing = :nowPlaying WHERE id = :movieId")
    fun updateNowPlayingMovies(movieId: Int, nowPlaying: Boolean)

    @Query(value = "UPDATE movies SET now_playing = :nowPlaying")
    fun updateNowPlayingMark(nowPlaying: Boolean = false)

    @Query(value = "DELETE FROM movies")
    fun deleteAll()
}
