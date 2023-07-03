package dev.tavieto.movielibrary.data.remote.di

import dev.tavieto.movielibrary.data.remote.datasource.AuthRemoteDataSourceImpl
import dev.tavieto.movielibrary.data.remote.datasource.MovieRemoteDataSourceImpl
import dev.tavieto.movielibrary.repository.datasource.remote.AuthRemoteDataSource
import dev.tavieto.movielibrary.repository.datasource.remote.MovieRemoteDataSource
import org.koin.dsl.module

val remoteDataModule = module {
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(get(), get()) }
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}
