package dev.tavieto.movielibrary.data.server.model


import com.google.gson.annotations.SerializedName

data class DatesServerResponse(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)