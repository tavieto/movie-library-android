package dev.tavieto.movielibrary.data.remote.model

import dev.tavieto.movielibrary.repository.model.UserData

data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val tmdbSessionId: String,
    val tmdbAccountId: Int
) {
    fun mapToRepository(): UserData {
        return UserData(
            id = this.id,
            name = this.name,
            email = this.email,
            tmdbSessionId = this.tmdbSessionId,
            tmdbAccountId = this.tmdbAccountId
        )
    }
}
