package com.pronaycoding.blankee.core.service.playback

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.context.GlobalContext

/**
 * Broadcast receiver for handling playback control actions from the system notification.
 *
 * This receiver intercepts the [ACTION_TOGGLE_PLAYBACK] broadcast and:
 * 1. Toggles the global playback state (play/pause)
 * 2. Syncs the notification with the new state
 *
 * The receiver can be triggered by:
 * - System playback notification buttons
 * - Voice commands
 * - External media button events (headphones, car systems, etc.)
 *
 * Registration: This receiver should be registered in the manifest or via dynamic registration.
 *
 * @see GlobalPlaybackState for playback state toggling
 * @see MediaPlaybackNotifications for notification updates
 * @see BlankeeMediaPlaybackService for service integration
 */
class MediaPlaybackActionReceiver : BroadcastReceiver() {
    /**
     * Handles broadcast intents for playback control actions.
     *
     * When [ACTION_TOGGLE_PLAYBACK] is received:
     * 1. Retrieves [GlobalPlaybackState] and [MediaPlaybackNotifications] from Koin
     * 2. Toggles the playback state
     * 3. Syncs the notification with the updated state
     *
     * @param context The context in which the receiver is running
     * @param intent The intent being received (should have action [ACTION_TOGGLE_PLAYBACK])
     */
    override fun onReceive(
        context: Context,
        intent: Intent?,
    ) {
        if (intent?.action != ACTION_TOGGLE_PLAYBACK) return
        val koin = GlobalContext.get()
        val global = koin.get<GlobalPlaybackState>()
        val notifications = koin.get<MediaPlaybackNotifications>()
        global.togglePlayPause()
        notifications.requestSync(
            global.canPlay.value,
            global.lastHasAudibleMix,
        )
    }

    companion object {
        /**
         * Broadcast action for toggling playback state.
         *
         * This action is typically sent by:
         * - Notification play/pause button
         * - System media controls
         * - Headphone button events
         */
        const val ACTION_TOGGLE_PLAYBACK = "com.pronaycoding.blankee.action.TOGGLE_PLAYBACK"
    }
}
