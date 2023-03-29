package com.space.conquestofspace.presentation.iss

import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.themeadapter.material.MdcTheme
import com.space.conquestofspace.R
import com.space.conquestofspace.data.remote.dto.iss.Crew
import com.space.conquestofspace.presentation.toolbar.CollapsingToolbar
import com.space.conquestofspace.presentation.toolbar.FixedScrollFlagState
import com.space.conquestofspace.presentation.toolbar.ToolbarState
import com.space.conquestofspace.presentation.toolbar.scrollflags.ScrollState
import com.space.conquestofspace.presentation.utils.Dimens
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Composable
fun InternationalSpaceStationScreen(
    viewModel: IssViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Surface {
        IssDetails(state = state)
    }
}

private val MinToolbarHeight = 96.dp
private val MaxToolbarHeight = 176.dp

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(toolbarHeightRange)
    }
}

@Composable
private fun IssDetails(state: IssState) {
    val scrollState = rememberScrollState()

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)

    val scope = rememberCoroutineScope()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                toolbarState.scrollOffset = toolbarState.scrollOffset - available.y
                return Offset(0f, toolbarState.consumed)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (available.y > 0) {
                    scope.launch {
                        animateDecay(
                            initialValue = toolbarState.height + toolbarState.offset,
                            initialVelocity = available.y,
                            animationSpec = FloatExponentialDecaySpec()
                        ) { value, velocity ->
                            toolbarState.scrollOffset = toolbarState.scrollOffset - (value - (toolbarState.height + toolbarState.offset))
                            if (toolbarState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
                        }
                    }
                }

                return super.onPostFling(consumed, available)
            }
        }
    }

    Box(
        modifier = Modifier.nestedScroll(nestedScrollConnection)
    ) {
        state.iss?.let { iss ->
            CollapsingToolbar(
                progress = toolbarState.progress,
                backgroundImageId = R.mipmap.iss_foreground,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                    .graphicsLayer { translationY = toolbarState.offset }
            )
            IssDetailsContent(
                name = iss.name,
                imageUrl = iss.spacestation.image_url,
                imageHeight = 278.dp,
                description = iss.spacestation.description,
                iss.crew,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { translationY = toolbarState.height + toolbarState.offset }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { scope.coroutineContext.cancelChildren() }
                        )
                    },
                contentPadding = PaddingValues(
                    bottom = if (toolbarState is FixedScrollFlagState) MinToolbarHeight else 0.dp
                )
            )
        }
    }
}

@Composable
private fun IssDetailsContent(
    name: String,
    imageUrl: String,
    imageHeight: Dp,
    description: String,
    astronauts: List<Crew>?,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        item {
            ConstraintLayout {
                val (info, crew) = createRefs()
//            IssImage(
//                imageUrl = imageUrl,
//                modifier = Modifier
//                    .constrainAs(image) { top.linkTo(parent.top) },
//                imageHeight = imageHeight
//            )

                IssInformation(
                    name = name,
                    description = description,
                    modifier = Modifier.constrainAs(info) { top.linkTo(parent.top) }
                )

                crew.let {
                    astronauts?.let { it1 ->
                        IssCrew(
                            crew = it1,
                            modifier = Modifier.constrainAs(crew) { top.linkTo(info.bottom) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun IssInformation(
    name: String,
    modifier: Modifier = Modifier,
    description: String
) {
    Column(
        modifier = modifier
            .padding(Dimens.PaddingLarge)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .onGloballyPositioned { },
            textAlign = TextAlign.Center

        )
        IssDescription(description)
    }
}

@Composable
private fun IssDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun IssCrew(
    crew: List<Crew>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(bottom = 220.dp)
    ) {
        items(crew) { crewMember ->
            AstronautItem(
                imageUrl = crewMember.astronaut.profile_image,
                astronautName = crewMember.astronaut.name,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun AstronautItem(
    imageUrl: String,
    astronautName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PersonImage(
            model = imageUrl,
            contentDescription = null
        )
//        Text(
//            text = astronautName,
//            style = MaterialTheme.typography.h3,
//            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
//        )
    }
}

@Composable
private fun IssImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    imageHeight: Dp,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        SpaceImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            imageHeight = imageHeight
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun InternationalSpaceStationScreenPreview() {
    MdcTheme {
        Surface {
            IssDetailsContent(
                name = "name",
                imageUrl = "image_url",
                imageHeight = 278.dp,
                description = "The International Space Station (ISS) is a habitable artificial satellite in low Earth orbit that has been continuously inhabited since 2000. It is the largest human-made body in low Earth orbit and has various components, including habitation modules, solar arrays, and experiment bays. The station is expected to operate until 2030 and is regularly visible from Earth.",
                null
            )
        }
    }
}
