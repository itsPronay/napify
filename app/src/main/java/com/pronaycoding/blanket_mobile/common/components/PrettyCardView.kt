package com.pronaycoding.blanket_mobile.common.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pronaycoding.blanket_mobile.common.model.CardItems
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.HomeViewmodel

@Composable
fun PrettyCardView(
    index: Int,
    cardItem: CardItems,
    playOrPause: Boolean,
    viewModel: HomeViewmodel = hiltViewModel(),
) {
    val context = LocalContext.current
    var shouldPlaySound by rememberSaveable { mutableStateOf(false) }
    var soundVolumeSlider by rememberSaveable { mutableFloatStateOf(0F) }


    LaunchedEffect(key1 = shouldPlaySound) {
        if (shouldPlaySound) {
            viewModel.playSound(index, soundVolumeSlider)
        } else viewModel.stopSound(index)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = cardItem.icon),
                contentDescription = "Icon",
                modifier = Modifier.size(32.dp),
                tint = if (shouldPlaySound) Color(0xFF27a157) else MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = cardItem.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Slider(
            colors = SliderDefaults.colors(
                thumbColor = when (playOrPause) {
                    true -> Color(0xFF27a157)
                    false -> Color.Gray
                },
                activeTrackColor = when (playOrPause) {
                    true -> Color(0xFF27a157)
                    false -> Color.Gray
                },
                inactiveTrackColor = Color(0xFF27a157).copy(alpha = 0.2f),
                disabledThumbColor = Color.Gray,
                disabledActiveTrackColor = Color.Gray,
                disabledInactiveTrackColor = Color.Gray
            ),
            value = soundVolumeSlider,
            onValueChange = { newValue ->
                if (playOrPause) {
                    soundVolumeSlider = newValue
                    shouldPlaySound = newValue > 0
                    viewModel.setVolume(index, soundVolumeSlider)
                }
            },
            onValueChangeFinished = {
                if (!playOrPause) {
                    Toast.makeText(context, "Can't play sound, Sound on pause", Toast.LENGTH_SHORT)
                        .show()

                }
            },
            valueRange = 0f..1f,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp) // Adjusted track height
        )

    }
}
