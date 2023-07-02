package dev.tavieto.movielibrary.data.remote.datasource

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.data.remote.model.mapToRepository
import dev.tavieto.movielibrary.data.remote.service.MovieService
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class MovieRemoteDataSourceImpl(
    private val service: MovieService
) : MovieRemoteDataSource {

    override suspend fun getMovieList(
        movieListType: MovieListType,
        sessionId: String,
        page: Int
    ): Flow<Either<MoviesData>> = channelFlow {
        service.getMovieList(movieListType, page, sessionId).collectLatest {
            trySend(it.mapCatching { response -> response.mapToRepository() })
        }
        awaitClose()
    }
}
