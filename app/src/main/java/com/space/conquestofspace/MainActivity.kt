package com.space.conquestofspace

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.space.conquestofspace.presentation.Screen
import com.space.conquestofspace.presentation.launch_list.LaunchesListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
    }

}
