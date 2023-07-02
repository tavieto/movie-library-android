package dev.tavieto.movielibrary.data.server.model


import com.google.gson.annotations.SerializedName

data class FavoriteMediaServerResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)