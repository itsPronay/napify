package com.pronaycoding.blankee.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Material Design 3 Typography system for the Blankee application.
 *
 * This file defines the complete typography scale used throughout the app's Compose UI.
 * All text styles follow Material Design 3 guidelines with appropriate font weights,
 * sizes, line heights, and letter spacing.
 *
 * Typography hierarchy:
 * - Display: Large, decorative text for headlines
 * - Title: For prominent section headings
 * - Body: For body text and regular content
 * - Label: For buttons, labels, and small text
 *
 * @see androidx.compose.material3.MaterialTheme.typography for accessing these styles
 * @see androidx.compose.material3.Text for composable that uses these styles
 */
val Typography = Typography(
    /**
     * Display Large: 32sp, Bold
     * Used for the largest, most prominent headlines.
     */
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    /**
     * Display Medium: 28sp, Bold
     * Used for secondary-level display headlines.
     */
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    /**
     * Title Large: 22sp, Semi-Bold
     * Used for main section titles and primary headings.
     */
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    /**
     * Title Medium: 18sp, Semi-Bold
     * Used for secondary titles and subsection headings.
     */
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    /**
     * Title Small: 16sp, Medium
     * Used for smaller titles and tertiary headings.
     */
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.1.sp
    ),
    /**
     * Body Large: 16sp, Normal
     * Used for main body text and longer content passages.
     */
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /**
     * Body Medium: 14sp, Normal
     * Used for regular body text and content.
     */
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    /**
     * Body Small: 12sp, Normal
     * Used for smaller body text, captions, and secondary content.
     */
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    /**
     * Label Large: 14sp, Semi-Bold
     * Used for button text and prominent labels.
     */
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    /**
     * Label Medium: 12sp, Semi-Bold
     * Used for labels and secondary button text.
     */
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    /**
     * Label Small: 10sp, Semi-Bold
     * Used for small labels, badges, and tiny text elements.
     */
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )
)

