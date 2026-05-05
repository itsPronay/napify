package com.pronaycoding.blankee.core.service.playback

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * Manages system playback notifications and foreground service for audio playback.
 *
 * This class coordinates with [BlankeeMediaPlaybackService] to:
 * - Show playback controls in the system notification
 * - Run as a foreground service when audio is playing (required for continuous playback)
 * - Update notification state based on playback and mix status
 *
 * Foreground service status:
 * - **Starts foreground**: When there's an audible mix to display
 * - **Starts regular service**: When there's no audible mix (silent preset)
 *
 * @param appContext Application context used to start the service
 *
 * @see BlankeeMediaPlaybackService for the actual service implementation
 * @see GlobalPlaybackState for playback state management
 */
class MediaPlaybackNotifications(
    private val appContext: Context,
) {
    /**
     * Requests a notification sync with the current playback state.
     *
     * This method starts or updates [BlankeeMediaPlaybackService] with the current
     * playback state. The service will:
     * - Start as a foreground service if [hasAudibleMix] is true
     * - Start as a regular service if [hasAudibleMix] is false
     *
     * @param canPlay Whether audio playback is currently enabled (play/pause state)
     * @param hasAudibleMix Whether there are sounds with volume > 0 (audible preset)
     *
     * @see BlankeeMediaPlaybackService.intentUpdate for intent creation
     * @see ContextCompat.startForegroundService for foreground service requirements
     */
    fun requestSync(
        canPlay: Boolean,
        hasAudibleMix: Boolean,
    ) {
        val intent = BlankeeMediaPlaybackService.intentUpdate(appContext, canPlay, hasAudibleMix)
        if (hasAudibleMix) {
            // Start as foreground service when there's something to play/show
            ContextCompat.startForegroundService(appContext, intent)
        } else {
            // Do not start as foreground when there's nothing to show/play.
            appContext.startService(intent)
        }
    }
}
