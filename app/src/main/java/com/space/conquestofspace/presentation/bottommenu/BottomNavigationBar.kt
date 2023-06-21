package com.space.conquestofspace.presentation.bottommenu

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.space.conquestofspace.R
import com.space.conquestofspace.presentation.destinations.AgenciesScreenDestination
import com.space.conquestofspace.presentation.destinations.Destination
import com.space.conquestofspace.presentation.destinations.InternationalSpaceStationScreenDestination
import com.space.conquestofspace.presentation.destinations.LaunchesListScreenDestination

@Composable
fun BottomNavigationBar(
    currentDestination: Destination,
    onBottomNavItemClick: (Direction) -> Unit,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
        BottomNavItem.Launches,
        BottomNavItem.Iss,
        BottomNavItem.Agencies
    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        BottomBarDestination.values().forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination == screen.direction,
//        selected = currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
                onClick = {
                    onBottomNavItemClick(screen.direction)
//            navController.navigate(screen.route) {
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
//            }
                },
                label = {
                    Text(
                        text = screen.name,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.name
                        )
                    }
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray
            )
        }

//        screens.forEach { screen ->
//            AddItem(
//                screen = screen,
//                currentDestination = currentDestination,
//                onBottomNavItemClick = onBottomNavItemClick
//                // currentDestination = currentDestination,
//                // navController = navController
//            )
//        }
    }
}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Launches(LaunchesListScreenDestination, Icons.Default.Home, R.string.greeting_screen),
    Iss(InternationalSpaceStationScreenDestination, Icons.Default.Place, R.string.profile_screen),
    Agencies(AgenciesScreenDestination, Icons.Default.Email, R.string.settings_screen)
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: Destination,
    onBottomNavItemClick: (Direction) -> Unit
    // currentDestination: NavDestination?,
    // navController: NavController
) {
    BottomNavigationItem(
        selected = currentDestination == screen.direction,
//        selected = currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
        onClick = {
            onBottomNavItemClick(screen.direction)
//            navController.navigate(screen.route) {
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
//            }
        },
        label = {
            Text(
                text = screen.name,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        },
        icon = {
            Column(horizontalAlignment = CenterHorizontally) {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = screen.name
                )
            }
        },
        selectedContentColor = Color.Green,
        unselectedContentColor = Color.Gray
    )
}
