package dev.tavieto.movielibrary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.tavieto.movielibrary.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(vararg movies: MovieEntity)

    @Query(value = "SELECT * FROM movies")
    fun getMovies(): List<MovieEntity>
}
