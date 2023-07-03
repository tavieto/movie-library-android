package dev.tavieto.movielibrary.data.server.model


import com.google.gson.annotations.SerializedName

data class SessionIdServerResponse(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)