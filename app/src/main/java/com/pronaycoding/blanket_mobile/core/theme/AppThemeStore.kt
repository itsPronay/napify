package com.pronaycoding.blanket_mobile.core.theme

import android.content.Context

object AppThemeStore {
    internal const val PREFS_NAME = "napify_settings"
    private const val KEY_THEME_MODE = "app_theme_mode"

    const val MODE_LIGHT = "light"
    const val MODE_DARK = "dark"
    const val MODE_SYSTEM = "system"

    fun getSavedMode(context: Context): String =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_THEME_MODE, MODE_DARK) ?: MODE_DARK

    fun setSavedMode(context: Context, mode: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_THEME_MODE, mode)
            .apply()
    }
}
