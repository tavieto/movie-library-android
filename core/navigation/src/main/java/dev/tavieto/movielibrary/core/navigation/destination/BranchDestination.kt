package dev.tavieto.movielibrary.core.navigation.destination

sealed class BranchDestination(private val name: String) {
    val route = "${name}_branch"

    object Auth : BranchDestination(name = "auth")
    object Main : BranchDestination(name = "main")
}
