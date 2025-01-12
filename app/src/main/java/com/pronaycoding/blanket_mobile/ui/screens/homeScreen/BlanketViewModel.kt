package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.pronaycoding.blanket_mobile.common.model.CardItems
import com.pronaycoding.blanket_mobile.common.model.DrawerItems
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class BlanketViewModel @Inject constructor(
    private val soundManager: SoundManager,
) : ViewModel() {

    //    val _isSoundPlaying = MutableStateFlow(fal)
    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Initial)
    val mainUiState = _mainUiState.asStateFlow()


    init {
        soundManager.loadSounds()
    }

    fun setVolume(id: Int, volume: Float) {
        soundManager.controlSound(id, volume)
    }

    fun stopSound(int: Int) {
        soundManager.stopSound(int)
    }

    fun playSound(int: Int, volume: Float) {
        soundManager.playSound(int, volume)
        Log.d("debugUwU", "playing sound")
    }

    fun isAnySongPlaying(): Boolean {
        return soundManager.isAnySoundPlaying()
    }

    fun resetSongs() {
        soundManager.stopAllSounds()
//        _mainUiState.value = MainUiState.ResetAllSound
//        _mainUiState.value = MainUiState.Initial
    }

    fun pauseAllSongs() {
        soundManager.pauseAllSounds()
        _mainUiState.value = MainUiState.PausePlay(play = false)
    }

    fun resumeAllSound() {
        soundManager.resumeAllSounds()
        _mainUiState.value = MainUiState.PausePlay(true)
    }

    fun getDrawerItems(): List<DrawerItems> {
        return listOf(
            DrawerItems.Home,
            DrawerItems.SourceCode,
            DrawerItems.RequestFeature,
            DrawerItems.About,
            DrawerItems.Settings,
            DrawerItems.Quit,
        )
    }

}


fun getCardList(): List<CardItems> {
    return listOf(
        CardItems.Rain,
        CardItems.Wind,
        CardItems.Storm,
        CardItems.Wave,
        CardItems.Stream,
        CardItems.Birds,
        CardItems.SummerNight,

        CardItems.Train,
        CardItems.Boat,
        CardItems.City,

        CardItems.CoffeeShop,
        CardItems.FirePlace,
        CardItems.BusyRestaurant,

        CardItems.PinkNoise,
        CardItems.WhiteNoise,

        CardItems.Custom
    )
}







