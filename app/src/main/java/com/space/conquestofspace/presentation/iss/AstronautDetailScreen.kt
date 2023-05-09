package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.R
import com.space.conquestofspace.data.remote.dto.astronaut.Agency
import com.space.conquestofspace.data.remote.dto.astronaut.AstronautResponse
import com.space.conquestofspace.data.remote.dto.astronaut.StatusXXX
import com.space.conquestofspace.data.remote.dto.astronaut.TypeX
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import com.space.conquestofspace.viewmodels.AstronautDetailsViewModel

@Composable
fun AstronautDetailScreen(
    viewModel: AstronautDetailsViewModel = hiltViewModel(),
    astronautId: Int
) {
    val state = viewModel.state.value

    ConquestOfSpaceAppTheme {
        Surface() {
            AstronautDetails(state)
        }
    }
}

@Composable
fun AstronautDetails(state: AstronautState) {
    val scrollState = rememberScrollState()
    state.astronaut?.let { astronaut ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            // verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AstronautDetailsContent(
                astronaut = astronaut,
                scrollState = scrollState
            )
        }
    }
    // astronaut id = 546
    // name = Stephen Bowen
    /*
        val agency: Agency,
        val id: Int,
        val name: String,
        val profile_image: String,
        val profile_image_thumbnail: String,
        val status: Status,
        val url: String
     */
}

@Composable
fun AstronautDetailsContent(
    scrollState: ScrollState,
    astronaut: AstronautResponse
) {
    Column(Modifier.verticalScroll(scrollState)) {
        ConstraintLayout() {
            val (image, info, card) = createRefs()

            AstronautImage(
                imageUrl = astronaut.profile_image
            )
            AstronautsData(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .constrainAs(card) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                name = astronaut.name,
                imageUrl = astronaut.profile_image_thumbnail
            )
        }
    }
}

@Composable
fun AstronautsData(modifier: Modifier = Modifier, name: String, imageUrl: String) {
    Card(
        modifier =
        modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Box(
            modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
        ) {
            Column(
                // horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                com.skydoves.landscapist.glide.GlideImage(
                    imageModel = { imageUrl },
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape),
                    previewPlaceholder = R.drawable.astronaut
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                    fontSize = 20.sp
                )
            }
        }
        // Content of the card goes here
    }
}

@Composable
fun AstronautImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
    ) {
        // SpaceImage(model = imageUrl, contentDescription = null, imageHeight = 200.dp)
        FullScreenImage(imageUrl)
    }
}

@Preview(showBackground = true)
@Composable
fun AstronautDetailsPreview() {
    ConquestOfSpaceAppTheme {
        val astronaut = AstronautResponse(
            age = 44,
            agency = Agency(
                id = 1,
                name = "NASA",
                type = "Government",
                url = ""
            ),
            bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            date_of_birth = "1979-07-15",
            date_of_death = "null",
            first_flight = "2009-05-11",
            flights = listOf(),
            flights_count = 1,
            id = 546,
            in_space = true,
            instagram = "null",
            landings = listOf(),
            landings_count = 1,
            last_flight = "null",
            name = "Stephen Bowen",
            nationality = "American",
            profile_image = "",
            profile_image_thumbnail = "null",
            status = StatusXXX(
                id = 1,
                name = "Active"
            ),
            twitter = "null",
            type = TypeX(
                id = 1,
                name = "NASA Astronaut"
            ),
            url = "",
            wiki = "null"
        )
        val state = AstronautState(astronaut = astronaut)
        AstronautDetails(state = state)
    }
}
