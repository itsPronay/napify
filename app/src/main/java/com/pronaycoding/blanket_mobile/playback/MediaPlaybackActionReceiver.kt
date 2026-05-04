package com.pronaycoding.blanket_mobile.playback

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.EntryPointAccessors

class MediaPlaybackActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != ACTION_TOGGLE_PLAYBACK) return
        val app = context.applicationContext
        val entry = EntryPointAccessors.fromApplication(app, PlaybackEntryPoint::class.java)
        val global = entry.globalPlaybackState()
        global.togglePlayPause()
        entry.mediaPlaybackNotifications().requestSync(
            global.canPlay.value,
            global.lastHasAudibleMix
        )
    }

    companion object {
        const val ACTION_TOGGLE_PLAYBACK = "com.pronaycoding.blanket_mobile.action.TOGGLE_PLAYBACK"
    }
}
