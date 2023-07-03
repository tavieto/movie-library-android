package dev.tavieto.movielibrary.data.server.di

import dev.tavieto.movielibrary.data.remote.BuildConfig
import dev.tavieto.movielibrary.data.remote.service.MovieService
import dev.tavieto.movielibrary.data.remote.service.TmbdService
import dev.tavieto.movielibrary.data.server.core.Retrofit
import dev.tavieto.movielibrary.data.server.interceptor.TmdbInterceptor
import dev.tavieto.movielibrary.data.server.retrofit.MovieRetrofitService
import dev.tavieto.movielibrary.data.server.retrofit.TmbdAuthRetrofitService
import dev.tavieto.movielibrary.data.server.service.MovieServiceImpl
import dev.tavieto.movielibrary.data.server.service.TmbdServiceImpl
import org.koin.dsl.module

val serverDataModule = module {
    single<MovieService> { MovieServiceImpl(get()) }
    single<TmbdService> { TmbdServiceImpl(get()) }

    single<MovieRetrofitService> {
        Retrofit(
            baseUrl = BuildConfig.TMDB_API_BASE,
            interceptor = TmdbInterceptor()
        )
    }
    single<TmbdAuthRetrofitService> {
        Retrofit(
            baseUrl = BuildConfig.TMDB_API_BASE,
            interceptor = TmdbInterceptor()
        )
    }
}
