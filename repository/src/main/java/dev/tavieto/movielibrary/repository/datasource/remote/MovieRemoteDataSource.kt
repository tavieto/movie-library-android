package dev.tavieto.movielibrary.repository.datasource.remote

import dev.tavieto.movielibrary.core.commons.base.Either

import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getMovieList(
        movieListType: MovieListType,
        sessionId: String,
        page: Int
    ): Flow<Either<MoviesData>>

    suspend fun getFavoriteMovieList(
        sessionId: String,
        page: Int
    ): Flow<Either<MoviesData>>

    suspend fun updateFavoriteMovie(
        isFavorite: Boolean,
        sessionId: String,
        movieId: Int
    ): Flow<Either<Unit>>
}
