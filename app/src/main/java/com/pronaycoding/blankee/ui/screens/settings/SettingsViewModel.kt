package com.pronaycoding.blankee.ui.screens.settings

import androidx.lifecycle.ViewModel
import com.pronaycoding.blankee.core.PreferenceManagerRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class SettingsViewModel(
    private val prefManager : PreferenceManagerRepositoryImpl
) : ViewModel() {

    private val _selectedTheme =
        MutableStateFlow(prefManager.getThemeModeBlocking(PreferenceManagerRepositoryImpl.MODE_DARK))
    val selectedTheme: StateFlow<String> = _selectedTheme.asStateFlow()

    private val _selectedLanguage =
        MutableStateFlow(prefManager.getLanguageTagBlocking("system"))
    val selectedLanguage: StateFlow<String> = _selectedLanguage.asStateFlow()

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
}
