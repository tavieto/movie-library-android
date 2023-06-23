package dev.tavieto.movielibrary.domain.auth.di

import dev.tavieto.movielibrary.domain.auth.usecase.SignInUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val authDomainModule = module {
    factory { (scope: CoroutineScope) ->
        SignInUseCase(scope, get())
    }
}
