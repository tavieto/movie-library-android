package dev.tavieto.movielibrary.domain.movie.repository

import dev.tavieto.movielibrary.core.commons.base.Either

import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(page: Int): Flow<Either<MovieListDomain>>

    suspend fun getFavoriteMovieList(page: Int): Flow<Either<MovieListDomain>>
    suspend fun getNowPlayingMovieList(page: Int): Flow<Either<MovieListDomain>>

    suspend fun updateFavoriteMovie(
        movieId: Int,
        isFavorite: Boolean
    ): Flow<Either<Unit>>
}
