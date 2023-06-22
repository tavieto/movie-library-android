package dev.tavieto.movielibrary.core.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventFocus(
    @SerializedName("object")
    val obj: String,
    @SerializedName("zoom")
    val zoom: Float
) : Event()
