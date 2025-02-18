package com.pronaycoding.blanket_mobile.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.pronaycoding.blanket_mobile.ui.nav.Routes

/**
 * Created by Pronay Sarker on 12/01/2025 (5:30 PM)
 */
class Utils {

    companion object {

        fun getDrawerItems() : List<DrawerItems> {
            return listOf(
                DrawerItems.Settings,
                DrawerItems.About,
                DrawerItems.Quit
            )
        }
    }
}


sealed class DrawerItems(
    val title: String,
    val icon: ImageVector,
    val route: String = ""
) {
    data object Settings : DrawerItems(
        title = "Settings",
        icon = Icons.Default.Settings,
        route = Routes.Settings.name
    )

    data object About : DrawerItems(
        title = "About",
        icon = Icons.Default.Info,
        route = Routes.AboutUs.name
    )

    data object Quit : DrawerItems(
        title = "Quit",
        icon = Icons.Default.ExitToApp
    )
}