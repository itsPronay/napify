package com.pronaycoding.blanket_mobile.playback

import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.SoundManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalPlaybackState @Inject constructor(
    private val soundManager: SoundManager,
) {
    private val _canPlay = MutableStateFlow(true)
    val canPlay: StateFlow<Boolean> = _canPlay.asStateFlow()

    @Volatile
    var lastHasAudibleMix: Boolean = false

    fun setCanPlay(play: Boolean) {
        _canPlay.value = play
        if (play) {
            soundManager.resumeAllSounds()
        } else {
            soundManager.pauseAllSounds()
        }
    }

    fun togglePlayPause() {
        setCanPlay(!_canPlay.value)
    }
}
