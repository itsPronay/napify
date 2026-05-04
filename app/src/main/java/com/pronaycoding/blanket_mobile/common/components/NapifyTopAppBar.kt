package com.pronaycoding.blanket_mobile.common.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pronaycoding.blanket_mobile.R
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.HomeViewmodel
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.getCardList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NapifyTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToSettings: () -> Unit,
    navigateToAboutScreen: () -> Unit,
) {
    val viewModel: HomeViewmodel = hiltViewModel()
    val context = LocalContext.current
    val canPlay by viewModel.canPlay.collectAsStateWithLifecycle()
    val presets by viewModel.presets.collectAsStateWithLifecycle()
    val builtinVolumes by viewModel.builtinVolumes.collectAsStateWithLifecycle()
    val customVolumes by viewModel.customVolumes.collectAsStateWithLifecycle()

    val canSavePreset = remember(builtinVolumes, customVolumes) {
        val until = getCardList().size - 1
        builtinVolumes.any { (i, v) -> i in 0 until until && v > 0f } ||
            customVolumes.any { (_, v) -> v > 0f }
    }

    var showDropdown by rememberSaveable { mutableStateOf(false) }
    var showSavePresetDialog by remember { mutableStateOf(false) }
    var presetNameInput by remember { mutableStateOf("") }
    var showTimerDialog by remember { mutableStateOf(false) }
    var selectedTimerOption by remember { mutableStateOf(5) }
    var customTimerInput by remember { mutableStateOf("") }

    if (showSavePresetDialog) {
        AlertDialog(
            onDismissRequest = { showSavePresetDialog = false },
            title = { Text(stringResource(R.string.save_preset_dialog_title)) },
            text = {
                OutlinedTextField(
                    value = presetNameInput,
                    onValueChange = { presetNameInput = it },
                    label = { Text(stringResource(R.string.preset_name_label)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.savePreset(presetNameInput)
                        presetNameInput = ""
                        showSavePresetDialog = false
                    }
                ) {
                    Text(stringResource(R.string.preset_dialog_save))
                }
            },
            dismissButton = {
                TextButton(onClick = { showSavePresetDialog = false }) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        )
    }

    if (showTimerDialog) {
        AlertDialog(
            onDismissRequest = { showTimerDialog = false },
            title = { Text(stringResource(R.string.timer_dialog_title)) },
            text = {
                Column {
                    val timerOptions = listOf(1, 5, 10, 15, 30, -1)
                    timerOptions.forEach { option ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedTimerOption == option,
                                onClick = { selectedTimerOption = option }
                            )
                            Text(
                                text = if (option == -1) {
                                    stringResource(R.string.timer_custom)
                                } else {
                                    stringResource(R.string.timer_minutes_format, option)
                                }
                            )
                        }
                    }
                    if (selectedTimerOption == -1) {
                        OutlinedTextField(
                            value = customTimerInput,
                            onValueChange = { customTimerInput = it.filter(Char::isDigit) },
                            label = { Text(stringResource(R.string.timer_custom_label)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedMinutes = if (selectedTimerOption == -1) {
                            customTimerInput.toLongOrNull()
                        } else {
                            selectedTimerOption.toLong()
                        }
                        if (selectedMinutes == null || selectedMinutes <= 0L) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.timer_invalid_input),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@TextButton
                        }
                        viewModel.startSleepTimer(selectedMinutes * 60_000L)
                        Toast.makeText(
                            context,
                            context.getString(R.string.timer_set, selectedMinutes),
                            Toast.LENGTH_SHORT
                        ).show()
                        showTimerDialog = false
                    }
                ) {
                    Text(stringResource(R.string.timer_start))
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimerDialog = false }) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        )
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.app_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.app_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        actions = {
            Row {
                IconButton(
                    onClick = {
                        viewModel.resetAllSounds()
                        Toast.makeText(
                            context,
                            context.getString(R.string.sounds_reset),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.menu_reset),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.handlePlayPause(!canPlay)
                    }
                ) {
                    Icon(
                        imageVector = when (canPlay) {
                            true -> Icons.Default.Pause
                            false -> Icons.Default.PlayArrow
                        },
                        contentDescription = if (canPlay) {
                            stringResource(R.string.menu_pause)
                        } else {
                            stringResource(R.string.menu_play)
                        },
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = {
                        showTimerDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = stringResource(R.string.menu_timer),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = {
                        showDropdown = true
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.content_desc_more_options),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    if (showDropdown) {
                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_settings)) },
                                onClick = {
                                    showDropdown = false
                                    navigateToSettings()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.menu_about)) },
                                onClick = {
                                    showDropdown = false
                                    navigateToAboutScreen()
                                }
                            )
                            HorizontalDivider()
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.presets_save_mix)) },
                                onClick = {
                                    showDropdown = false
                                    if (!canSavePreset) {
                                        Toast.makeText(
                                            context,
                                            context.getString(R.string.preset_nothing_to_save),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        presetNameInput = ""
                                        showSavePresetDialog = true
                                    }
                                }
                            )
                            HorizontalDivider()
                            if (presets.isEmpty()) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            stringResource(R.string.presets_empty),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    },
                                    onClick = { },
                                    enabled = false
                                )
                            } else {
                                presets.forEach { preset ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                preset.name,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        },
                                        onClick = {
                                            showDropdown = false
                                            viewModel.applyPreset(preset)
                                        },
                                        trailingIcon = {
                                            IconButton(
                                                onClick = {
                                                    showDropdown = false
                                                    viewModel.deletePreset(preset.id)
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.Delete,
                                                    contentDescription = stringResource(R.string.preset_delete_desc)
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
