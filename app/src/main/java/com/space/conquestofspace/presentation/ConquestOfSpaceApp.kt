package com.space.conquestofspace.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.space.conquestofspace.presentation.bottom_nav_menu.BottomNavGraph
import com.space.conquestofspace.presentation.bottom_nav_menu.BottomNavigationBar

@Composable
fun ConquestOfSpaceApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        BottomNavGraph(navHostController = navController)
    }
}
