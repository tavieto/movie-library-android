package dev.tavieto.movielibrary.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import dev.tavieto.movielibrary.data.local.datastore.SessionKeys
import dev.tavieto.movielibrary.data.local.datastore.sessionDataStore
import dev.tavieto.movielibrary.data.local.manager.LocalSessionManager
import dev.tavieto.movielibrary.repository.datasource.local.SessionLocalDataSource
import dev.tavieto.movielibrary.repository.model.SessionData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SessionLocalDataSourceImpl(
    private val context: Context,
    private val manager: LocalSessionManager
) : SessionLocalDataSource {

    override suspend fun saveSession(session: SessionData) {
        context.sessionDataStore.edit { preferences ->
            preferences[SessionKeys.ID] = session.id
        }
        manager.connectSession()
    }

    override suspend fun deleteSession() {
        context.sessionDataStore.edit { preferences ->
            preferences.clear()
        }
        manager.disconnectSession()
    }

    override suspend fun getSession(): Flow<SessionData?> {
        return context.sessionDataStore.data.map { preferences ->
            val id = preferences[SessionKeys.ID] ?: return@map null

            SessionData(id = id)
        }
    }
}