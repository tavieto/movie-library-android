package dev.tavieto.movielibrary.repository.datasource.local


import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun saveMovies(moviePage: MoviesData)
    fun updateFavoriteMovies(movieId: Int, isFavorite: Boolean)
    fun getMovies(): MoviesData
    fun getFavoriteMovies(): MoviesData
    fun getLastPage(): Flow<Int>

    suspend fun deleteAll()
}
