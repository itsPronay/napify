package com.pronaycoding.blanket_mobile.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BlanketTabRow(modifier: Modifier = Modifier) {
    val titles = listOf("Home", "Settings")
    var state by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TabRow(selectedTabIndex = state, divider = {},
            indicator = {

            }
            ) {
            titles.forEachIndexed { index, title ->
                Tab(selectedContentColor = Color(0xFF27a157).copy(alpha = .5f),
                    selected = (index == state),
                    onClick = { state = index },
                    text = {
                        Text(
                            text = title,
                            color = if (state == index) Color(0xFF27a157) else MaterialTheme.colorScheme.inverseSurface.copy(
                                alpha = .7f
                            )
                        )
                    })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBlanketTabRow() {
    BlanketTabRow()
}
