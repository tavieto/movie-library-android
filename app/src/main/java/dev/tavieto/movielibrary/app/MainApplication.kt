package dev.tavieto.movielibrary.app

import android.app.Application
import dev.tavieto.movielibrary.core.navigation.di.navigationModule
import dev.tavieto.movielibrary.domain.auth.di.authDomainModule
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    navigationModule,
                    authDomainModule
                )
            )
        }
    }
}