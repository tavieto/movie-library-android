package dev.tavieto.movielibrary.data.server.model

import com.google.gson.annotations.SerializedName

data class RequestTokenServerRequest(
    @SerializedName("request_token")
    val requestToken: String
)
