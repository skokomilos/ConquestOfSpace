package com.space.conquestofspace.presentation.agencies.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.R
import com.space.conquestofspace.data.remote.responses.agencies.Agency
import com.space.conquestofspace.presentation.iss.CircleImage
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import kotlin.math.max
import kotlin.math.min

/**
 *
 * @author berka on 7/24/23
 */
private val TitleHeight = 128.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val ExpandableImageSize = 300.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun AgencyDetailScreen(
    viewModel: AgencyDetailViewModel = hiltViewModel()
) {
    val agency = viewModel.state.value
    ConquestOfSpaceAppTheme {
        Surface {
            AgencyDetailContent(agency)
        }
    }
}

@Composable
fun AgencyDetailContent(state: AgencyDetailState) {
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(scroll)
        Title(state.agency) { scroll.value }
        Image(state.agency?.image_url) { scroll.value }
    }
}

@Composable
fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xff7057f5), Color(0xff86f7fa))
                )
            )
    )
}

@Composable
private fun Body(scroll: ScrollState) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(115.dp))
                    Spacer(Modifier.height(128.dp))
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Details",
                        style = MaterialTheme.typography.overline,
                        color = MaterialTheme.colors.primary
                    )
                    Spacer(Modifier.height(16.dp))
                    var seeMore by remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(id = R.string.dummy_large_text),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        maxLines = if (false) 5 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(400.dp))
                }
            }
        }
    }
}

@Composable
fun Image(imageUrl: String?, scrollProvider: () -> Int) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    var rotationAngle by remember { mutableStateOf(0f) }

    val maxRotation = 90f // Maximum rotation angle
    val maxScroll = 1000 // Scroll position at which rotation should stop

    val currentScroll = scrollProvider()

    if (currentScroll <= maxScroll) {
        rotationAngle = currentScroll.toFloat() * 0.1f
    } else {
        rotationAngle = maxRotation
    }

    CollapsingImageLayout(
        scrollProvider,
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding())

    ) {
        CircleImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun CollapsingImageLayout(
    scrollProvider: () -> Int,
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val collapseFraction = collapseFractionProvider()
    val maxRotation = collapseFraction * 360f // Adjust the maximum rotation angle here

    val rotationAngle = if (collapseFraction < 1f) {
        collapseFraction * maxRotation
    } else {
        maxRotation
    }
    Layout(
        modifier = modifier.rotate(rotationAngle),
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()
        val imageMaxSize = min(ExpandableImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(
            start = MinTitleOffset,
            stop = MinImageOffset,
            fraction = collapseFraction
        ).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2,
            constraints.maxWidth - imageWidth,
            collapseFraction
        )
        layout(
            width = constraints.minWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

@Composable
fun Title(agency: Agency?, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = MaterialTheme.colors.background)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Headline H1",
            modifier = HzPadding
        )
    }
}
