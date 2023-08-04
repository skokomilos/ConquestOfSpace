package com.space.conquestofspace.presentation.agencies.detail

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme

/**
 *
 * @author berka on 7/24/23
 */
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
    println("agencyName ${state.agency?.name}")
}
