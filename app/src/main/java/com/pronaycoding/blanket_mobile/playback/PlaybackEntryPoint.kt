package com.pronaycoding.blanket_mobile.playback

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PlaybackEntryPoint {
    fun globalPlaybackState(): GlobalPlaybackState
    fun mediaPlaybackNotifications(): MediaPlaybackNotifications
}
