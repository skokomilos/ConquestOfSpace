package com.space.conquestofspace.presentation.bottom_nav_menu

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.space.conquestofspace.presentation.Screen
import com.space.conquestofspace.presentation.international_space_station_screen.InternationalSpaceStationScreen
import com.space.conquestofspace.presentation.launch_list.LaunchesListScreen
import com.space.conquestofspace.presentation.third_screen.ThirdScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "home")
    {
        composable("home") {
            Surface(color = MaterialTheme.colors.background) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.LaunchListScreen.route)
                {
                    composable(
                        route = Screen.LaunchListScreen.route
                    ) {
                        LaunchesListScreen(navController)
                    }
                }
            }
        }
        composable("second") {
            InternationalSpaceStationScreen()
        }
        composable("third") {
            ThirdScreen()
        }
    }
}