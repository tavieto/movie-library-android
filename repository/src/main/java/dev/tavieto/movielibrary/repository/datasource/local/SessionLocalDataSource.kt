package dev.tavieto.movielibrary.repository.datasource.local

import dev.tavieto.movielibrary.repository.model.SessionData
import kotlinx.coroutines.flow.Flow

interface SessionLocalDataSource {
    suspend fun saveSession(session: SessionData)
    suspend fun deleteSession()
    suspend fun getSession(): Flow<SessionData?>
}
