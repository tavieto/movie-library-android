package dev.tavieto.movielibrary.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.tavieto.movielibrary.core.navigation.AppNavigation
import dev.tavieto.movielibrary.core.navigation.manager.NavigationCommand
import dev.tavieto.movielibrary.core.navigation.manager.NavigationManager
import dev.tavieto.movielibrary.core.navigation.manager.NavigationType
import dev.tavieto.movielibrary.core.navigation.routes.AuthRoutes
import dev.tavieto.movielibrary.core.navigation.routes.MainRoutes
import dev.tavieto.movielibrary.core.uikit.theme.MovieLibraryTheme
import dev.tavieto.movielibrary.data.local.manager.LocalSessionManager
import dev.tavieto.movielibrary.data.local.manager.SessionState
import dev.tavieto.movielibrary.domain.auth.usecase.SaveRequestIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity(), KoinComponent {

    private val navManager: NavigationManager by inject()
    private val localSessionManager: LocalSessionManager by inject()
    private val saveRequestIdUseCase: SaveRequestIdUseCase by inject {
        parametersOf(lifecycleScope + Dispatchers.IO)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val requestId = intent?.data?.getQueryParameter("request_token")
        saveRequestIdUseCase(
            params = requestId,
            onSuccess = {
                runOnUiThread {
                    Toast.makeText(this, "TMDB - connected", Toast.LENGTH_SHORT).show()
                }
            },
            onFailure = {
                runOnUiThread {
                    Toast.makeText(this, "TMDB - error connection", Toast.LENGTH_SHORT).show()
                }
                it.printStackTrace()
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MovieLibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(navController = navController)
                }
            }

            LaunchedEffect(Unit) {
                observeAndNavigate(navManager, navController)
                observeLocalSession(localSessionManager, navManager)
            }
        }
    }

    private fun observeLocalSession(
        localSessionManager: LocalSessionManager,
        navManager: NavigationManager
    ) {
        localSessionManager.checkConnection()
        lifecycleScope.launch {
            localSessionManager.state.collectLatest { state ->
                val route = when (state) {
                    SessionState.CONNECTED -> MainRoutes.Home.createRoute()
                    SessionState.DISCONNECTED -> AuthRoutes.Introduction.createRoute()
                }
                navManager.navigate(route = route)
            }
        }
    }

    private fun observeAndNavigate(
        navManager: NavigationManager,
        navController: NavController
    ) {
        lifecycleScope.launch {
            navManager.commands.collectLatest { command ->
                when (command) {
                    is NavigationCommand.Navigate -> navigate(command, navController)
                    is NavigationCommand.NavigateUp -> navController.navigateUp()
                    is NavigationCommand.PopBackStack.Arguments -> popBackStack(
                        command,
                        navController
                    )

                    is NavigationCommand.PopBackStack.Default -> navController.popBackStack()
                }
            }
        }
    }

    private fun navigate(
        command: NavigationCommand.Navigate,
        navController: NavController
    ) {
        when (val type = command.type) {
            is NavigationType.NavigateTo -> navController.navigate(
                route = command.destination,
                navOptions = command.navOptions
            )

            is NavigationType.PopUpTo -> navController.popBackStack(
                route = command.destination,
                inclusive = type.inclusive
            )
        }
    }

    private fun popBackStack(
        command: NavigationCommand.PopBackStack.Arguments,
        navController: NavController
    ) {
        if (command.route.isNullOrBlank()) {
            navController.previousBackStackEntry?.savedStateHandle?.let { savedState ->
                for ((key, data) in command.value) {
                    savedState[key] = data
                }
            }
            navController.popBackStack()
        } else {
            val route = command.route ?: ""

            navController.getBackStackEntry(route).savedStateHandle.let { savedState ->
                for ((key, data) in command.value) {
                    savedState[key] = data
                }
            }
            navController.popBackStack(route = route, inclusive = false)
        }
    }
}