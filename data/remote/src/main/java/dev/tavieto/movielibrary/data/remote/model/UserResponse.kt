package dev.tavieto.movielibrary.data.remote.model

data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val imageURL: String,
    val favoriteMoviesID: List<String>
)
