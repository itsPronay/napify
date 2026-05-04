package com.pronaycoding.blankee.playback

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.context.GlobalContext

class MediaPlaybackActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != ACTION_TOGGLE_PLAYBACK) return
        val koin = GlobalContext.get()
        val global = koin.get<GlobalPlaybackState>()
        val notifications = koin.get<MediaPlaybackNotifications>()
        global.togglePlayPause()
        notifications.requestSync(
            global.canPlay.value,
            global.lastHasAudibleMix
        )
    }

    companion object {
        const val ACTION_TOGGLE_PLAYBACK = "com.pronaycoding.blankee.action.TOGGLE_PLAYBACK"
    }
}
