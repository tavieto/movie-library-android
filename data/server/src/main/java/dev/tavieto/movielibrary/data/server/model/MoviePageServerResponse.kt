package dev.tavieto.movielibrary.data.server.model

import com.google.gson.annotations.SerializedName

data class MoviePageServerResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: MoviesServerResponse,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)