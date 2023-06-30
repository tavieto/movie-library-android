package dev.tavieto.movielibrary.feature.main.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieItem(
    moviePosterPath: String,
    height: Dp,
    modifier: Modifier = Modifier,
) {
    val width by remember(height) {
        mutableStateOf(height.div(4).times(2.5f))
    }
    val density = LocalDensity.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        GlideImage(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(10.dp)),
            imageModel = moviePosterPath,
            contentScale = ContentScale.FillHeight,
            requestOptions = {
                with(density) {
                    RequestOptions()
                        .override(
                            height.toPx().toInt(),
                            width.toPx().toInt()
                        )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}