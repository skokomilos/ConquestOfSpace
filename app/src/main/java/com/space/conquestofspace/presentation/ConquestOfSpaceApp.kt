package com.space.conquestofspace.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.space.conquestofspace.presentation.bottommenu.BottomNavGraph
import com.space.conquestofspace.presentation.bottommenu.BottomNavigationBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConquestOfSpaceApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        BottomNavGraph(navHostController = navController)
    }
}
