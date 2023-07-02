package dev.tavieto.movielibrary.data.server.model


import com.google.gson.annotations.SerializedName

data class RequestTokenServerResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)