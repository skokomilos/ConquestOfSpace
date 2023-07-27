package com.space.conquestofspace.presentation.agencies.detail

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.space.conquestofspace.presentation.agencies.AgenciesViewModel

/**
 *
 * @author berka on 7/24/23
 */
@Composable
@Destination
fun AgencyDetailScreen(
    viewModel: AgenciesViewModel,
) {
    Surface() {
        Text("Agency Detail")
    }
}
