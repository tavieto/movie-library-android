package dev.tavieto.movielibrary.feature.main.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.usecase.SignOutUseCase
import dev.tavieto.movielibrary.domain.movie.model.MovieParams
import dev.tavieto.movielibrary.domain.movie.usecase.GetMoviesByTypeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class HomeViewModel: ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private val getMovieUseCase: GetMoviesByTypeUseCase by useCase()
    private val signOutUseCase: SignOutUseCase by useCase()

    fun getMovies(movieType: MovieListType) {
        Log.d("TAG", "start $movieType")
        getMovieUseCase(
            params = MovieParams(
                type = movieType,
                page = 1
            ),
            onSuccess = { movies ->
                Log.d("TAG", "success $movieType")
                _state.update { it.copy(movies = movies) }
            },
            onFailure = {
                Log.d("TAG", "failure $movieType", it)
            }
        )
    }

    fun signOut() {
        signOutUseCase()
    }
}
