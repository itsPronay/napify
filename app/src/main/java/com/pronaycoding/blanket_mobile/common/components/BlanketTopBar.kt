package com.pronaycoding.blanket_mobile.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pronaycoding.blanket_mobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlanketTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onMusicActionButtonClick: () -> Unit,
    isPlaying: Boolean,
    resetSongs: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(32.dp), painter = painterResource(
                            id = R.drawable.blanket__1_
                        ), contentDescription = ""

                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Blanket", style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "Listen to different sounds",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = .7f)
                    )

                }
            }

        }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        }, actions = {
            Row {
                IconButton(onClick = { resetSongs() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = ""
                    )
                }

                IconButton(onClick = { onMusicActionButtonClick() }) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = ""
                    )
                }

            }
        }
    )
}






