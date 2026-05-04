package com.pronaycoding.blankee.core

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class PreferenceManagerRepositoryImpl(
    private val context: Context
) : PreferenceManagerRepository {
    private val appContext = context.applicationContext

    override fun getThemeModeBlocking(defaultValue: String): String = runBlocking {
        appContext.dataStore.data.first()[Keys.themeMode] ?: defaultValue
    }

    override suspend fun setThemeMode(mode: String) {
        appContext.dataStore.edit { prefs ->
            prefs[Keys.themeMode] = mode
        }
    }

    override fun getLanguageTagBlocking(defaultValue: String): String = runBlocking {
        appContext.dataStore.data.first()[Keys.languageTag] ?: defaultValue
    }

    override suspend fun setLanguageTag(tag: String) {
        appContext.dataStore.edit { prefs ->
            prefs[Keys.languageTag] = tag
        }
    }

    override suspend fun incrementLaunchCount(): Int {
        var next = 1
        appContext.dataStore.edit { prefs ->
            next = (prefs[Keys.launchCount] ?: 0) + 1
            prefs[Keys.launchCount] = next
        }
        return next
    }

    override suspend fun getLaunchCount(): Int =
        appContext.dataStore.data.first()[Keys.launchCount] ?: 0

    override suspend fun isEnjoyPromptShown(): Boolean =
        appContext.dataStore.data.first()[Keys.enjoyPromptShown] ?: false

    override suspend fun setEnjoyPromptShown(shown: Boolean) {
        appContext.dataStore.edit { prefs ->
            prefs[Keys.enjoyPromptShown] = shown
        }
    }

    override suspend fun isNotificationPrimerShown(): Boolean =
        appContext.dataStore.data.first()[Keys.notificationPrimerShown] ?: false

    override suspend fun setNotificationPrimerShown(shown: Boolean) {
        appContext.dataStore.edit { prefs ->
            prefs[Keys.notificationPrimerShown] = shown
        }
    }

    companion object {
        const val MODE_LIGHT = "light"
        const val MODE_DARK = "dark"
        const val MODE_SYSTEM = "system"
    }

    private object Keys {
        val themeMode: Preferences.Key<String> = stringPreferencesKey("app_theme_mode")
        val languageTag: Preferences.Key<String> = stringPreferencesKey("app_language_tag")
        val launchCount: Preferences.Key<Int> = intPreferencesKey("launch_count")
        val enjoyPromptShown: Preferences.Key<Boolean> = booleanPreferencesKey("enjoy_prompt_shown")
        val notificationPrimerShown: Preferences.Key<Boolean> =
            booleanPreferencesKey("notification_primer_shown")
    }
}

private val Context.dataStore by preferencesDataStore(name = "blankee_preferences")