package com.space.conquestofspace

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.space.conquestofspace.presentation.Screen
import com.space.conquestofspace.presentation.bottom_nav_menu.BottomNavItem
import com.space.conquestofspace.presentation.bottom_nav_menu.BottomNavigationBar
import com.space.conquestofspace.presentation.launch_list.LaunchesListScreen
import com.space.conquestofspace.presentation.second_screen.SecondScreen
import com.space.conquestofspace.presentation.third_screen.ThirdScreen
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConquestOfSpaceAppTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Home",
                            route = "home",
                            icon = Icons.Default.Home
                        ),
                        BottomNavItem(
                            name = "Second",
                            route = "second",
                            icon = Icons.Default.Place
                        ),
                        BottomNavItem(
                            name = "Third",
                            route = "third",
                            icon = Icons.Default.Email
                        )
                    ),
                    navController = navController,
                    onItemClick = { navController.navigate(it.route) }
                )}) {
                    Navigation(navHostController = navController)
                }
            }
           /* Surface(color = MaterialTheme.colors.background) {
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
            }*/
        }
    }
    @Composable
    fun Navigation(navHostController: NavHostController) {
        NavHost(navController = navHostController, startDestination = "home") {
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
                SecondScreen()
            }
            composable("third") {
                ThirdScreen()
            }
        }
    }

}
