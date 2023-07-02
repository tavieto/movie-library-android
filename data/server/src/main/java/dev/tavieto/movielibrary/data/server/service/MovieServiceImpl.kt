package dev.tavieto.movielibrary.data.server.service

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.runCatchSuspendData
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.data.remote.model.MoviesResponse
import dev.tavieto.movielibrary.data.remote.service.MovieService
import dev.tavieto.movielibrary.data.server.BuildConfig
import dev.tavieto.movielibrary.data.server.core.NetworkWrapper
import dev.tavieto.movielibrary.data.server.model.mapToRemote
import dev.tavieto.movielibrary.data.server.retrofit.MovieRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MovieServiceImpl(
    private val service: MovieRetrofitService
) : MovieService {
    override suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int,
        sessionId: String
    ): Flow<Either<MoviesResponse>> = flow {
        emit(
            runCatchSuspendData {
                NetworkWrapper {
                    if (movieListType == MovieListType.FAVORITE) {
                        service.getFavoriteMovies(
                            accountId = BuildConfig.TMDB_ACCOUNT_ID,
                            page = page,
                            language = "pt-BR",
                            sortedBy = "created_at.asc",
                            sessionId = sessionId
                        )
                    } else {
                        service.getMovieList(
                            movieListType = movieListType.id,
                            page = page,
                            language = "pt-BR"
                        )
                    }
                }.results.mapToRemote()
            }
        )
    }
}