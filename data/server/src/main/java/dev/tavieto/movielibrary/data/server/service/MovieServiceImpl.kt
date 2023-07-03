package dev.tavieto.movielibrary.data.server.service

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.runCatchSuspendData
import dev.tavieto.movielibrary.data.remote.model.MoviesResponse
import dev.tavieto.movielibrary.data.remote.service.MovieService
import dev.tavieto.movielibrary.data.server.BuildConfig
import dev.tavieto.movielibrary.data.server.core.NetworkWrapper
import dev.tavieto.movielibrary.data.server.model.FavoriteMediaServerRequest
import dev.tavieto.movielibrary.data.server.model.mapToRemote
import dev.tavieto.movielibrary.data.server.retrofit.MovieRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MovieServiceImpl(
    private val service: MovieRetrofitService
) : MovieService {
    override suspend fun getMovieList(
        page: Int,
        sessionId: String,
        onlyNowPlaying: Boolean
    ): Flow<Either<MoviesResponse>> = flow {
        emit(
            runCatchSuspendData {
                NetworkWrapper {
                    service.getMovieList(
                        movieListType = if (onlyNowPlaying) "popular" else "now_playing",
                        page = page,
                        language = "pt-BR"
                    )
                }.results.mapToRemote()
            }
        )
    }

    override suspend fun getFavoriteMovieList(
        page: Int,
        sessionId: String
    ): Flow<Either<MoviesResponse>> = flow {
        emit(
            runCatchSuspendData {
                NetworkWrapper {
                    service.getFavoriteMovies(
                        accountId = BuildConfig.TMDB_ACCOUNT_ID,
                        page = page,
                        language = "pt-BR",
                        sortedBy = "created_at.asc",
                        sessionId = sessionId
                    )
                }.results.mapToRemote(isFavorite = true)
            }
        )
    }

    override suspend fun postFavoriteMovie(
        isFavorite: Boolean,
        sessionId: String,
        movieId: Int
    ): Flow<Either<Unit>> = flow {
        val result = runCatchSuspendData {
            NetworkWrapper {
                service.postFavoriteMovie(
                    accountId = BuildConfig.TMDB_ACCOUNT_ID,
                    sessionId = sessionId,
                    favorite = FavoriteMediaServerRequest(
                        favorite = isFavorite,
                        mediaId = movieId,
                        mediaType = "movie"
                    )
                )
            }
        }
        emit(
            when (result) {
                is Either.Failure -> result
                is Either.Success -> {
                    if (result.data.success) {
                        Either.Success(Unit)
                    } else {
                        Either.Failure(Throwable("the request do not succeed"))
                    }
                }
            }
        )
    }
}
