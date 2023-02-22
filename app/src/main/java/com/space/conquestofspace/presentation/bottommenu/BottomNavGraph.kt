package com.space.conquestofspace.presentation.bottommenu

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.space.conquestofspace.presentation.iss.InternationalSpaceStationScreen
import com.space.conquestofspace.presentation.launches.LaunchesListScreen
import com.space.conquestofspace.presentation.thirdscreen.ThirdScreen

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
