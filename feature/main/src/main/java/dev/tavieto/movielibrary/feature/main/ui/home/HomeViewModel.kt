package dev.tavieto.movielibrary.feature.main.ui.home

import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.usecase.GetTmdbRequestToken
import dev.tavieto.movielibrary.domain.auth.usecase.GetUserNameUseCase
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
    private val getTmdbRequestToken: GetTmdbRequestToken by useCase()
    private val getUserNameUseCase: GetUserNameUseCase by useCase()

    fun getMovies(movieType: MovieListType) {
        getMovieUseCase(
            params = MovieParams(
                type = movieType,
                page = 1
            ),
            onSuccess = { newMovies ->
                when (movieType) {
                    MovieListType.NOW_PLAYING -> {
                        _state.update {
                            it.copy(
                                nowPlayingMovies = newMovies.mapFromDomain()
                            )
                        }
                    }

                    MovieListType.POPULAR -> {
                        _state.update {
                            it.copy(
                                popularMovies = newMovies.mapFromDomain()
                            )
                        }
                    }

                    MovieListType.FAVORITE -> {
                        _state.update {
                            it.copy(
                                favoritesMovies = newMovies.mapFromDomain()
                            )
                        }
                    }

                    else -> {

                    }
                }
            },
            onFailure = { error ->
                _state.update { it.copy(error = error) }
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

    fun getTmdbRequestToken() {
        getTmdbRequestToken(
            onSuccess = { token ->
                _state.update {
                    it.copy(tmdbRequestToken = token)
                }
            },
            onFailure = {
                _state.update {
                    it.copy(tmdbRequestToken = null)
                }
            }
        )
    }

    fun getUserName() {
        getUserNameUseCase(
            onSuccess = { name ->
                _state.update { it.copy(userName = name) }
            },
            onFailure = {
                _state.update { it.copy(userName = "error") }
            }
        )
    }
}
