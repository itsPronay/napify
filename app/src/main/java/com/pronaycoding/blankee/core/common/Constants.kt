package com.pronaycoding.blankee.core.common

/**
 * Application-wide constants for the Blankee app.
 *
 * This object centralizes all constant values used throughout the application,
 * including URLs, theme modes, language tags, and configuration thresholds.
 */
object Constants {
    /**
     * GitHub repository URL for Blankee project.
     * Used for linking to the project repository in the UI (e.g., about screen).
     */
    const val GITHUB_REPO = "https://github.com/itsPronay/blankee"

    /**
     * GitHub profile URL for Rafael Mardojai (original Blanket app developer).
     * Used for attribution in about/credits section.
     */
    const val RAFAEL_MARDOJAI_GITHUB = "https://github.com/rafaelmardojai/"

    /**
     * GitHub profile URL for Pronay (Blankee maintainer).
     * Used for attribution and contact information.
     */
    const val PRONAY_GITHUB = "https://github.com/itsPronay/"

    /**
     * Number of app launches before showing the "Enjoy Blankee" prompt.
     * The prompt appears after the user has launched the app this many times,
     * typically to request app store ratings or encourage premium purchases.
     */
    const val ENJOY_PROMPT_TRIGGER_LAUNCH = 3

    // ==================== Theme Modes ====================
    /**
     * Theme mode constant for light theme.
     * Used with PreferenceManager to store/retrieve user's theme preference.
     */
    const val MODE_LIGHT = "light"

    /**
     * Theme mode constant for dark theme.
     * Used with PreferenceManager to store/retrieve user's theme preference.
     */
    const val MODE_DARK = "dark"

    /**
     * Theme mode constant for system theme (follows device settings).
     * Used with PreferenceManager to store/retrieve user's theme preference.
     */
    const val MODE_SYSTEM = "system"

    // ==================== Language Tags (BCP 47) ====================
    /**
     * Language tag constant for system language.
     * Special value indicating the app should use the device's system language.
     */
    const val LANGUAGE_TAG_SYSTEM = "system"

    /**
     * BCP 47 language tag for English.
     */
    const val LANGUAGE_TAG_ENGLISH = "en"

    /**
     * BCP 47 language tag for Hindi.
     */
    const val LANGUAGE_TAG_HINDI = "hi"

    /**
     * BCP 47 language tag for Bengali.
     */
    const val LANGUAGE_TAG_BENGALI = "bn"

    /**
     * BCP 47 language tag for Spanish.
     */
    const val LANGUAGE_TAG_SPANISH = "es"

}

