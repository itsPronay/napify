package com.pronaycoding.blankee.core.ui.theme

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
import com.pronaycoding.blankee.core.datastore.PreferenceManagerRepositoryImpl

// Dark theme - Calm and minimalist like Blanket app
private val DarkColorScheme =
    darkColorScheme(
        primary = Primary, // Soft Blue
        onPrimary = Color.White,
        primaryContainer = PrimaryDark,
        onPrimaryContainer = Color.White,
        secondary = Secondary, // Cyan
        onSecondary = Color.White,
        secondaryContainer = SecondaryDark,
        onSecondaryContainer = Color.White,
        tertiary = Tertiary, // Soft Green
        onTertiary = Color.White,
        tertiaryContainer = TertiaryLight,
        onTertiaryContainer = Color.White,
        error = Error,
        onError = Color.White,
        errorContainer = Color(0xFFF9DEDC),
        onErrorContainer = Color(0xFF410E0B),
        background = DarkBackground, // Almost black
        onBackground = LightText,
        surface = DarkSurface, // Dark card background
        onSurface = LightText,
        surfaceVariant = DarkSurfaceVariant,
        onSurfaceVariant = TextSecondary,
        outline = Color(0xFF79747E),
        outlineVariant = Color(0xFF49454E),
    )

// Light theme
private val LightColorScheme =
    lightColorScheme(
        primary = Primary, // Soft Blue
        onPrimary = Color.White,
        primaryContainer = PrimaryLight,
        onPrimaryContainer = Color(0xFF062D5E),
        secondary = Secondary, // Cyan
        onSecondary = Color.White,
        secondaryContainer = SecondaryLight,
        onSecondaryContainer = Color(0xFF003D47),
        tertiary = Tertiary, // Soft Green
        onTertiary = Color.White,
        tertiaryContainer = TertiaryLight,
        onTertiaryContainer = Color.White,
        error = Error,
        onError = Color.White,
        errorContainer = Color(0xFFFCDEDB),
        onErrorContainer = Color(0xFF370B1E),
        background = LightBackground, // Off-white
        onBackground = DarkText,
        surface = LightSurface, // Pure white
        onSurface = DarkText,
        surfaceVariant = LightSurfaceVariant,
        onSurfaceVariant = TextSecondary,
        outline = Color(0xFF79747E),
        outlineVariant = Color(0xFFCAC7D0),
    )

@Composable
fun BlankeeAppTheme(
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled for consistent branding
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val preferenceManager = PreferenceManagerRepositoryImpl(context)
    val systemDark = isSystemInDarkTheme()
    val darkTheme =
        when (preferenceManager.getThemeModeBlocking(PreferenceManagerRepositoryImpl.MODE_DARK)) {
            PreferenceManagerRepositoryImpl.MODE_LIGHT -> false
            PreferenceManagerRepositoryImpl.MODE_DARK -> true
            PreferenceManagerRepositoryImpl.MODE_SYSTEM -> systemDark
            else -> true
        }

    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    val view = LocalView.current
    if (!view.isInEditMode) {
        @Suppress("DEPRECATION")
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view)!!.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
