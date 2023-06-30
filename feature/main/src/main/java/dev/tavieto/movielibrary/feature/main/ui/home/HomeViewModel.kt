package dev.tavieto.movielibrary.feature.main.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.usecase.SignOutUseCase
import dev.tavieto.movielibrary.domain.movie.model.MovieParams
import dev.tavieto.movielibrary.domain.movie.usecase.GetMoviesByTypeUseCase
import dev.tavieto.movielibrary.feature.main.model.MovieModel
import dev.tavieto.movielibrary.feature.main.model.mapFromDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val navigation: HomeNavigation
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private val getMovieUseCase: GetMoviesByTypeUseCase by useCase()
    private val signOutUseCase: SignOutUseCase by useCase()

    fun getMovies(movieType: MovieListType) {
        getMovieUseCase(
            params = MovieParams(
                type = movieType,
                page = 1
            ),
            onSuccess = { newMovies ->
                val newMoviesMapped = newMovies.mapFromDomain()
                Log.d("TAG", "success $movieType")
                val movieTypePair = _state.value.movies.firstOrNull { it.first == movieType }

                _state.update { newState ->
                    val list = newState.movies.toMutableList()
                    if (movieTypePair == null) {
                        list.add(movieType to newMoviesMapped)
                    } else {
                        val index = newState.movies.indexOfFirst { movieTypePair.first == it.first }
                        movieTypePair.second.toMutableList().addAll(newMoviesMapped)
                        list.add(index, movieTypePair)
                    }
                    newState.copy(movies = list)
                }
            },
            onFailure = {
                Log.d("TAG", "failure $movieType", it)
            }
        )
    }

    fun navigateToDetails(movie: MovieModel) {
        navigation.navigateToDetails(movie)
    }

    fun signOut() {
        signOutUseCase()
    }

    fun initialize() {
        _state.update { it.copy(isInitialized = true) }
    }
}
