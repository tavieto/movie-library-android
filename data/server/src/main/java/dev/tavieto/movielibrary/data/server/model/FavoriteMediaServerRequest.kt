package dev.tavieto.movielibrary.data.server.model


import com.google.gson.annotations.SerializedName

data class FavoriteMediaServerRequest(
    @SerializedName("favorite")
    val favorite: Boolean = true,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String = "movie"
)