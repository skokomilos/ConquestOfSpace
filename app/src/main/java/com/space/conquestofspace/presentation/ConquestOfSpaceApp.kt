package com.space.conquestofspace.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.manualcomposablecalls.ManualComposableCallsBuilder
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.navigate
import com.space.conquestofspace.presentation.bottommenu.BottomNavigationBar
import com.space.conquestofspace.presentation.destinations.Destination
import com.space.conquestofspace.presentation.destinations.LaunchesListScreenDestination
import com.space.conquestofspace.presentation.launches.LaunchesListScreen
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ConquestOfSpaceApp() {
    ConquestOfSpaceAppTheme {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberAnimatedNavController()

        ConquestOfSpaceScaffold(
            navController = navController,
            scaffoldState = scaffoldState,
            bottomBar = { destination ->
                BottomNavigationBar(
                    currentDestination = destination,
                    onBottomNavItemClick = {
                        navController.navigate(it) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) { paddingValues ->
            AppNavigation(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navHostEngine = rememberAnimatedNavHostEngine()
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navController,
        modifier = modifier
    ) {
        launchesScreen()
    }
}

private fun ManualComposableCallsBuilder.launchesScreen() {
    composable(LaunchesListScreenDestination) {
        LaunchesListScreen(
            navigator = destinationsNavigator
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun ConquestOfSpaceScaffold(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    bottomBar: @Composable (Destination) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(16.dp)
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { bottomBar(destination) },
            content = content
        )
    }
}
