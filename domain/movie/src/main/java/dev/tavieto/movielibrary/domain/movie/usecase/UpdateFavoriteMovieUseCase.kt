package dev.tavieto.movielibrary.domain.movie.usecase

import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.base.UseCase
import dev.tavieto.movielibrary.core.commons.exception.MissingParamsException
import dev.tavieto.movielibrary.domain.movie.model.FavoriteMovieParams
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class UpdateFavoriteMovieUseCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<FavoriteMovieParams, Unit>(scope) {
    override suspend fun run(params: FavoriteMovieParams?): Flow<Either<Unit>> {
        if (params == null) throw MissingParamsException()
        return repository.updateFavoriteMovie(
            movieId = params.movieId,
            isFavorite = params.isFavorite
        )
    }

}
