package com.pronaycoding.blanket_mobile.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.pronaycoding.blanket_mobile.ui.nav.Routes

sealed class DrawerItems(
    val title: String,
    val icon: ImageVector,
    val route: String = ""
) {
    data object Home : DrawerItems(
        title = "Home",
        icon = Icons.Default.Home,
        route = Routes.Home.name
    )

    data object SourceCode : DrawerItems(
        title = "Source code",
        icon = Icons.Filled.Build
    )

    data object About : DrawerItems(
        title = "About",
        icon = Icons.Default.Info,
        route = Routes.AboutUs.name
    )

    data object RequestFeature : DrawerItems(
        title = "Request feature",
        icon = Icons.Default.MailOutline
    )

    data object Quit : DrawerItems(
        title = "Quit",
        icon = Icons.Default.ExitToApp
    )

    data object Settings : DrawerItems(
        title = "Settings",
        icon = Icons.Default.Settings,
        route = Routes.Settings.name
    )


}