package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pronaycoding.blanket_mobile.common.model.CardItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val soundManager: SoundManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _canPlay: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val canPlay: StateFlow<Boolean> = _canPlay.asStateFlow()

    init {
        soundManager.loadSounds()
    }

    fun handlePlayPause(canPlay: Boolean) {
        _canPlay.value = canPlay

        if (canPlay) {
            soundManager.resumeAllSounds()
        } else {
            soundManager.pauseAllSounds()
        }
    }

    fun setVolume(id: Int, volume: Float) {
        if (canPlay.value) {
            soundManager.controlSound(id, volume)
        }
    }

    fun stopSound(int: Int) {
        soundManager.stopSound(int)
    }

    fun playSound(int: Int, volume: Float) {
        if (canPlay.value) {
            soundManager.playSound(int, volume)
        }
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







