package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SpaceImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    imageHeight: Dp,
    imageResId: Int? = null
) {
    if (LocalInspectionMode.current) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Green)
                .height(imageHeight)
        )
        return
    }
    GlideImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PersonImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    GlideImage(
        model = model,
        contentDescription = null,
        modifier = Modifier
            .size(88.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}
