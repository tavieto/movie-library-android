package dev.tavieto.movielibrary.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val SESSION_DATA_STORE_NAME = "session"

internal object SessionKeys {
    val ID = stringPreferencesKey("id_session")
}

internal val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = SESSION_DATA_STORE_NAME)