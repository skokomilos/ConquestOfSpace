package com.space.conquestofspace.presentation.agencies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalFoundationApi
@Destination
@Composable
fun AgenciesScreen(
    viewModel: AgenciesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(state.agencies) { agency ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(1f)
                            .background(Color.Magenta),
                        contentAlignment = Alignment.Center
                    ) {
                        AgencyListItem(agency = agency)
                    }
                }
            }
        )
    }
}
