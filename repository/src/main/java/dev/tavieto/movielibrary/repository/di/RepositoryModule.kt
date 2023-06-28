package dev.tavieto.movielibrary.repository.di

import dev.tavieto.movielibrary.domain.auth.repository.AuthRepository
import dev.tavieto.movielibrary.domain.movie.repository.MovieRepository
import dev.tavieto.movielibrary.repository.repository.AuthRepositoryImpl
import dev.tavieto.movielibrary.repository.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}
