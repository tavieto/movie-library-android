package dev.tavieto.movielibrary.data.server.model

import com.google.gson.annotations.SerializedName

data class AccountDetailsServerResponse(
    @SerializedName("id")
    val id: Int
)
