package com.pronaycoding.blanket_mobile.ui.nav

//import com.pronaycoding.blanket_mobile.ui.homeScreen.MainScreen
import androidx.compose.runtime.Composable
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pronaycoding.blanket_mobile.common.model.CardItems
import com.pronaycoding.blanket_mobile.common.model.DrawerItems
import com.pronaycoding.blanket_mobile.ui.screens.about.AboutScreen
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.Dashboard
import com.pronaycoding.blanket_mobile.ui.screens.settings.SettingsScreen

@Composable
fun Navigation(
    cardItems: List<CardItems>,
    drawerItems: List<DrawerItems>,
    audioPlayer : ExoPlayer
) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.name) {
        composable(Routes.Home.name) {
            Dashboard()
        }

        composable(Routes.Settings.name) {
            SettingsScreen(
                onBackArrowClicked = {
                    navController.navigateUp()
                }
            )
        }

        composable(Routes.AboutUs.name){
            AboutScreen(
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }
    }

}