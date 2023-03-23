package com.space.conquestofspace.presentation.iss

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

private val MinToolbarHeight = 96.dp
private val MaxToolbarHeight = 176.dp

@Composable
fun Catalog(
    modifier: Modifier = Modifier
) {
    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
}
