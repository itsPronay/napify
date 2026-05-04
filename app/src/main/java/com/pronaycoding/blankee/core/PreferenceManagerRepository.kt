package com.pronaycoding.blankee.core

interface PreferenceManagerRepository {
    fun getThemeModeBlocking(defaultValue: String): String
    suspend fun setThemeMode(mode: String)

    fun getLanguageTagBlocking(defaultValue: String): String
    suspend fun setLanguageTag(tag: String)

    suspend fun incrementLaunchCount(): Int
    suspend fun getLaunchCount(): Int

    suspend fun isEnjoyPromptShown(): Boolean
    suspend fun setEnjoyPromptShown(shown: Boolean)

    suspend fun isNotificationPrimerShown(): Boolean
    suspend fun setNotificationPrimerShown(shown: Boolean)
}