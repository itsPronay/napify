package com.pronaycoding.blankee.core.engagement

import android.content.Context
import com.pronaycoding.blankee.core.constants.AppConstants
import com.pronaycoding.blankee.core.PreferenceManagerRepositoryImpl

object AppEngagementStore {
    /**
     * Increments the launch count and returns the updated value.
     */
    suspend fun incrementLaunchCount(context: Context): Int =
        PreferenceManagerRepositoryImpl(context).incrementLaunchCount()

    suspend fun shouldShowEnjoyPrompt(context: Context, currentLaunchCount: Int): Boolean {
        if (currentLaunchCount != AppConstants.ENJOY_PROMPT_TRIGGER_LAUNCH) return false
        return !PreferenceManagerRepositoryImpl(context).isEnjoyPromptShown()
    }

    suspend fun markEnjoyPromptShown(context: Context) {
        PreferenceManagerRepositoryImpl(context).setEnjoyPromptShown(true)
    }

    suspend fun shouldShowNotificationPrimer(context: Context): Boolean {
        return !PreferenceManagerRepositoryImpl(context).isNotificationPrimerShown()
    }

    suspend fun markNotificationPrimerShown(context: Context) {
        PreferenceManagerRepositoryImpl(context).setNotificationPrimerShown(true)
    }
}

