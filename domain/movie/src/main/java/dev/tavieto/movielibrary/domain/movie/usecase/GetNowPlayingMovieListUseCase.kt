package dev.tavieto.movielibrary.domain.movie.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.core.commons.exception.InvalidParameterException
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain
import dev.tavieto.movielibrary.domain.movie.model.MovieParams
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetNowPlayingMovieListUseCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<MovieParams, MovieListDomain>(scope) {
    override suspend fun run(params: MovieParams?): Flow<Either<MovieListDomain>> {
        if (params == null) throw MissingParamsException()
        if (params.page <= 0) throw InvalidParameterException()

        return repository.getMovieList(params.page)
    }
}
