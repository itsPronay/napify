package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

sealed class HomeUiState {
    data object Initial : HomeUiState()
    data object Loading : HomeUiState()

    //    object DeletedSuccessfully : HomeUiState()
    data class ShowError(val message: Int) : HomeUiState()
}
