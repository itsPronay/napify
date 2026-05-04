package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import com.pronaycoding.blanket_mobile.common.model.CardItems
import java.io.File

class SoundManager(private val context: Context) {

    private val mediaPlayers = mutableMapOf<Int, MediaPlayer>()
    private val customMediaPlayers = mutableMapOf<Int, MediaPlayer>()
    private var currentlyPlayingSounds: MutableList<Int> = mutableListOf()

    /**
     * Loads a list of sounds into the media players.
     */
    fun loadSounds() {
        getCardList().forEachIndexed { index: Int, item: CardItems ->
            Log.d("SoundManager", "Loading sound: $index")
            val mediaPlayer = MediaPlayer.create(context, item.audioSource)
            mediaPlayers[index] = mediaPlayer
        }
    }

    /**
     * Loads a custom sound from file path or content URI
     */
    fun loadCustomSound(soundId: Int, filePath: String): Boolean {
        return try {
            Log.d("SoundManager", "Loading custom sound: $soundId from path: $filePath")
            val mediaPlayer = MediaPlayer()

            // Try to handle both file paths and content URIs
            if (filePath.startsWith("content://")) {
                // Handle content URI
                val uri = Uri.parse(filePath)
                mediaPlayer.setDataSource(context, uri)
            } else if (filePath.startsWith("file://")) {
                // Handle file URI
                val uri = Uri.parse(filePath)
                mediaPlayer.setDataSource(context, uri)
            } else {
                // Handle file path
                val file = File(filePath)
                if (!file.exists()) {
                    Log.e("SoundManager", "Custom sound file does not exist: $filePath")
                    return false
                }
                mediaPlayer.setDataSource(context, Uri.fromFile(file))
            }

            mediaPlayer.prepare()
            customMediaPlayers[soundId] = mediaPlayer
            Log.d("SoundManager", "Successfully loaded custom sound: $soundId")
            true
        } catch (e: Exception) {
            Log.e("SoundManager", "Error loading custom sound: $soundId - ${e.message}", e)
            false
        }
    }

    /**
     * Unloads a custom sound and releases resources
     */
    fun unloadCustomSound(soundId: Int) {
        customMediaPlayers[soundId]?.let {
            try {
                if (it.isPlaying) {
                    it.stop()
                }
                it.release()
                customMediaPlayers.remove(soundId)
                Log.d("SoundManager", "Unloaded custom sound: $soundId")
            } catch (e: Exception) {
                Log.e("SoundManager", "Error unloading custom sound: $soundId", e)
            }
        }
    }

    /**
     * Checks if any sound is playing.
     */
    fun isAnySoundPlaying(): Boolean {
        return mediaPlayers.values.any { it.isPlaying } || customMediaPlayers.values.any { it.isPlaying }
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
                mediaPlayer.isLooping = true
                Log.d("SoundManager", "Playing sound at index: $soundIndex")
            } else {
                mediaPlayer.setVolume(volume, volume)
                Log.d("SoundManager", "Sound at index: $soundIndex already playing; volume updated.")
            }
        } ?: Log.e("SoundManager", "MediaPlayer not found for index: $soundIndex")
    }

    fun isBuiltInPlaying(soundIndex: Int): Boolean =
        mediaPlayers[soundIndex]?.isPlaying == true

    /**
     * Plays a custom sound from file
     */
    fun playCustomSound(soundId: Int, volume: Float) {
        Log.d("SoundManager", "Play custom sound called for id: $soundId")
        customMediaPlayers[soundId]?.let { mediaPlayer ->
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.setVolume(volume, volume)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                Log.d("SoundManager", "Playing custom sound: $soundId")
            } else {
                mediaPlayer.setVolume(volume, volume)
                Log.d("SoundManager", "Custom sound: $soundId already playing; volume updated.")
            }
        } ?: Log.e("SoundManager", "Custom MediaPlayer not found for id: $soundId")
    }

    fun isCustomSoundPlaying(soundId: Int): Boolean =
        customMediaPlayers[soundId]?.isPlaying == true

    /**
     * Adjusts the volume of a currently playing sound.
     */
    fun controlSound(soundIndex: Int, volume: Float) {
        mediaPlayers[soundIndex]?.let {
            it.setVolume(volume, volume)
        } ?: Log.e("SoundManager", "Cannot control sound at index: $soundIndex, not found.")
    }

    /**
     * Adjusts the volume of a custom sound.
     */
    fun controlCustomSound(soundId: Int, volume: Float) {
        customMediaPlayers[soundId]?.let {
            it.setVolume(volume, volume)
        } ?: Log.e("SoundManager", "Cannot control custom sound: $soundId, not found.")
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
     * Stops a custom sound.
     */
    fun stopCustomSound(soundId: Int) {
        customMediaPlayers[soundId]?.let {
            if (it.isPlaying) {
                it.stop()
                try {
                    it.prepare()
                } catch (e: Exception) {
                    Log.e("SoundManager", "Error preparing custom sound after stop: $soundId", e)
                }
                Log.d("SoundManager", "Stopped custom sound: $soundId")
            }
        } ?: Log.e("SoundManager", "Cannot stop custom sound: $soundId, not found.")
    }

    fun pauseAllSounds() {
        mediaPlayers.entries.forEach { (index, mediaPlayer) ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                currentlyPlayingSounds.add(index)
                Log.d("SoundManager", "Paused sound at index: $index")
            }
        }
        customMediaPlayers.entries.forEach { (id, mediaPlayer) ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                currentlyPlayingSounds.add(id)
                Log.d("SoundManager", "Paused custom sound: $id")
            }
        }
        Log.d("SoundManager", "All sounds paused.")
    }


    /**
     * Resumes all paused sounds.
     */
    fun resumeAllSounds() {
        mediaPlayers.entries.forEach { (index, mediaPlayer) ->
            if (currentlyPlayingSounds.contains(index)) {
                mediaPlayer.start()
            }
        }
        customMediaPlayers.entries.forEach { (id, mediaPlayer) ->
            if (currentlyPlayingSounds.contains(id)) {
                mediaPlayer.start()
            }
        }
        currentlyPlayingSounds = mutableListOf()
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
        customMediaPlayers.values.forEach { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                try {
                    mediaPlayer.prepare()
                } catch (e: Exception) {
                    Log.e("SoundManager", "Error preparing custom sound after stop", e)
                }
            }
        }
        currentlyPlayingSounds.clear()
        Log.d("SoundManager", "All sounds stopped.")
    }

    /**
     * Releases resources used by MediaPlayer.
     */
    fun release() {
        mediaPlayers.values.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        customMediaPlayers.values.forEach { mediaPlayer ->
            mediaPlayer.release()
        }
        mediaPlayers.clear()
        customMediaPlayers.clear()
        Log.d("SoundManager", "SoundManager resources released.")
    }
}


