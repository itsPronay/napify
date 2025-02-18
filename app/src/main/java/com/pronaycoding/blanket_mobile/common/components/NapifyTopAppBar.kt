package com.pronaycoding.blanket_mobile.common.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NapifyTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
//    onMusicActionButtonClick: () -> Unit,
    pauseAllSounds: () -> Unit,
    resumeAllSounds: () -> Unit,
    resetSongs: () -> Unit,
    navigationButtonClicked: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit
) {
    var canPlay by rememberSaveable { mutableStateOf(true) }
    var showDropdown by rememberSaveable { mutableStateOf(false) }

    var timeLeft by rememberSaveable { mutableStateOf(0) }
    var isTimerActive by rememberSaveable { mutableStateOf(false) }
    var showTimerSelectionBox by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    if (showTimerSelectionBox && isTimerActive){
        AlertDialog(
            onDismissRequest = { isTimerActive = false },
            icon = {
                Icon(imageVector = Icons.Filled.Timer, contentDescription = null)
            },
            title = {},
            text = {
                Column {
                    Text("Timer already running :")

                    Text("Time left : " + timeLeft + " minutes" + timeLeft%60 + "seconds")
                }
            },
            dismissButton = {
                Text("Dismiss", color = MaterialTheme.colorScheme.error)
            },
            confirmButton = {

            }
        )
    }


    if (showTimerSelectionBox && !isTimerActive) {
        AlertDialog(
            onDismissRequest = { showTimerSelectionBox = false },
            icon = {
                Row {
                    Icon(imageVector = Icons.Filled.Timer, contentDescription = null)
                    Text("Set Timer")
                }
            },
            text = {
                Column (modifier = Modifier.padding(horizontal = 16.dp)) {

                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            timeLeft = 5 * 60
                            isTimerActive = true
                            Toast.makeText(context, "Timer set to " + timeLeft/60 + " minutes", Toast.LENGTH_LONG ).show()
                            showTimerSelectionBox = false
                        }
                    ) {
                        Text("5 minutes")
                    }
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            timeLeft = 10 * 60
                            isTimerActive = true
                            Toast.makeText(context, "Timer set to " + timeLeft/60 + " minutes", Toast.LENGTH_LONG ).show()
                            showTimerSelectionBox = false
                        }
                    ) {
                        Text("10 minutes")
                    }
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            timeLeft = 15 * 60
                            isTimerActive = true
                            Toast.makeText(context, "Timer set to " + timeLeft/60 + " minutes", Toast.LENGTH_LONG ).show()
                            showTimerSelectionBox = false
                        }
                    ) {
                        Text("15 minutes")
                    }
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            timeLeft = 20 * 60
                            isTimerActive = true
                            Toast.makeText(context, "Timer set to " + timeLeft/60 + " minutes", Toast.LENGTH_LONG ).show()
                            showTimerSelectionBox = false
                        }
                    ) {
                        Text("20 minutes")
                    }
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            timeLeft = 30 * 60
                            isTimerActive = true
                            Toast.makeText(context, "Timer set to " + timeLeft/60 + " minutes", Toast.LENGTH_LONG ).show()
                            showTimerSelectionBox = false
                        }
                    ) {
                        Text("30 minutes")
                    }
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            timeLeft = 60 * 60
                            isTimerActive = true
                            Toast.makeText(context, "Timer set to " + timeLeft/60 + " minutes", Toast.LENGTH_LONG ).show()
                            showTimerSelectionBox = false
                        }
                    ) {
                        Text("1 hour")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimerSelectionBox = false }) {
                    Text("Dismiss", color = MaterialTheme.colorScheme.error)
                }
            },
            confirmButton = { }
        )
    }

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Napify", style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Listen to different sounds",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = .7f)
                )
            }
        },
//        navigationIcon = {
//            IconButton(onClick = { navigationButtonClicked() }) {
//                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
//            }
//        },
        actions = {
            Row {
                IconButton(onClick = { resetSongs() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = ""
                    )
                }

                IconButton(onClick = {
                    canPlay = !canPlay
                    if (canPlay) resumeAllSounds() else pauseAllSounds()
                }) {
                    Icon(
                        imageVector = if (canPlay) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = ""
                    )
                }

                IconButton(
                    onClick = {
                        showDropdown = true
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = ""
                    )
                    if (showDropdown) {
                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }) {
                            DropdownMenuItem(
                                text = { Text("Set sleep timer") },
                                onClick = {
                                    showTimerSelectionBox = true
                                    showDropdown = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = {
                                    showDropdown = false
                                    navigateToSettingsScreen()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("About") },
                                onClick = {
                                    showDropdown = false
                                    navigateToAboutScreen()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Quit") },
                                onClick = { showDropdown = false }
                            )
                        }
                    }
                }
            }
        }
    )
}






