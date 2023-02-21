package com.space.conquestofspace.presentation.bottom_nav_menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        name = "Home",
        route = "home",
        icon = Icons.Default.Home
    )
    object Iss : BottomNavItem(
        name = "Second",
        route = "second",
        icon = Icons.Default.Place
    )
    object Third: BottomNavItem(
        name = "Third",
        route = "third",
        icon = Icons.Default.Email
    )
}
