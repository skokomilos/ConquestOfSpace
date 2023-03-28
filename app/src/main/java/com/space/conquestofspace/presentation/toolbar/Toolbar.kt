package com.space.conquestofspace.presentation.toolbar

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.space.conquestofspace.R
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import kotlin.math.roundToInt

@Preview
@Composable
fun CollapsingToolbarPreview() {
    ConquestOfSpaceAppTheme {
        CollapsingToolbar(
            progress = 0f,
            backgroundImageId = R.mipmap.glx_foreground,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
    }
}

@Composable
fun CollapsingToolbar(
    progress: Float,
    @DrawableRes backgroundImageId: Int,
    modifier: Modifier = Modifier
) {
    val headlineOneHeight = with(LocalDensity.current) {
        lerp(16.dp.toPx(), 20.dp.toPx(), progress).toDp()
    }

    val headlineTwoHeight = with(LocalDensity.current) {
        lerp(12.dp.toPx(), 12.dp.toPx(), progress).toDp()
    }

    val logoPadding = with(LocalDensity.current) {
        lerp(3.dp.toPx(), 1.dp.toPx(), progress).toDp()
    }

    Surface(
        color = MaterialTheme.colors.primary,
        elevation = 4.dp,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = backgroundImageId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = progress * 0.95f
                    }
                //  alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            )
        }

        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {
            CollapsingToolbarLayout(progress = progress) {
                Text(
                    text = "International",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(logoPadding)
                        .height(32.dp)
                        .wrapContentWidth()
                    // .graphicsLayer { alpha = ((0.25f - progress) * 4).coerceIn(0f, 1f) }
                )
                Text(
                    text = "Space",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(logoPadding)
                        .height(32.dp)
                        .wrapContentWidth()
                )
                Text(
                    text = "Station",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(logoPadding)
                        .height(32.dp)
                        .wrapContentWidth()
                )
//                Image(
//                    painter = painterResource(id = R.drawable.baseline_rocket_launch_24),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(logoPadding)
//                        .height(32.dp)
//                        .wrapContentWidth()
//                        .graphicsLayer { alpha = ((0.25f - progress) * 4).coerceIn(0f, 1f) },
//                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.baseline_cloud_24),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(logoPadding)
//                        .height(headlineOneHeight)
//                        .wrapContentWidth(),
//                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.baseline_star_24),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(logoPadding)
//                        .height(headlineOneHeight)
//                        .wrapContentWidth(),
//                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
//                )
            }
        }
    }
}

@Composable
private fun CollapsingToolbarLayout(
    progress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 3) // [0]: Country Map | [1-3]: Logo Images | [4]: Buttons

        val placeables = measurables.map {
            it.measure(constraints)
        }
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            val expandedHorizontalGuideline = (constraints.maxHeight * 0.4f).roundToInt()
            val collapsedHorizontalGuideline = (constraints.maxHeight * 0.5f).roundToInt()
            Log.d(
                "TAG",
                "CollapsingToolbarLayout: $expandedHorizontalGuideline and $collapsedHorizontalGuideline"
            )

            val international = placeables[0]
            val space = placeables[1]
            val station = placeables[2]

            international.placeRelative(
                x = lerp(
                    start = 0,
                    stop = constraints.maxWidth / 2 - international.width,
                    fraction = progress
                ),
                y = lerp(
                    start = collapsedHorizontalGuideline - international.height / 2,
                    stop = expandedHorizontalGuideline - station.height,
                    fraction = progress
                )
            )
            space.placeRelative(
                x = lerp(
                    start = international.width,
                    stop = constraints.maxWidth / 2 - international.width,
                    fraction = progress
                ),
                y = lerp(
                    start = collapsedHorizontalGuideline - international.height / 2,
                    stop = expandedHorizontalGuideline,
                    fraction = progress
                )
            )
            station.placeRelative(
                x = lerp(
                    start = international.width + space.width,
                    stop = constraints.maxWidth / 2 - international.width,
                    fraction = progress
                ),
                y = lerp(
                    start = collapsedHorizontalGuideline - station.height / 2,
                    stop = expandedHorizontalGuideline + international.height,
                    fraction = progress
                )
            )
        }
    }
}
