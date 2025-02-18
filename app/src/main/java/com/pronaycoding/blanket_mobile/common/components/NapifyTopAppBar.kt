package com.pronaycoding.blanket_mobile.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NapifyTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToAboutScreen: () -> Unit,
) {
    var showDropdown by rememberSaveable { mutableStateOf(false) }

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

        actions = {
            Row {
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
                                text = { Text("About") },
                                onClick = {
                                    showDropdown = false
                                    navigateToAboutScreen()
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}






