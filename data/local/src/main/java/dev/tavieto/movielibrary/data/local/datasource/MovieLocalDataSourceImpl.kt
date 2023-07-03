package dev.tavieto.movielibrary.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.tavieto.movielibrary.data.local.dao.MovieDao
import dev.tavieto.movielibrary.data.local.entity.mapToEntity
import dev.tavieto.movielibrary.data.local.entity.mapToRepository
import dev.tavieto.movielibrary.repository.datasource.local.MovieLocalDataSource
import dev.tavieto.movielibrary.repository.model.MoviesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MovieLocalDataSourceImpl(
    private val dao: MovieDao,
    private val context: Context
) : MovieLocalDataSource {

    private val Context.movieDataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    private object MovieKeys {
        val pageCount = intPreferencesKey("page_count_key")
    }

    override fun saveMovies(moviePage: MoviesData) {
        dao.insertMovies(*moviePage.map { it.mapToEntity() }.toTypedArray())
    }

    override fun updateFavoriteMovies(movieId: Int, isFavorite: Boolean) {
        dao.updateFavoriteMovies(movieId, isFavorite)
    }

    override fun getMovies(): MoviesData {
        return dao.getMovies().mapToRepository()
    }

    override fun getFavoriteMovies(): MoviesData {
        return dao.getFavoriteMovies().mapToRepository()
    }

    override fun getLastPage(): Flow<Int> {
        return context.movieDataStore.data.map { preferences ->

            return@map preferences[MovieKeys.pageCount] ?: return@map 0
        }
    }

    private companion object {
        const val DATA_STORE_NAME = "movie_info"

    }
}
