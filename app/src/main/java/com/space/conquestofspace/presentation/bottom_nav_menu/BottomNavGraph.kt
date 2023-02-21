package com.space.conquestofspace.presentation.bottom_nav_menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.space.conquestofspace.presentation.international_space_station_screen.InternationalSpaceStationScreen
import com.space.conquestofspace.presentation.launch_list.LaunchesListScreen
import com.space.conquestofspace.presentation.third_screen.ThirdScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.Launches.route
    ) {
        composable(BottomNavItem.Launches.route) {
            LaunchesListScreen()
        }
        composable(BottomNavItem.Iss.route) {
            InternationalSpaceStationScreen()
        }
        composable(BottomNavItem.Third.route) {
            ThirdScreen()
        }
    }
}
