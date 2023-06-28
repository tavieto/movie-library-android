package dev.tavieto.movielibrary.repository.datasource.remote

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MoviesData>>
}
