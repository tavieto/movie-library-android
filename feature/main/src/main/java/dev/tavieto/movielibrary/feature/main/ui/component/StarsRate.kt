package dev.tavieto.movielibrary.feature.main.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StarsRate(
    value: Float,
    modifier: Modifier = Modifier,
    maxValue: Float = 10f
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StarsProgress(value = value, maxValue = maxValue)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "$value/$maxValue")
    }
}

@Composable
private fun StarsProgress(
    value: Float,
    maxValue: Float = 10f
) {
    val progressPercent by remember(value, maxValue) {
        mutableStateOf(value.times(100).div(maxValue))
    }
    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = Modifier.wrapContentWidth()
    ) {
        var referenceWidth by remember {
            mutableStateOf(0.dp)
        }

        val width by animateDpAsState(
            targetValue = referenceWidth.times(progressPercent).div(100),
            animationSpec = tween(durationMillis = 2000)
        )

        Stars(
            color = Color.Gray,
            modifier = Modifier.onSizeChanged {
                referenceWidth = with(density) { it.width.toDp() }
            }
        )

        Stars(
            color = if (isSystemInDarkTheme()) Color.Yellow else Color(color = 0xFFFFAB00),
            modifier = Modifier
                .clipToBounds()
                .width(width)
        )
    }
}

@Composable
private fun Stars(
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentWidth(Alignment.Start, unbounded = true),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) {
            Icon(
                modifier = Modifier
                    .defaultMinSize(24.dp)
                    .size(24.dp),
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = color
            )
        }
    }
}

@Preview
@Composable
fun StartsRatePreview() {
    StarsRate(
        modifier = Modifier,
        value = 7f
    )
}
