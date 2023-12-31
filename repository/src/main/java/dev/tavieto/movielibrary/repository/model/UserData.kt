package dev.tavieto.movielibrary.repository.model

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val tmdbSessionId: String?,
    val tmdbAccountId: Int?
)
