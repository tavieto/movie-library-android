package dev.tavieto.movielibrary.app

import android.app.Application
import dev.tavieto.movielibrary.core.navigation.di.navigationModule
import dev.tavieto.movielibrary.data.firebase.di.firebaseDataModule
import dev.tavieto.movielibrary.data.local.di.localDataModule
import dev.tavieto.movielibrary.data.remote.di.remoteDataModule
import dev.tavieto.movielibrary.data.server.di.serverDataModule
import dev.tavieto.movielibrary.domain.auth.di.authDomainModule
import dev.tavieto.movielibrary.domain.movie.di.movieDomainModule
import dev.tavieto.movielibrary.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    navigationModule,
                    firebaseDataModule,
                    localDataModule,
                    remoteDataModule,
                    serverDataModule,
                    authDomainModule,
                    movieDomainModule,
                    repositoryModule
                )
            )
        }.androidContext(this)
    }
}