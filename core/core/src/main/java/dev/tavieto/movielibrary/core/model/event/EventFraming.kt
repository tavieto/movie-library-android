package dev.tavieto.movielibrary.core.model.event

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventFraming(
    @SerializedName("targetPosition")
    val targetPosition: EventTargetPosition,
    @SerializedName("orbit")
    val orbit: EventOrbit
) : Event() {
    companion object {
        val FRAMING_SOLE = EventFraming(
            targetPosition = EventTargetPosition(
                x = 0f,
                y = 0.07f,
                z = -0.1f
            ),
            orbit = EventOrbit(
                theta = 40f,
                phi = 55f,
                radius = 0.5f
            )
        )
        val FRAMING_DEFAULT = EventFraming(
            targetPosition = EventTargetPosition(
                x = 0f,
                y = 0.11f,
                z = -0.1f
            ),
            orbit = EventOrbit(
                theta = 0f,
                phi = 90f,
                radius = 0.36f
            )
        )

        val FRAMING_STRAP = EventFraming(
            targetPosition = EventTargetPosition(
                x = 0.03f,
                y = 0f,
                z = -0.08f
            ),
            orbit = EventOrbit(
                theta = 30f,
                phi = 40f,
                radius = 105f
            )
        )

        val FRAMING_PIN = EventFraming(
            targetPosition = EventTargetPosition(
                x = 0f,
                y = 0.08f,
                z = 0.05f
            ),
            orbit = EventOrbit(
                theta = 89.0f,
                phi = 92.2f,
                radius = 20.62f
            )
        )

        val FRAMING_PIN_LEFT = EventFraming(
            targetPosition = EventTargetPosition(
                x = 0f,
                y = 0.13f,
                z = 0.11f
            ),
            orbit = EventOrbit(
                theta = 89.0f,
                phi = 92.2f,
                radius = 0f
            )
        )

        val FRAMING_PIN_RIGHT = EventFraming(
            targetPosition = EventTargetPosition(
                x = 0f,
                y = 0.13f,
                z = 0f
            ),
            orbit = EventOrbit(
                theta = 89.0f,
                phi = 92.2f,
                radius = 0f
            )
        )
    }
}

@Parcelize
data class EventTargetPosition(
    @SerializedName("x")
    val x: Float,
    @SerializedName("y")
    val y: Float,
    @SerializedName("z")
    val z: Float,
) : Parcelable

@Parcelize
data class EventOrbit(
    @SerializedName("theta")
    val theta: Float,
    @SerializedName("phi")
    val phi: Float,
    @SerializedName("radius")
    val radius: Float,
) : Parcelable
