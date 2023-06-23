package dev.tavieto.movielibrary.feature.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: String,
    val name: String,
    val imageURL: String
) : Parcelable
