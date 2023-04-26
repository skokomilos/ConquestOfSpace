package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.data.remote.dto.iss.Astronaut
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
    state.astronaut?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Milos Skoko",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
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
    val scrollState = rememberScrollState()
//    AstronautDetailsContent(
//        astronaut = astronaut,
//        scrollState = scrollState
//    )
}

@Composable
fun AstronautDetailsContent(
    scrollState: ScrollState,
    astronaut: Astronaut
) {
    Column(Modifier.verticalScroll(scrollState)) {
        ConstraintLayout() {
            val (image, info) = createRefs()

            AstronautImage(
                imageUrl = astronaut.profile_image

            )
        }
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
        SpaceImage(model = imageUrl, contentDescription = null, imageHeight = 200.dp)
    }
}
