package com.space.conquestofspace.presentation.bottommenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.space.conquestofspace.presentation.destinations.AgenciesScreenDestination
import com.space.conquestofspace.presentation.destinations.InternationalSpaceStationScreenDestination
import com.space.conquestofspace.presentation.destinations.LaunchesListScreenDestination

sealed class BottomNavItem(
    val direction: DirectionDestinationSpec,
    val name: String,
    val route: String,
    val icon: ImageVector
) {
    object Launches : BottomNavItem(
        direction = LaunchesListScreenDestination,
        name = "Launches",
        route = "launches",
        icon = Icons.Default.Home
    )
    object Iss : BottomNavItem(
        InternationalSpaceStationScreenDestination,
        name = "Iss",
        route = "iss",
        icon = Icons.Default.Place
    )
    object Agencies : BottomNavItem(
        AgenciesScreenDestination,
        name = "Agencies",
        route = "agencies",
        icon = Icons.Default.Email
    )
}
