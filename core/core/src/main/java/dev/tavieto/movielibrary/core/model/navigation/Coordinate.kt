package dev.tavieto.movielibrary.core.model.navigation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinate(
    @SerializedName("origin")
    var origin: String,
    @SerializedName("destination")
    var destination: String
) : Parcelable
