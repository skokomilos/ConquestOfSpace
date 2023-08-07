package com.space.conquestofspace.presentation.agencies.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.presentation.iss.SpaceImage
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme

/**
 *
 * @author berka on 7/24/23
 */
private val MinTitleOffset = 56.dp
private val GradientScroll = 180.dp

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
        Image(state)
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
fun Image(state: AgencyDetailState) {
    SpaceImage(model = state.agency?.image_url, contentDescription = "", imageHeight = 120.dp)
}

@Composable
fun Title(s1: Int, s: String) {
}
