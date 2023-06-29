package dev.tavieto.movielibrary.repository.datasource.local

import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun saveMovies(moviePage: MoviesData)
    fun getMovies(): MoviesData
    fun getLastPage(): Flow<Int>
}
