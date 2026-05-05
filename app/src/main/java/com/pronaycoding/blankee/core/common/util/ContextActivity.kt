package com.pronaycoding.blankee.core.common.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * Extension function to find the Activity from a given Context.
 *
 * This utility recursively traverses the context chain to locate an Activity instance.
 * Useful in Compose composables and other contexts where you need access to the Activity
 * but only have a Context reference.
 *
 * Implementation uses tail recursion for efficient unwrapping of ContextWrapper instances.
 *
 * Usage example:
 * ```kotlin
 * val activity = context.findActivity()
 * if (activity != null) {
 *     // Use the activity for operations like:
 *     // - Requesting permissions
 *     // - Launching intents
 *     // - Accessing activity-specific APIs
 * }
 * ```
 *
 * @return The Activity if found, null if the context doesn't wrap an Activity
 */
tailrec fun Context.findActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
