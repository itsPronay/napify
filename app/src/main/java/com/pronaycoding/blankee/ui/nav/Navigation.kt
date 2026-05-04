package com.pronaycoding.blankee.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pronaycoding.blankee.ui.screens.homeScreen.HomeScreenRoute
import com.pronaycoding.blankee.ui.screens.settings.SettingsScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.name) {
        composable(Routes.Home.name) {
            HomeScreenRoute(
                navigateToSettings = { navController.navigate(Routes.Settings.name) }
            )
        }

        composable(Routes.Settings.name) {
            SettingsScreen(
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}