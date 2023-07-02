package dev.tavieto.movielibrary.repository.repository

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import dev.tavieto.movielibrary.repository.datasource.local.AuthLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.local.MovieLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import dev.tavieto.movielibrary.repository.model.mapToDomain
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

internal class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource,
    private val authLocal: AuthLocalDataSource
) : MovieRepository {

    override suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MovieListDomain>> {
        return channelFlow {
            val localMovies = local.getMovies(movieListType)
            val lastPage = local.getLastPage().first()
            val sessionId = authLocal.getUser().first()?.tmdbSessionId ?: ""
            if (localMovies.isEmpty()) {
                remote.getMovieList(
                    movieListType = movieListType,
                    page = lastPage + 1,
                    sessionId = sessionId
                ).collectLatest { result ->
                    trySend(
                        result.mapCatching {
                            local.saveMovies(movieListType, it)
                            local.getMovies(movieListType).mapToDomain()
                        }
                    )
                }
            } else {
                trySend(Either.Success(localMovies.mapToDomain()))
            }
            awaitClose()
        }
    }
}