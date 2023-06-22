package dev.tavieto.movielibrary.core.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventModelLog(
    @SerializedName("type")
    val type: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("progress")
    val progress: Float?,
    @SerializedName("data")
    val data: String,
) : Event()
