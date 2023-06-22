package dev.tavieto.movielibrary.core.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventMaterial(
    @SerializedName("model")
    val model: String,
    @SerializedName("slot")
    val slot: String,
    @SerializedName("material")
    val material: String
) : Event()
