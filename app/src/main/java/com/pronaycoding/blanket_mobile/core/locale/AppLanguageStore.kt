package com.pronaycoding.blanket_mobile.core.locale

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import com.pronaycoding.blanket_mobile.core.theme.AppThemeStore
import java.util.Locale

object AppLanguageStore {
    private const val KEY_LANGUAGE_TAG = "app_language_tag"
    const val TAG_SYSTEM = "system"
    const val TAG_ENGLISH = "en"
    const val TAG_HINDI = "hi"
    const val TAG_BENGALI = "bn"
    const val TAG_SPANISH = "es"

    fun getSavedTag(context: Context): String =
        context.getSharedPreferences(AppThemeStore.PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LANGUAGE_TAG, TAG_SYSTEM) ?: TAG_SYSTEM

    fun setSavedTag(context: Context, tag: String) {
        context.getSharedPreferences(AppThemeStore.PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LANGUAGE_TAG, tag)
            .apply()
    }

    /**
     * Returns a context whose resources use [getSavedTag], so stringResource / getString match
     * the user’s language. Used from [android.app.Activity.attachBaseContext].
     */
    fun wrapContext(base: Context): Context {
        val tag = getSavedTag(base)
        if (tag == TAG_SYSTEM) return base

        val locale = Locale.forLanguageTag(tag)
        Locale.setDefault(locale)
        val config = Configuration(base.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(LocaleList(locale))
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
        }
        return base.createConfigurationContext(config)
    }
}
