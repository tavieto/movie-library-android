package dev.tavieto.movielibrary.feature.main.ui.details

import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.movie.model.FavoriteMovieParams
import dev.tavieto.movielibrary.domain.movie.usecase.UpdateFavoriteMovieUseCase
import dev.tavieto.movielibrary.feature.main.model.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class DetailsViewModel(
    private val navigation: DetailsNavigation
) : ViewModel(), KoinComponent {

    private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase by useCase()

    private val _state = MutableStateFlow(DetailsViewState())
    val state = _state.asStateFlow()

    fun setMovie(movie: MovieModel) {
        _state.update { it.copy(movie = movie, isLoading = false) }
    }

    fun updateFavoriteMovie() {
        _state.value.movie?.run {
            updateFavoriteMovieUseCase(
                params = FavoriteMovieParams(
                    movieId = this.id,
                    isFavorite = isFavorite.not()
                ),
                onSuccess = {
                    _state.update { it.copy(movie = it.movie?.copy(isFavorite = isFavorite.not())) }
                },
                onFailure = {
                    it.printStackTrace()
                }
            )
        }
    }

    fun navigateBack() {
        navigation.popBackStack()
    }
}
