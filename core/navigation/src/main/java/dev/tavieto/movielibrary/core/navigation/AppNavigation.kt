package dev.tavieto.movielibrary.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.tavieto.movielibrary.core.navigation.graph.addAuthGraph
import dev.tavieto.movielibrary.core.navigation.graph.addMainGraph
import dev.tavieto.movielibrary.core.navigation.routes.AuthRoutes

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = AuthRoutes.branch.route
    ) {
        addAuthGraph()
        addMainGraph()
    }
}
