package dev.tavieto.movielibrary.core.navigation

sealed class BranchDestination(private val name: String) {
    val route = "${name}_branch"

    object Auth : BranchDestination(name = "auth")
    object Home : BranchDestination(name = "home")
}
