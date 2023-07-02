package dev.tavieto.movielibrary.data.remote.service

import dev.tavieto.movielibrary.core.commons.base.Either

import dev.tavieto.movielibrary.data.remote.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieService {
    suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int,
        sessionId: String
    ): Flow<Either<MoviesResponse>>

    suspend fun getFavoriteMovieList(
        page: Int,
        sessionId: String
    ): Flow<Either<MoviesResponse>>

    suspend fun postFavoriteMovie(
        isFavorite: Boolean,
        sessionId: String,
        movieId: Int
    ): Flow<Either<Unit>>
}
