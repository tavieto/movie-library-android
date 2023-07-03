package dev.tavieto.movielibrary.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.tavieto.movielibrary.repository.datasource.local.AuthLocalDataSource
import dev.tavieto.movielibrary.repository.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AuthLocalDataSourceImpl(
    private val context: Context
) : AuthLocalDataSource {

    private object UserKey {
        val ID = stringPreferencesKey("id_user")
        val NAME = stringPreferencesKey("name_user")
        val EMAIL = stringPreferencesKey("email_user")
        val TMDB_SESSION_ID = stringPreferencesKey("session_id_user")
        val TMDB_ACCOUNT_ID = intPreferencesKey("account_id_user")
    }

    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    override suspend fun saveUser(user: UserData) {
        context.userDataStore.edit {
            it[UserKey.ID] = user.id
            it[UserKey.NAME] = user.name
            it[UserKey.EMAIL] = user.email

            if (user.tmdbSessionId != null) {
                it[UserKey.TMDB_SESSION_ID] = user.tmdbSessionId!!
            }
            if (user.tmdbAccountId != null) {
                it[UserKey.TMDB_ACCOUNT_ID] = user.tmdbAccountId!!
            }
        }
    }

    override suspend fun saveSessionId(sessionId: String) {
        context.userDataStore.edit {
            it[UserKey.TMDB_SESSION_ID] = sessionId
        }
    }

    override suspend fun saveAccountId(accountId: Int) {
        context.userDataStore.edit {
            it[UserKey.TMDB_ACCOUNT_ID] = accountId
        }
    }

    override suspend fun getUser(): Flow<UserData?> {
        return context.userDataStore.data.map {
            val id = it[UserKey.ID] ?: return@map null
            val name = it[UserKey.NAME] ?: return@map null
            val email = it[UserKey.EMAIL] ?: return@map null
            val sessionId = it[UserKey.TMDB_SESSION_ID] ?: return@map null
            val accountId = it[UserKey.TMDB_ACCOUNT_ID] ?: return@map null

            return@map UserData(
                id = id,
                name = name,
                email = email,
                tmdbSessionId = sessionId,
                tmdbAccountId = accountId
            )
        }
    }

    override suspend fun deleteUser() {
        context.userDataStore.edit { it.clear() }
    }
}
