package dev.tavieto.movielibrary.repository.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.domain.movie.model.MoviesDomain
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import dev.tavieto.movielibrary.repository.model.mapToDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

internal class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource
) : MovieRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MoviesDomain>> {
        return remote.getMovieList(movieListType, page).mapLatest { response ->
            response.mapCatching { it.mapToDomain() }
        }
    }
}