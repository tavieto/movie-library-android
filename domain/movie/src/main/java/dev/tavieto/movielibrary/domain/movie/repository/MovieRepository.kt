package dev.tavieto.movielibrary.domain.movie.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MovieListDomain>>
}
