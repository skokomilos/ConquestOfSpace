package com.space.conquestofspace.presentation

/**
 *
 * @author berka on 2/5/23
 */
sealed class Screen(val route: String) {
    object LaunchListScreen : Screen("launch_list_screen")
    object LaunchDetailScreen : Screen("launch_detail_screen")
    object IssScreen : Screen("iss_screen")
}
