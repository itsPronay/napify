package com.pronaycoding.blankee.feature.settings

/**
 * ViewModel for the Settings screen managing user preferences.
 *
 * Responsibilities:
 * - Theme preference management (light/dark/system)
 * - Language/locale preference management
 * - Premium unlock status monitoring
 * - Billing operations coordination
 *
 * State exposed:
 * - `selectedTheme`: Current theme mode
 * - `selectedLanguage`: Current language tag (BCP 47)
 * - `customSoundsUnlocked`: Premium status
 * - `themeChoices`: Available theme options
 * - `languageChoices`: Available language options
 *
 * Theme changes require Activity recreation to apply new theme/language.
 *
 * @see PreferenceManagerRepository for preference persistence
 * @see PlayBillingManager for in-app purchase operations
 */

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pronaycoding.blankee.BuildConfig
import com.pronaycoding.blankee.R
import com.pronaycoding.blankee.core.common.Constants
import com.pronaycoding.blankee.core.datastore.PreferenceManagerRepository
import com.pronaycoding.blankee.core.service.billing.PlayBillingManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsViewModel(
    private val prefManager: PreferenceManagerRepository,
    private val billing: PlayBillingManager,
) : ViewModel() {
    val themeChoices =
        listOf(
            ThemeChoice(Constants.MODE_LIGHT, R.string.theme_light),
            ThemeChoice(Constants.MODE_DARK, R.string.theme_dark),
            ThemeChoice(Constants.MODE_SYSTEM, R.string.theme_system),
        )

    val languageChoices =
        listOf(
            LanguageChoice(Constants.LANGUAGE_TAG_SYSTEM, R.string.language_system),
            LanguageChoice(Constants.LANGUAGE_TAG_ENGLISH, R.string.language_english),
            LanguageChoice(Constants.LANGUAGE_TAG_HINDI, R.string.language_hindi),
            LanguageChoice(Constants.LANGUAGE_TAG_BENGALI, R.string.language_bengali),
            LanguageChoice(Constants.LANGUAGE_TAG_SPANISH, R.string.language_spanish),
        )

    private val _selectedTheme =
        MutableStateFlow(prefManager.getThemeModeBlocking(Constants.MODE_DARK))
    val selectedTheme: StateFlow<String> = _selectedTheme.asStateFlow()

    private val _selectedLanguage =
        MutableStateFlow(
            prefManager.getLanguageTagBlocking(Constants.LANGUAGE_TAG_SYSTEM),
        )
    val selectedLanguage: StateFlow<String> = _selectedLanguage.asStateFlow()

    val customSoundsUnlocked: StateFlow<Boolean> =
        prefManager
            .premiumUnlockedFlow()
            .map { storedPremium -> !BuildConfig.CUSTOM_SOUNDS_PREMIUM_LOCKED || storedPremium }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                !BuildConfig.CUSTOM_SOUNDS_PREMIUM_LOCKED,
            )

    fun updateTheme(mode: String) {
        if (_selectedTheme.value == mode) return
        _selectedTheme.value = mode
        runBlocking {
            prefManager.setThemeMode(mode)
        }
    }

    fun updateLanguage(tag: String) {
        if (_selectedLanguage.value == tag) return
        _selectedLanguage.value = tag
        runBlocking {
            prefManager.setLanguageTag(tag)
        }
    }

    fun launchPremiumPurchase(activity: Activity) {
        billing.launchPremiumPurchase(activity)
    }

    fun restorePurchases(context: Context) {
        viewModelScope.launch {
            val has = billing.syncPremiumFromPlay()
            Toast
                .makeText(
                    context,
                    context.getString(
                        if (has) R.string.billing_restored else R.string.billing_restore_none,
                    ),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }
}

data class ThemeChoice(
    val mode: String,
    val labelRes: Int,
)

data class LanguageChoice(
    val tag: String,
    val labelRes: Int,
)
