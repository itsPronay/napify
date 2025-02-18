package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log

class SoundManager (private val context: Context) {

    private val soundPool: SoundPool
    private val soundIds = mutableMapOf<Int, Int>()
    private val activeSounds = mutableMapOf<Int, Int>()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA )
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(15) // Adjust max streams as needed
            .setAudioAttributes(audioAttributes)
            .build()
    }

    /**
     * Loads a list of sounds into the sound pool.
     * @param context The context required to access resources.
     */
    fun loadSounds() {
        getCardList().forEachIndexed { index, item ->
            Log.d("debugUwu", "loading sound: $index")
            soundIds[index] = soundPool.load(context, item.audioSource, 1)
        }
    }

    /**
     * Checks if a sound is currently playing.
     */
    private fun isSoundPlaying(soundIndex: Int): Boolean {
        return activeSounds.containsKey(soundIndex)
    }

    /**
     * Checks if any sound is playing.
     */
    fun isAnySoundPlaying(): Boolean {
        return activeSounds.isNotEmpty()
    }

    /**
     * Plays a sound if it's not already playing.
     */
    fun playSound(soundIndex: Int, volume: Float) {
        Log.d("UwUpronay", "SoundManager playing called ")
        if (!isSoundPlaying(soundIndex)) {
            val soundId = soundIds[soundIndex]
            soundId?.let {
                val streamId = soundPool.play(it, volume, volume, 1, -1, 1f)
                if (streamId != 0) { // Check if the stream was successfully played
                    activeSounds[soundIndex] = streamId
                    Log.d("SoundManager", "Playing sound at index: $soundIndex")
                } else {
                    Log.e("SoundManager", "Failed to play sound at index: $soundIndex")
                }
            } ?: Log.e("SoundManager", "Sound ID not found for index: $soundIndex")
        }
    }

    /**
     * Adjusts the volume of a currently playing sound.
     */
    fun controlSound(soundIndex: Int, volume: Float) {
        activeSounds[soundIndex]?.let {
            soundPool.setVolume(it, volume, volume)
        } ?: Log.e("SoundManager", "Cannot control sound at index: $soundIndex, not playing.")
    }

    /**
     * Stops a currently playing sound.
     */
    fun stopSound(soundIndex: Int) {
        activeSounds[soundIndex]?.let {
            soundPool.stop(it)
            activeSounds.remove(soundIndex)
            Log.d("SoundManager", "Stopped sound at index: $soundIndex")
        } ?: Log.e("SoundManager", "Cannot stop sound at index: $soundIndex, not playing.")
    }

    /**
     * Pauses all currently playing sounds.
     */
    fun pauseAllSounds() {
        activeSounds.values.forEach { streamId ->
            soundPool.pause(streamId)
        }
        Log.d("SoundManager", "All sounds paused.")
    }

    /**
     * Resumes all paused sounds.
     */
    fun resumeAllSounds() {
        activeSounds.values.forEach { streamId ->
            soundPool.resume(streamId)
        }
        Log.d("SoundManager", "All sounds resumed.")
    }

    /**
     * Stops all currently playing sounds and clears active sound records.
     */
    fun stopAllSounds() {
        activeSounds.values.forEach { streamId ->
            soundPool.stop(streamId)
        }
        activeSounds.clear()
        Log.d("SoundManager", "All sounds stopped.")
    }

    /**
     * Releases resources used by the SoundPool.
     * This must be called when the SoundManager is no longer needed to avoid memory leaks.
     */
    fun release() {
        soundPool.release()
        soundIds.clear()
        activeSounds.clear()
        Log.d("SoundManager", "SoundManager resources released.")
    }
}
