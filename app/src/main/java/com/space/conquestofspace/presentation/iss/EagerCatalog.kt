package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EagerCatalog(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(contentPadding.calculateTopPadding())
        )
    }
}
