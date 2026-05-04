package com.pronaycoding.blankee.core.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Material Design 3 Color Palette for the Blankee application.
 *
 * The color scheme is designed to evoke calm and relaxation, fitting the app's sleep/ambient sound purpose.
 * Colors are organized into:
 * - Primary: Soft blues for main branding and interactive elements
 * - Secondary: Cyan tones for secondary accents
 * - Tertiary: Soft greens for tertiary accents
 * - Neutral: Greys, whites, and background colors for light/dark modes
 * - Status: Error, success, and warning colors for user feedback
 *
 * @see androidx.compose.material3.MaterialTheme.colorScheme for accessing these colors
 */

// Primary Colors - Soft, calming blues and teals
/**
 * Primary brand color: Soft Blue (#1E88E5)
 * Used for primary interactive elements, buttons, and key UI components.
 */
@Suppress("unused")
val Primary = Color(0xFF1E88E5)

/**
 * Primary Dark: Darker blue shade (#1565C0)
 * Used for pressed/selected states of primary elements.
 */
@Suppress("unused")
val PrimaryDark = Color(0xFF1565C0)

/**
 * Primary Light: Lighter blue shade (#64B5F6)
 * Used for primary container backgrounds and soft highlights.
 */
@Suppress("unused")
val PrimaryLight = Color(0xFF64B5F6)

// Secondary Colors - Warm teal/cyan
/**
 * Secondary brand color: Cyan (#26C6DA)
 * Used for secondary interactive elements and accents.
 */
@Suppress("unused")
val Secondary = Color(0xFF26C6DA)

/**
 * Secondary Dark: Darker cyan shade (#00ACC1)
 * Used for pressed/selected states of secondary elements.
 */
@Suppress("unused")
val SecondaryDark = Color(0xFF00ACC1)

/**
 * Secondary Light: Lighter cyan shade (#80DEEA)
 * Used for secondary container backgrounds.
 */
@Suppress("unused")
val SecondaryLight = Color(0xFF80DEEA)

// Tertiary - Soft green accent
/**
 * Tertiary accent color: Soft Green (#4CAF50)
 * Used for tertiary interactive elements and success indicators.
 */
@Suppress("unused")
val Tertiary = Color(0xFF4CAF50)

/**
 * Tertiary Light: Lighter green shade (#81C784)
 * Used for tertiary container backgrounds.
 */
@Suppress("unused")
val TertiaryLight = Color(0xFF81C784)

// Neutral - Greys and whites
/**
 * Dark background: Almost black (#0F0F0F)
 * Primary background color for dark theme.
 */
@Suppress("unused")
val DarkBackground = Color(0xFF0F0F0F)

/**
 * Dark surface: Dark grey (#1A1A1A)
 * Surface/card background color for dark theme.
 */
@Suppress("unused")
val DarkSurface = Color(0xFF1A1A1A)

/**
 * Dark surface variant: Slightly lighter grey (#2D2D2D)
 * Elevation/depth surface for dark theme.
 */
@Suppress("unused")
val DarkSurfaceVariant = Color(0xFF2D2D2D)

/**
 * Light background: Off-white (#FAFAFA)
 * Primary background color for light theme.
 */
@Suppress("unused")
val LightBackground = Color(0xFFFAFAFA)

/**
 * Light surface: Pure white (#FFFFFF)
 * Surface/card background color for light theme.
 */
@Suppress("unused")
val LightSurface = Color(0xFFFFFFFF)

/**
 * Light surface variant: Light grey (#F5F5F5)
 * Elevation/depth surface for light theme.
 */
@Suppress("unused")
val LightSurfaceVariant = Color(0xFFF5F5F5)

// Text Colors
/**
 * Dark text color: Near-black (#1C1B1F)
 * Primary text color for light backgrounds.
 */
@Suppress("unused")
val DarkText = Color(0xFF1C1B1F)

/**
 * Light text color: Off-white (#FFFBFE)
 * Primary text color for dark backgrounds.
 */
@Suppress("unused")
val LightText = Color(0xFFFFFBFE)

/**
 * Secondary text color: Medium grey (#79747E)
 * Used for secondary text and disabled elements across both themes.
 */
@Suppress("unused")
val TextSecondary = Color(0xFF79747E)

// Error/Status
/**
 * Error color: Red (#EF5350)
 * Used for error messages, validation failures, and destructive actions.
 */
@Suppress("unused")
val Error = Color(0xFFEF5350)

/**
 * Success color: Green (#66BB6A)
 * Used for success messages and positive confirmations.
 */
@Suppress("unused")
val Success = Color(0xFF66BB6A)

/**
 * Warning color: Amber (#FFB74D)
 * Used for warning messages and cautionary alerts.
 */
@Suppress("unused")
val Warning = Color(0xFFFFB74D)

