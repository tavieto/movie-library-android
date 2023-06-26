package dev.tavieto.movielibrary.domain.auth.di

import dev.tavieto.movielibrary.domain.auth.usecase.SignInUseCase
import dev.tavieto.movielibrary.domain.auth.usecase.SignOutUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val authDomainModule = module {
    factory { (scope: CoroutineScope) ->
        SignInUseCase(scope, get())
    }
    factory { (scope: CoroutineScope) ->
        SignOutUseCase(scope, get())
    }
}
