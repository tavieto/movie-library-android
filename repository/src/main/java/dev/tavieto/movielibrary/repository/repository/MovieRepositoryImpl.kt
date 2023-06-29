package dev.tavieto.movielibrary.repository.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.domain.movie.model.MoviesDomain
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import dev.tavieto.movielibrary.repository.datasource.local.MovieLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import dev.tavieto.movielibrary.repository.model.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

internal class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {

    override suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MoviesDomain>> {
        return flow {
            val localMovies = local.getMovies()
            val lastPage = local.getLastPage().first()
            if (localMovies.isEmpty()) {
                emitAll(
                    remote.getMovieList(
                        movieListType = movieListType,
                        page = lastPage + 1
                    ).transform { result ->
                        result.mapCatching { it.mapToDomain() }
                    }
                )
            } else {
                emit(Either.Success(localMovies.mapToDomain()))
            }
        }
    }
}