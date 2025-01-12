package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

/**
 * Created by Pronay Sarker on 12/01/2025 (1:57 PM)
 */
sealed class MainUiState {

    data object Initial : MainUiState()

    data object ResetAllSound : MainUiState()

    data class PausePlay(val play: Boolean) : MainUiState()
}
