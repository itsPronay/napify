package com.pronaycoding.blankee.core.theme

import android.content.Context
import com.pronaycoding.blankee.core.PreferenceManagerRepositoryImpl
import kotlinx.coroutines.runBlocking

object AppThemeStore {
    const val MODE_LIGHT = PreferenceManagerRepositoryImpl.MODE_LIGHT
    const val MODE_DARK = PreferenceManagerRepositoryImpl.MODE_DARK
    const val MODE_SYSTEM = PreferenceManagerRepositoryImpl.MODE_SYSTEM

    fun getSavedMode(context: Context): String =
        PreferenceManagerRepositoryImpl(context).getThemeModeBlocking(MODE_DARK)

    fun setSavedMode(context: Context, mode: String) {
        runBlocking {
            PreferenceManagerRepositoryImpl(context).setThemeMode(mode)
        }
    }
}
