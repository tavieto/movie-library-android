package dev.tavieto.movielibrary.data.local.di

import androidx.room.Room
import dev.tavieto.movielibrary.data.local.database.MovieDatabase
import dev.tavieto.movielibrary.data.local.datasource.AuthLocalDataSourceImpl
import dev.tavieto.movielibrary.data.local.datasource.MovieLocalDataSourceImpl
import dev.tavieto.movielibrary.data.local.datasource.SessionLocalDataSourceImpl
import dev.tavieto.movielibrary.data.local.manager.LocalSessionManager
import dev.tavieto.movielibrary.repository.datasource.local.AuthLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.local.MovieLocalDataSource
import dev.tavieto.movielibrary.repository.datasource.local.SessionLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    single {
        val db = get<MovieDatabase>()
        db.movieDao()
    }

    single {
        LocalSessionManager(
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
            context = androidContext()
        )
    }
    single<SessionLocalDataSource> { SessionLocalDataSourceImpl(androidContext(), get()) }
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(androidContext()) }
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get(), get()) }
}
