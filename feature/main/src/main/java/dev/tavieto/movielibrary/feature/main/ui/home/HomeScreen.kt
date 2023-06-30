package dev.tavieto.movielibrary.feature.main.ui.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.tavieto.movielibrary.core.commons.enums.MovieListType
import dev.tavieto.movielibrary.feature.main.R
import dev.tavieto.movielibrary.feature.main.ui.component.MovieItem

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()

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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(state.movies) { (type, movies) ->
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = stringResource(
                            id = when (type) {
                                MovieListType.NOW_PLAYING -> R.string.now_playing_movie_list_type_name
                                MovieListType.POPULAR -> R.string.popular_movie_list_type_name
                                MovieListType.TOP_RATED -> R.string.top_rated_movie_list_type_name
                                MovieListType.UPCOMING -> R.string.upcoming_movie_list_type_name
                            }
                        )
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(movies) { movie ->
                            MovieItem(
                                modifier = Modifier.clickable {
                                    viewModel.navigateToDetails(movie)
                                },
                                moviePosterPath = movie.posterPath,
                                height = 200.dp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (state.isInitialized.not()) {
            MovieListType.values().forEach { viewModel.getMovies(it) }
            viewModel.initialize()
        }
    }
}
