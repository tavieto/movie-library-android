package dev.tavieto.movielibrary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.tavieto.movielibrary.data.local.dao.MovieDao
import dev.tavieto.movielibrary.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
