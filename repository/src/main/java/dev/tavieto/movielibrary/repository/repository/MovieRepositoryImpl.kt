package dev.tavieto.movielibrary.repository.repository

import android.util.Log
import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.mapCatching
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import dev.tavieto.movielibrary.repository.datasource.local.MovieLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import dev.tavieto.movielibrary.repository.model.mapToDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

internal class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getMovieList(
        movieListType: MovieListType,
        page: Int
    ): Flow<Either<MovieListDomain>> {
        return flow {
            val localMovies = local.getMovies(movieListType)
            val lastPage = local.getLastPage().first()
            Log.d("TAG", "repository ${movieListType.name}")
            if (localMovies.isEmpty()) {
                Log.d("TAG", "repository empty ${movieListType.name}")
                emitAll(
                    remote.getMovieList(
                        movieListType = movieListType,
                        page = lastPage + 1
                    ).mapLatest { result ->
                        Log.d("TAG", "repository collect ${movieListType.name}")
                        result.mapCatching {
                            local.saveMovies(movieListType, it)
                            local.getMovies(movieListType).mapToDomain()
                        }
                    }
                )
            } else {
                Log.d("TAG", "repository not empty ${movieListType.name}")
                emit(Either.Success(localMovies.mapToDomain()))
            }
        }
    }
}