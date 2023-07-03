package dev.tavieto.movielibrary.data.remote.service

import dev.tavieto.movielibrary.core.commons.base.Either

import dev.tavieto.movielibrary.data.remote.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieService {
    suspend fun getMovieList(
        page: Int,
        sessionId: String,
        onlyNowPlaying: Boolean
    ): Flow<Either<MoviesResponse>>

    suspend fun getFavoriteMovieList(
        page: Int,
        sessionId: String,
        accountId: Int
    ): Flow<Either<MoviesResponse>>

    suspend fun postFavoriteMovie(
        isFavorite: Boolean,
        sessionId: String,
        movieId: Int,
        accountId: Int
    ): Flow<Either<Unit>>
}
