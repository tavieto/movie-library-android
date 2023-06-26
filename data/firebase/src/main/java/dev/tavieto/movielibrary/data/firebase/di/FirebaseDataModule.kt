package dev.tavieto.movielibrary.data.firebase.di

import dev.tavieto.movielibrary.data.firebase.service.AuthServiceImpl
import dev.tavieto.movielibrary.data.remote.service.AuthService
import org.koin.dsl.module

val firebaseDataModule = module {
    single<AuthService> { AuthServiceImpl() }
}
