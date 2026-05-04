package com.pronaycoding.blankee.playback

import android.content.Context
import androidx.core.content.ContextCompat

class MediaPlaybackNotifications(
    private val appContext: Context,
) {
    fun requestSync(canPlay: Boolean, hasAudibleMix: Boolean) {
        val intent = BlankeeMediaPlaybackService.intentUpdate(appContext, canPlay, hasAudibleMix)
        if (hasAudibleMix) {
            ContextCompat.startForegroundService(appContext, intent)
        } else {
            // Do not start as foreground when there's nothing to show/play.
            appContext.startService(intent)
        }
    }
}
