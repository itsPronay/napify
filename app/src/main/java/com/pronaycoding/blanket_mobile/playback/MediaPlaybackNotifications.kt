package com.pronaycoding.blanket_mobile.playback

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPlaybackNotifications @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {
    fun requestSync(canPlay: Boolean, hasAudibleMix: Boolean) {
        val intent = NapifyMediaPlaybackService.intentUpdate(appContext, canPlay, hasAudibleMix)
        if (hasAudibleMix) {
            ContextCompat.startForegroundService(appContext, intent)
        } else {
            // Do not start as foreground when there's nothing to show/play.
            appContext.startService(intent)
        }
    }
}
