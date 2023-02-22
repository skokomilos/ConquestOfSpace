package com.space.conquestofspace.presentation.iss

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SpaceImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    GlideImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier
    )
}
