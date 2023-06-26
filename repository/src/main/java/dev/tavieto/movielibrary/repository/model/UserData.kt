package dev.tavieto.movielibrary.repository.model

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val imageURL: String,
    val favoriteMoviesID: List<String>
)
