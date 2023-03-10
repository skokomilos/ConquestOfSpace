package com.space.conquestofspace.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.space.conquestofspace.R

object Dimens {

    val PaddingSmall: Dp
        @Composable get() = dimensionResource(R.dimen.margin_small)

    val PaddingNormal: Dp
        @Composable get() = dimensionResource(R.dimen.margin_normal)

    val PaddingLarge: Dp = 24.dp

    val PlantDetailAppBarHeight: Dp
        @Composable get() = dimensionResource(R.dimen.detail_app_bar_height)

    val ToolbarIconPadding = 12.dp

    val ToolbarIconSize = 32.dp
}
