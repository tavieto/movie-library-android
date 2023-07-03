package dev.tavieto.movielibrary.feature.main.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.tavieto.movielibrary.core.commons.extension.formatDate
import dev.tavieto.movielibrary.feature.main.model.MovieModel
import dev.tavieto.movielibrary.feature.main.ui.component.MovieItem
import dev.tavieto.movielibrary.feature.main.ui.component.StarsRate

@Composable
fun DetailsScreen(
    movie: MovieModel,
    viewModel: DetailsViewModel
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        viewModel.navigateBack()
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }
                IconButton(
                    onClick = {
                        viewModel.updateFavoriteMovie()
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = null)
                    AnimatedVisibility(
                        visible = state.movie?.isFavorite == true,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = state.movie != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MovieItem(moviePosterPath = state.movie!!.posterPath, height = 200.dp)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 30.dp),
                            text = state.movie!!.title,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        StarsRate(
                            value = state.movie!!.voteAverage.toFloat()
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Data de lançamento", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.movie!!.releaseDate.formatDate(
                            to = stringResource(id = com.raptor.sports.core.R.string.date_pattern)
                        ),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Sinopse", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.movie!!.overview,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = state.isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CircularProgressIndicator()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setMovie(movie)
    }
}
