package dev.tavieto.movielibrary.domain.movie.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.core.commons.exception.InvalidParameterException
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.domain.movie.model.MovieParams
import dev.tavieto.movielibrary.domain.movie.model.MoviesDomain
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMoviesByTypeUseCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<MovieParams, MoviesDomain>(scope) {
    override suspend fun run(params: MovieParams?): Flow<Either<MoviesDomain>> {
        if (params == null) throw MissingParamsException()
        if (params.page <= 0) throw InvalidParameterException()

        return repository.getMovieList(params.type, params.page)
    }
}