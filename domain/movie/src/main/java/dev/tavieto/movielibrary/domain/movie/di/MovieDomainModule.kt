package dev.tavieto.movielibrary.domain.movie.di

import dev.tavieto.movielibrary.domain.movie.usecase.GetMoviesByTypeUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val movieDomainModule = module {
    factory { (scope: CoroutineScope) ->
        GetMoviesByTypeUseCase(scope, get())
    }
}
