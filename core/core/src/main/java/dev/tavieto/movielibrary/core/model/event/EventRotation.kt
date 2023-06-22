package dev.tavieto.movielibrary.core.model.event

import com.raptor.sports.core.util.enums.Models
import com.google.gson.annotations.SerializedName

data class EventRotation(
    @SerializedName("rotation")
    val rotation: Rotation,
    @SerializedName("model")
    val model: String
) {
    companion object {
        val ROTATION_START = EventRotation(
            rotation = Rotation(
                x = -92,
                y = 158,
                z = 92
            ),
            model = Models.CHINELO.text
        )
        val ROTATION_SOLE = EventRotation(
            rotation = Rotation(
                x = -92,
                y = 180,
                z = 85
            ),
            model = Models.CHINELO.text
        )
        val ROTATION_STRAP = EventRotation(
            rotation = Rotation(
                x = -125,
                y = 148,
                z = 124
            ),
            model = Models.CHINELO.text
        )

        val ROTATION_PIN = EventRotation(
            rotation = Rotation(
                x = 0,
                y = 0,
                z = 0
            ),
            model = Models.CHINELO.text
        )
    }
}

data class Rotation(
    @SerializedName("x")
    val x: Int,
    @SerializedName("y")
    val y: Int,
    @SerializedName("z")
    val z: Int
)
