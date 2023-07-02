package dev.tavieto.movielibrary.repository.datasource.local

import dev.tavieto.movielibrary.repository.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveUser(user: UserData)
    suspend fun saveSessionId(sessionId: String)
    suspend fun getUser(): Flow<UserData?>
    suspend fun deleteUser()
}
