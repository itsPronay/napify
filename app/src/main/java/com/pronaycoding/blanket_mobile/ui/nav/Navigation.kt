package com.pronaycoding.blanket_mobile.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pronaycoding.blanket_mobile.ui.screens.about.AboutScreen
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.HomeScreenRoute

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.name) {
        composable(Routes.Home.name) {
            HomeScreenRoute(
                navigateToAboutScreen = { navController.navigate(Routes.AboutUs.name) }
            )
        }

        composable(Routes.AboutUs.name) {
            AboutScreen(
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }
    }
}