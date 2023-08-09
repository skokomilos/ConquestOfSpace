package com.space.conquestofspace.presentation.agencies.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.presentation.iss.CircleImage
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import kotlin.math.max
import kotlin.math.min
/**
 *
 * @author berka on 7/24/23
 */
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
        Title(scroll.value, "Neki naslov")
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

    CollapsingImageLayout(
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
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()
        val imageMaxSize = min(ExpandableImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
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
fun Title(s1: Int, s: String) {
}
