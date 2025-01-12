package com.pronaycoding.blanket_mobile.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pronaycoding.blanket_mobile.R
import com.pronaycoding.blanket_mobile.ui.theme.NapifyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackArrowClicked: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Settings") },
                navigationIcon = {
                    IconButton(onClick = { onBackArrowClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(), onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .weight(.2f)
                            .height(32.dp),
                        painter = painterResource(
                            id = R.drawable.day
                        ), contentDescription = "Theme"
                    )

                    Column(
                        modifier = Modifier
                            .weight(.8f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Theme",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Current theme: System theme",
                            color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            /*
            language
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth(), onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .weight(.2f)
                            .height(32.dp),
                        painter = painterResource(
                            id = R.drawable.translate
                        ), contentDescription = "Language"
                    )

                    Column(
                        modifier = Modifier
                            .weight(.8f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Language",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "English",
                            color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            /*
            language
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth(), onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .weight(.2f)
                            .height(32.dp),
                        painter = painterResource(
                            id = R.drawable.white_noise
                        ), contentDescription = "Language"
                    )

                    Column(
                        modifier = Modifier
                            .weight(.8f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Manage sounds",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Add or Delete sounds",
                            color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                        )
                    }
                }
            }

        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun PreviewSystemScreen(modifier: Modifier = Modifier) {
    NapifyAppTheme {
        SettingsScreen(
            onBackArrowClicked = {}
        )
    }
}