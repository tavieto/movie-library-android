package dev.tavieto.movielibrary.core.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun <T> StateFlow<T>.rememberFlowWithLifecycle(
    initialValue: T = this.value,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): State<T> {
    return produceState(initialValue, this, lifecycle, minActiveState) {
        this@rememberFlowWithLifecycle.flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = minActiveState
        )
            .distinctUntilChanged()
            .collect {
                this@produceState.value = it
            }
    }
}
