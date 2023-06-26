package dev.tavieto.movielibrary.data.local.manager

import android.content.Context
import dev.tavieto.movielibrary.data.local.datastore.SessionKeys
import dev.tavieto.movielibrary.data.local.datastore.sessionDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LocalSessionManager(
    private val scope: CoroutineScope,
    private val context: Context
) {

    private val _state: Channel<SessionState> = Channel(Channel.BUFFERED)
    val state = _state.receiveAsFlow()

    fun checkConnection() {
        scope.launch {
            context.sessionDataStore.data.map { preferences ->
                preferences[SessionKeys.ID]
            }.collectLatest { id ->
                _state.send(
                    if (id == null) SessionState.DISCONNECTED else SessionState.CONNECTED
                )
            }
        }
    }

    fun connectSession() {
        scope.launch {
            _state.send(SessionState.CONNECTED)
        }
    }

    fun disconnectSession() {
        scope.launch {
            _state.send(SessionState.DISCONNECTED)
        }
    }
}

enum class SessionState {
    CONNECTED,
    DISCONNECTED;
}
