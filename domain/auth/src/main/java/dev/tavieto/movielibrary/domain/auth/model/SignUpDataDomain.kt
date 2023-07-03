package dev.tavieto.movielibrary.domain.auth.model

data class SignUpDataDomain(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
