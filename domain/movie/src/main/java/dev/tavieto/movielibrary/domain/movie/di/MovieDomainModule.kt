package dev.tavieto.movielibrary.domain.movie.di

import dev.tavieto.movielibrary.domain.movie.usecase.GetFavoriteMovieListUseCase
import dev.tavieto.movielibrary.domain.movie.usecase.GetMovieListUseCase
import dev.tavieto.movielibrary.domain.movie.usecase.GetNowPlayingMovieListUseCase
import dev.tavieto.movielibrary.domain.movie.usecase.UpdateFavoriteMovieUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val movieDomainModule = module {
    factory { (scope: CoroutineScope) ->
        GetMovieListUseCase(scope, get())
    }
    factory { (scope: CoroutineScope) ->
        GetFavoriteMovieListUseCase(scope, get())
    }
    factory { (scope: CoroutineScope) ->
        GetNowPlayingMovieListUseCase(scope, get())
    }
    factory { (scope: CoroutineScope) ->
        UpdateFavoriteMovieUseCase(scope, get())
    }
}
