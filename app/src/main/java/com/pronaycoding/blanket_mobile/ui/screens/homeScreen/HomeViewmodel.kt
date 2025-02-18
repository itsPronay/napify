package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pronaycoding.blanket_mobile.common.model.CardItems
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val soundManager: SoundManager2,
) : ViewModel() {

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







