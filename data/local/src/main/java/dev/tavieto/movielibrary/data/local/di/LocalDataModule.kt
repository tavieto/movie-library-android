package dev.tavieto.movielibrary.data.local.di

import dev.tavieto.movielibrary.data.local.datasource.SessionLocalDataSourceImpl
import dev.tavieto.movielibrary.data.local.manager.LocalSessionManager
import dev.tavieto.movielibrary.repository.datasource.local.SessionLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single {
        LocalSessionManager(
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
            context = androidContext()
        )
    }
    single<SessionLocalDataSource> { SessionLocalDataSourceImpl(androidContext(), get()) }
}
