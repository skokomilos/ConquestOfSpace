package com.space.conquestofspace.presentation.bottommenu

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
//    DestinationsNavHost(
//        navGraph = NavGraphs.root
//    )
//    NavHost(
//        navController = navHostController,
//        startDestination = BottomNavItem.Launches.route
//    ) {
//        composable(BottomNavItem.Launches.route) {
//            LaunchesListScreen()
//        }
//        composable(BottomNavItem.Iss.route) {
//            InternationalSpaceStationScreen(
//                onAstronautClick = {
//                    navHostController.navigate(Graph.DETAILSX)
//                }
//            )
//        }
//        composable(BottomNavItem.Third.route) {
//            ThirdScreen()
//        }
//        issDetailsNavGraph(navController = navHostController)
//    }
}

sealed class IssNestedScreen(val route: String) {
    object AstronautScreen : IssNestedScreen(route = "astronaut_screen")
}
