package dev.tavieto.movielibrary.repository.datasource.local

import dev.tavieto.movielibrary.repository.model.UserData

interface AuthLocalDataSource {
    fun saveUser(user: UserData)
    fun getUser(): UserData
}
