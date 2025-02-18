package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class SoundManager2(private val context: Context) {

    private val mediaPlayers = mutableMapOf<Int, MediaPlayer>()

    /**
     * Loads a list of sounds into the media players.
     */
    fun loadSounds() {
        getCardList().forEachIndexed { index, item ->
            Log.d("SoundManager", "Loading sound: $index")
            val mediaPlayer = MediaPlayer.create(context, item.audioSource)
            mediaPlayers[index] = mediaPlayer
        }
    }

    /**
     * Checks if a sound is currently playing.
     */
    private fun isSoundPlaying(soundIndex: Int): Boolean {
        return mediaPlayers[soundIndex]?.isPlaying == true
    }

    /**
     * Checks if any sound is playing.
     */
    fun isAnySoundPlaying(): Boolean {
        return mediaPlayers.values.any { it.isPlaying }
    }

    /**
     * Plays a sound if it's not already playing.
     */
    fun playSound(soundIndex: Int, volume: Float) {
        Log.d("SoundManager", "Play sound called for index: $soundIndex")
        mediaPlayers[soundIndex]?.let { mediaPlayer ->
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.setVolume(volume, volume)
                mediaPlayer.start()
                Log.d("SoundManager", "Playing sound at index: $soundIndex")
            } else {
                Log.d("SoundManager", "Sound at index: $soundIndex is already playing.")
            }
        } ?: Log.e("SoundManager", "MediaPlayer not found for index: $soundIndex")
    }

    /**
     * Adjusts the volume of a currently playing sound.
     */
    fun controlSound(soundIndex: Int, volume: Float) {
        mediaPlayers[soundIndex]?.let {
            it.setVolume(volume, volume)
        } ?: Log.e("SoundManager", "Cannot control sound at index: $soundIndex, not found.")
    }

    /**
     * Stops a currently playing sound.
     */
    fun stopSound(soundIndex: Int) {
        mediaPlayers[soundIndex]?.let {
            if (it.isPlaying) {
                it.stop()
                it.prepare() // Reset to initial state
                Log.d("SoundManager", "Stopped sound at index: $soundIndex")
            }
        } ?: Log.e("SoundManager", "Cannot stop sound at index: $soundIndex, not found.")
    }

    /**
     * Pauses all currently playing sounds.
     */
    fun pauseAllSounds() {
        mediaPlayers.values.forEach { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }
        Log.d("SoundManager", "All sounds paused.")
    }

    /**
     * Resumes all paused sounds.
     */
    fun resumeAllSounds() {
        mediaPlayers.values.forEach { mediaPlayer ->
            mediaPlayer.start()
        }
        Log.d("SoundManager", "All sounds resumed.")
    }

    /**
     * Stops all currently playing sounds and clears active sound records.
     */
    fun stopAllSounds() {
        mediaPlayers.values.forEach { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
        }
        Log.d("SoundManager", "All sounds stopped.")
    }

    /**
     * Releases resources used by MediaPlayer.
     */
    fun release() {
        mediaPlayers.values.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayers.clear()
        Log.d("SoundManager", "SoundManager resources released.")
    }
}
