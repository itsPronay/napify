package com.pronaycoding.blanket_mobile.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4A148C),  // Muted Purple
    secondary = Color(0xFF00796B),  // Muted Teal
    tertiary = Color(0xFF80CBC4),  // Soft Mint Green
    background = Color(0xFF121212),  // Very Dark Grey for background
    surface = Color(0xFF1C1B1F),  // Dark surface color for cards and other surfaces
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color(0xFF1C1B1F),  // Dark for tertiary elements
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF673AB7),  // Soft Purple
    secondary = Color(0xFF26A69A),  // Calm Teal
    tertiary = Color(0xFF80CBC4),  // Soft Mint Green
    background = Color(0xFFF0F0F0),  // Light Grey for background
    surface = Color(0xFFFFFFFF),  // White surface for cards
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color(0xFF1C1B1F),  // Dark text on tertiary elements
    onBackground = Color(0xFF1C1B1F),  // Dark text on light background
    onSurface = Color(0xFF1C1B1F)  // Dark text on white surfaces
)


@Composable
fun NapifyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}