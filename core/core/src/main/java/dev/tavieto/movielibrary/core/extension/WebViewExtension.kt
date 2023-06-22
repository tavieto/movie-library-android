@file:Suppress("TooGenericExceptionCaught", "SwallowedException", "TooManyFunctions")

package dev.tavieto.movielibrary.core.extension

import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.raptor.sports.core.R
import com.raptor.sports.core.model.event.EventFocus
import com.raptor.sports.core.model.event.EventFraming
import com.raptor.sports.core.model.event.EventMaterial
import com.raptor.sports.core.model.event.EventModel
import com.raptor.sports.core.model.event.EventModelLog
import com.raptor.sports.core.model.event.EventRotation
import com.raptor.sports.core.model.event.EventSceneSetup
import com.raptor.sports.core.model.event.EventVisibility
import com.raptor.sports.core.util.enums.EventType
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun WebView.setMaterial(material: EventMaterial, action: (Boolean?) -> Unit = {}) {

    val event = getEvent(
        EventType.CHANGE_MATERIAL.text,
        Gson().toJson(material).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.setModel(model: EventModel, action: (Boolean?) -> Unit = {}) {
    val gson = GsonBuilder().serializeNulls().create()
    val event = getEvent(
        EventType.CHANGE_MODEL.text,
        gson.toJson(model).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.setVisibility(visibility: EventVisibility, action: (Boolean?) -> Unit = {}) {
    val event = getEvent(
        EventType.CHANGE_VISIBILITY.text,
        Gson().toJson(visibility).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.setFraming(framing: EventFraming, action: (Boolean?) -> Unit = {}) {
    val event = getEvent(
        EventType.CHANGE_FRAMING.text,
        Gson().toJson(framing).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.setFocus(focus: EventFocus, action: (Boolean?) -> Unit = {}) {
    val event = getEvent(
        EventType.FOCUS_OBJECT.text,
        Gson().toJson(focus).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.setScene(scene: EventSceneSetup, action: (Boolean?) -> Unit = {}) {
    val event = getEvent(
        EventType.SETUP_SCENE.text,
        Gson().toJson(scene).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.setRotation(rotation: EventRotation, action: (Boolean?) -> Unit = {}) {
    val event = getEvent(
        EventType.ROTATION_MODEL.text,
        Gson().toJson(rotation).toString()
    )
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.takeScreenShot(action: (Boolean?) -> Unit = {}) {
    val event = getEvent(EventType.TAKE_SNAPSHOT.text)
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.showPins(show: Boolean, action: (Boolean?) -> Unit = {}) {
    val event = if (show) getEvent(EventType.SHOW_PINS.text) else getEvent(EventType.HIDE_PINS.text)
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.resetModel(action: (Boolean?) -> Unit = {}) {
    val event = getEvent(EventType.RESET_MODEL.text)
    injectJavaScript(event) {
        action(it)
    }
}

fun WebView.modelProgressListener(action: (EventModelLog?) -> Unit = {}) {
    webChromeClient = object : WebChromeClient() {
        override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
            try {
                val progress: EventModelLog = Gson().fromJson(
                        consoleMessage.message(),
                        EventModelLog::class.java
                    )
                action(progress)
            } catch (e: Exception) {
                action(null)
            }
            return true
        }
    }
}

private fun WebView.getEvent(event: String, detail: String): String =
    String.format(context.getString(R.string.event_customization_header), event, detail)

private fun WebView.getEvent(event: String): String =
    String.format(context.getString(R.string.event_customization_single_header), event)

private fun WebView.injectJavaScript(script: String, event: (Boolean?) -> Unit) {
    evaluateJavascript(script) {
        event(
            when (it) {
                "true" -> true
                "false" -> false
                else -> null
            }
        )
    }
}
