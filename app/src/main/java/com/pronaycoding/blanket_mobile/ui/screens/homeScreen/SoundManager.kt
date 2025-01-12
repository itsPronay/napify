package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log

class

SoundManager {

    private val soundPool: SoundPool
    private val soundIds = mutableMapOf<Int, Int>()
    private val activeSounds = mutableMapOf<Int, Int>()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(15)  // Set the max number of sounds you want to play simultaneously
            .setAudioAttributes(audioAttributes)
            .build()
    }

    fun loadSounds(context: Context) {
        getCardList().forEachIndexed { index, item ->
            Log.d("debugUwu", "loading sound: $index")
            soundIds[index] = soundPool.load(context, item.audioSource, 1)
        }
    }

    private fun isSoundPlaying(soundIndex: Int): Boolean {
        Log.d("debugUwU", "isSoundPlaying: $soundIndex")
        return activeSounds.containsKey(soundIndex)
    }

    fun isAnySoundPlaying(): Boolean {
        getCardList().forEachIndexed { index, _ ->
            if (isSoundPlaying(index)) {
                return true
            }
        }
        return false
    }


    fun playSound(soundIndex: Int, volume: Float) {
        Log.d("debugUwU","playing:  "+  isSoundPlaying(soundIndex))

        if (!isSoundPlaying(soundIndex)) {
            val soundId = soundIds[soundIndex]
            Log.d("debugUwU","soundId:  "+  soundId)

            soundId?.let {
                val streamId = soundPool.play(it, volume, volume, 0, -1, 1f)
                activeSounds[soundIndex] = streamId
            }
        }
    }

    fun controlSound(soundIndex: Int, volume: Float) {
        if (isSoundPlaying(soundIndex)) {
            val streamId = activeSounds[soundIndex]
            streamId?.let {
                soundPool.setVolume(streamId, volume, volume)
            }
        }
    }

    fun stopSound(soundIndex: Int) {
        if (isSoundPlaying(soundIndex)) {
            val streamId = activeSounds[soundIndex]
            streamId?.let {
                soundPool.stop(it)
                activeSounds.remove(soundIndex)
            }
        }
    }

    fun pauseAllSounds() {
        activeSounds.values.forEach { streamId ->
            soundPool.pause(streamId)
        }
    }

    fun resumeAllSounds() {
        activeSounds.values.forEach { streamId ->
            soundPool.resume(streamId)
        }
    }

    fun pauseAllSound() {
        activeSounds.values.forEach { streamId ->
            soundPool.pause(streamId)
        }
    }

    fun stopAllSounds() {
        activeSounds.values.forEach { streamId ->
            soundPool.stop(streamId)
        }
        activeSounds.clear()
    }

    fun release() {
        soundPool.release()
    }
}
