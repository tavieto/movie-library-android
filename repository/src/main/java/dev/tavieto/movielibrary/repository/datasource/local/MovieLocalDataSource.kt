package dev.tavieto.movielibrary.repository.datasource.local


import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun saveMovies(moviePage: MoviesData)
    fun updateFavoriteMovies(movieId: Int, isFavorite: Boolean)
    fun updateNowPlayingMovies(movieId: Int, nowPlaying: Boolean)
    fun getMovies(): MoviesData
    fun getFavoriteMovies(): MoviesData
    fun getNowPlayingMovies(): MoviesData
    fun getLastPage(): Flow<Int>

    fun resetNowPLayingMark()

    suspend fun deleteAll()
}
