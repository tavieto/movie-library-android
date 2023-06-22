package dev.tavieto.movielibrary.core.model.event

import com.google.gson.annotations.SerializedName
import kotlin.math.PI

data class EventSceneSetup(
    @SerializedName("rotate")
    val rotate: SceneRotation,
    @SerializedName("camera")
    val camera: EventFraming,
    @SerializedName("hotspots")
    val hotspots: EventHotspots,
    @SerializedName("resetFov")
    val resetFov: Boolean,
    @SerializedName("controlsMode")
    val controlsMode: String,
    @SerializedName("controlsEnable")
    val controlsEnable: Boolean = true
) {
    companion object {

        val SCENE_DEFAULT = EventSceneSetup(
            rotate = SceneRotation(
                rx = PI.toFloat() / 2f,
                ry = 0f,
                rz = 0f
            ),
            camera = EventFraming.FRAMING_DEFAULT,
            hotspots = EventHotspots(visible = false),
            resetFov = true,
            controlsMode = "orbit"
        )

        val SCENE_SOLE = SCENE_DEFAULT

        val SCENE_STRAP = SCENE_DEFAULT

        val SCENE_PIN = EventSceneSetup(
            rotate = SceneRotation(
                rx = PI.toFloat() / 2f,
                ry = 0f,
                rz = 0f
            ),
            camera = EventFraming.FRAMING_DEFAULT,
            hotspots = EventHotspots(visible = true),
            resetFov = true,
            controlsMode = "pan",
            controlsEnable = true
        )

        val SCENE_PIN_RIGHT = EventSceneSetup(
            rotate = SceneRotation(
                rx = 1.68f,
                ry = 0f,
                rz = -1.5708f
            ),
            camera = EventFraming.FRAMING_PIN_RIGHT,
            hotspots = EventHotspots(visible = true),
            resetFov = true,
            controlsMode = "pan",
            controlsEnable = false
        )

        val SCENE_PIN_LEFT = EventSceneSetup(
            rotate = SceneRotation(
                rx = 1.50f,
                ry = 0f,
                rz = -1.5708f
            ),
            camera = EventFraming.FRAMING_PIN_LEFT,
            hotspots = EventHotspots(visible = true),
            resetFov = true,
            controlsMode = "pan",
            controlsEnable = false
        )
    }
}

data class EventHotspots(
    @SerializedName("visible")
    val visible: Boolean,
)

data class SceneRotation(
    @SerializedName("rx")
    val rx: Float,
    @SerializedName("ry")
    val ry: Float,
    @SerializedName("rz")
    val rz: Float,
)
