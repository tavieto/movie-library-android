package dev.tavieto.movielibrary.repository.datasource.local

import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun saveMovies(type: MovieListType, moviePage: MoviesData)
    fun getMovies(type: MovieListType): MoviesData
    fun getLastPage(): Flow<Int>
}
