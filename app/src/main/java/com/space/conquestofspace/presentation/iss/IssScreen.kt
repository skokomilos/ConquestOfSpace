package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun InternationalSpaceStationScreen(
    viewModel: IssViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        state.iss?.spacestation?.image_url?.let {
            IssDetailsContent(
                scrollState = scrollState,
                imageUrl = it
            )
        }
    }
}

@Composable
private fun IssDetailsContent(
    scrollState: ScrollState,
    imageUrl: String
) {
    Column(Modifier.verticalScroll(scrollState)) {
        ConstraintLayout {
            val (image, fab, info) = createRefs()
            IssImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .constrainAs(image) { top.linkTo(parent.top) }
            )
        }
    }
}

@Composable
private fun IssImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        SpaceImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
