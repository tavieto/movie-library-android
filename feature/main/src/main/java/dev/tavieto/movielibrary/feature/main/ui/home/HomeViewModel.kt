package dev.tavieto.movielibrary.feature.main.ui.home

import androidx.lifecycle.ViewModel

import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.usecase.GetTmdbRequestToken
import dev.tavieto.movielibrary.domain.auth.usecase.GetUserNameUseCase
import dev.tavieto.movielibrary.domain.auth.usecase.SignOutUseCase
import dev.tavieto.movielibrary.domain.movie.model.MovieParams
import dev.tavieto.movielibrary.domain.movie.usecase.GetFavoriteMovieListUseCase
import dev.tavieto.movielibrary.domain.movie.usecase.GetMovieListUseCase
import dev.tavieto.movielibrary.domain.movie.usecase.GetNowPlayingMovieListUseCase
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

    private val getMoviesUseCase: GetMovieListUseCase by useCase()
    private val getNowPlayingMovieListUseCase: GetNowPlayingMovieListUseCase by useCase()
    private val getFavoriteMoviesUseCase: GetFavoriteMovieListUseCase by useCase()
    private val signOutUseCase: SignOutUseCase by useCase()
    private val getTmdbRequestToken: GetTmdbRequestToken by useCase()
    private val getUserNameUseCase: GetUserNameUseCase by useCase()

    fun getMovies() {
        getMoviesUseCase(
            params = MovieParams(page = 1),
            onSuccess = { newMovies ->
                _state.update {
                    it.copy(
                        movies = newMovies.mapFromDomain()
                    )
                }
            },
            onFailure = { error ->
                error.printStackTrace()
                _state.update { it.copy(error = error) }
            }
        )
    }

    fun getFavoriteMovies() {
        getFavoriteMoviesUseCase(
            params = MovieParams(page = 1),
            onSuccess = { newMovies ->
                _state.update {
                    it.copy(
                        favoritesMovies = newMovies.mapFromDomain()
                    )
                }
            },
            onFailure = { error ->
                _state.update { it.copy(error = error) }
                error.printStackTrace()
            }
        )
    }

    fun getNowPlayingMovies() {
        getNowPlayingMovieListUseCase(
            params = MovieParams(page = 1),
            onSuccess = { newMovies ->
                _state.update {
                    it.copy(
                        nowPlayingMovies = newMovies.mapFromDomain()
                    )
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

    fun getTmdbRequestToken() {
        getTmdbRequestToken(
            onSuccess = { token ->
                _state.update {
                    it.copy(tmdbRequestToken = token)
                }
            },
            onFailure = {
                getMovies()
                getNowPlayingMovies()
                getFavoriteMovies()
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
            onFailure = { error ->
                _state.update { it.copy(userName = "") }
                error.printStackTrace()
            }
        )
    }

    fun updateTabIndex(newIndex: Int) {
        _state.update { it.copy(tabIndex = newIndex) }
    }

    fun setSearchText(text: String) {
        _state.update {
            it.copy(
                searchText = text,
                resultSearch = it.movies.filter { movie -> movie.title.contains(text) }
            )
        }
    }
}
