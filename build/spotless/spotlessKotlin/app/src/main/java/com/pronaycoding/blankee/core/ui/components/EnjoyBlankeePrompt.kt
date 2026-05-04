package com.pronaycoding.blankee.core.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.pronaycoding.blankee.R
import com.pronaycoding.blankee.core.common.Constants
import com.pronaycoding.blankee.core.common.util.openExternalUrl
import com.pronaycoding.blankee.core.datastore.PreferenceManagerRepositoryImpl

@Composable
fun EnjoyBlankeePrompt(shouldShow: Boolean) {
    if (!shouldShow) return

    val context = LocalContext.current
    var showEnjoyDialog by rememberSaveable { mutableStateOf(false) }
    var showStarDialog by rememberSaveable { mutableStateOf(false) }
    val preferenceManager =
        remember(context) {
            PreferenceManagerRepositoryImpl(
                context,
            )
        }

    LaunchedEffect(Unit) {
        // Mark as shown as soon as we decide to show, so it won't loop on restarts.
        preferenceManager.setEnjoyPromptShown(true)
        showEnjoyDialog = true
    }

    if (showEnjoyDialog) {
        AlertDialog(
            onDismissRequest = { showEnjoyDialog = false },
            title = { Text(stringResource(R.string.enjoy_prompt_title)) },
            text = { Text(stringResource(R.string.enjoy_prompt_message)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showEnjoyDialog = false
                        showStarDialog = true
                    },
                ) {
                    Text(stringResource(R.string.enjoy_prompt_yes))
                }
            },
            dismissButton = {
                TextButton(onClick = { showEnjoyDialog = false }) {
                    Text(stringResource(R.string.enjoy_prompt_no))
                }
            },
        )
    }

    if (showStarDialog) {
        AlertDialog(
            onDismissRequest = { showStarDialog = false },
            title = { Text(stringResource(R.string.star_github_title)) },
            text = { Text(stringResource(R.string.star_github_message)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showStarDialog = false
                        openExternalUrl(context, Constants.GITHUB_REPO)
                    },
                ) {
                    Text(stringResource(R.string.star_github_confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showStarDialog = false }) {
                    Text(stringResource(R.string.star_github_later))
                }
            },
        )
    }
}
