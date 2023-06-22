package dev.tavieto.movielibrary.core.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventVisibility(
    @SerializedName("object")
    val side: String,
    @SerializedName("visibility")
    val visibility: Boolean
) : Event() {
    companion object {
        val HIDE_LEFT = EventVisibility(
            side = "esquerda",
            visibility = false
        )
        val HIDE_RIGHT = EventVisibility(
            side = "direita",
            visibility = false
        )

        val SHOW_LEFT = EventVisibility(
            side = "esquerda",
            visibility = true
        )
        val SHOW_RIGHT = EventVisibility(
            side = "direita",
            visibility = true
        )
    }
}
