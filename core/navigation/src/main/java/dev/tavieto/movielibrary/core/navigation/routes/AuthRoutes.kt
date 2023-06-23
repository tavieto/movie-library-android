package dev.tavieto.movielibrary.core.navigation.routes

import dev.tavieto.movielibrary.core.navigation.core.Routes
import dev.tavieto.movielibrary.core.navigation.destination.BranchDestination
import dev.tavieto.movielibrary.core.navigation.destination.LeafDestination

object AuthRoutes : Routes {
    override val branch: BranchDestination = BranchDestination.Auth

    object Introduction : LeafDestination(root = branch, route = "introduction")
    object SignIn : LeafDestination(root = branch, route = "sign_in")
    object SignUp : LeafDestination(root = branch, route = "sign_up")
}
