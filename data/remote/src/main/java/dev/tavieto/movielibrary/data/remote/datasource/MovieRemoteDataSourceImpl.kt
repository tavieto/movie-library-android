package dev.tavieto.movielibrary.data.remote.datasource

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.data.remote.model.mapToRepository
import dev.tavieto.movielibrary.data.remote.service.MovieService
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class MovieRemoteDataSourceImpl(
    private val service: MovieService
) : MovieRemoteDataSource {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MoviesData>> {
        return service.getMovieList(movieListType, page).mapLatest {
            it.mapCatching { response ->
                response.mapToRepository()
            }
        }
    }
}
