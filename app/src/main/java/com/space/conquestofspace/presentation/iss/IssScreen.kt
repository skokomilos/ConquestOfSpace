package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.composethemeadapter.MdcTheme
import com.space.conquestofspace.data.remote.dto.iss.Crew
import com.space.conquestofspace.presentation.utils.Dimens

@Composable
fun InternationalSpaceStationScreen(
    viewModel: IssViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Surface {
        IssDetails(state = state)
    }
}

@Composable
private fun IssDetails(state: IssState) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        state.iss?.let { iss ->
            IssDetailsContent(
                scrollState = scrollState,
                name = iss.name,
                imageUrl = iss.spacestation.image_url,
                imageHeight = 278.dp,
                description = iss.spacestation.description,
                iss.crew
            )
        }
    }
}

@Composable
private fun IssDetailsContent(
    scrollState: ScrollState,
    name: String,
    imageUrl: String,
    imageHeight: Dp,
    description: String,
    astronauts: List<Crew>?
) {
    Column(Modifier.verticalScroll(scrollState)) {
        ConstraintLayout {
            val (image, info, crew) = createRefs()
            IssImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .constrainAs(image) { top.linkTo(parent.top) },
                imageHeight = imageHeight
            )

            IssInformation(
                name = name,
                description = description,
                modifier = Modifier.constrainAs(info) { top.linkTo(image.bottom) }
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
        modifier = modifier.padding(
            start = 24.dp,
            end = 24.dp
        )
    ) {
        items(crew) { crewMember ->
            AstronautItem(
                imageUrl = crewMember.astronaut.profile_image,
                astronautName = crewMember.astronaut.name
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
        Text(
            text = astronautName,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
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
                scrollState = rememberScrollState(),
                name = "name",
                imageUrl = "image_url",
                imageHeight = 278.dp,
                description = "The International Space Station (ISS) is a habitable artificial satellite in low Earth orbit that has been continuously inhabited since 2000. It is the largest human-made body in low Earth orbit and has various components, including habitation modules, solar arrays, and experiment bays. The station is expected to operate until 2030 and is regularly visible from Earth.",
                null
            )
        }
    }
}
