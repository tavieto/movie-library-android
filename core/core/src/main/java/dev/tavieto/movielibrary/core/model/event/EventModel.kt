package dev.tavieto.movielibrary.core.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventModel(
    @SerializedName("modelGroup")
    val modelGroup: String?,
    @SerializedName("model")
    val model: String?
) : Event()
