package dev.tavieto.movielibrary.feature.main.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tavieto.movielibrary.core.commons.enums.MovieListType

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        viewModel.signOut()
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null)
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
            Text(text = "Home screen")
            val list = remember {
                listOf(
                    MovieListType.NOW_PLAYING,
                    MovieListType.TOP_RATED,
                    MovieListType.POPULAR,
                    MovieListType.UPCOMING,
                )
            }

            LazyColumn {
                items(list) {
                    Button(onClick = { viewModel.getMovies(it) }) {
                        Text(text = it.name)
                    }
                }
            }
            LazyColumn {
                items(state.movies) {
                    Text(text = it.title)
                }
            }
        }
    }
}
