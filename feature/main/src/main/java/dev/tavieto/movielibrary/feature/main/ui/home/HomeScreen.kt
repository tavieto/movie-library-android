package dev.tavieto.movielibrary.feature.main.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import dev.tavieto.movielibrary.feature.main.BuildConfig
import dev.tavieto.movielibrary.feature.main.R
import dev.tavieto.movielibrary.feature.main.ui.component.MovieItem

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()
    val activity = LocalContext.current as Activity
    val tabsText = remember {
        mutableStateListOf("Em cartaz", "Que existem", "Favoritos")
    }
    var tabIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.home_screen_welcome))
                    Text(
                        text = state.userName,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = null)
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                    }
                    IconButton(
                        onClick = { viewModel.signOut() }
                    ) {
                        Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TabRow(selectedTabIndex = tabIndex) {
                tabsText.forEachIndexed { index, text ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            if (tabIndex != index) {
                                tabIndex = index
                            }
                        },
                        text = {
                            Text(text = text)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(visible = state.tmdbRequestToken != null) {
                TextButton(
                    onClick = {
                        val url = BuildConfig.TMDB_URL_AUTH + state.tmdbRequestToken
                        val urlQuery =
                            "?redirect_to=" + "tavietodev://movielibrary.app/tmdb-approved"
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url + urlQuery)
                        activity.startActivity(intent)
                    }
                ) {
                    Text(text = "Connect with your TMDB account")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Crossfade(targetState = tabIndex) {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
                    columns = GridCells.Adaptive(90.dp),
                    content = {
                        when (it) {
                            0 -> items(state.nowPlayingMovies) { movie ->
                                MovieItem(
                                    modifier = Modifier.clickable {
                                        viewModel.navigateToDetails(movie)
                                    },
                                    moviePosterPath = movie.posterPath,
                                    height = 150.dp
                                )
                            }

                            1 -> items(state.movies) { movie ->
                                MovieItem(
                                    modifier = Modifier.clickable {
                                        viewModel.navigateToDetails(movie)
                                    },
                                    moviePosterPath = movie.posterPath,
                                    height = 150.dp
                                )
                            }

                            else -> items(state.favoritesMovies) { movie ->
                                MovieItem(
                                    modifier = Modifier.clickable {
                                        viewModel.navigateToDetails(movie)
                                    },
                                    moviePosterPath = movie.posterPath,
                                    height = 150.dp
                                )
                            }
                        }
                    },
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getMovies()
        viewModel.getFavoriteMovies()
        viewModel.getNowPlayingMovies()
        viewModel.getUserName()
        viewModel.getTmdbRequestToken()
    }
}
