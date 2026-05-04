package com.pronaycoding.blankee.common.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pronaycoding.blankee.R
import com.pronaycoding.blankee.common.model.CardItems
import com.pronaycoding.blankee.ui.screens.homeScreen.HomeViewmodel
import org.koin.androidx.compose.koinViewModel


@Composable
fun PrettyCardView(
    modifier: Modifier = Modifier,
    index: Int,
    cardItem: CardItems,
    playOrPause: Boolean,
    viewModel: HomeViewmodel = koinViewModel(),
) {
    val context = LocalContext.current
    val builtinVolumes by viewModel.builtinVolumes.collectAsStateWithLifecycle()
    val volume = builtinVolumes[index] ?: 0f
    val shouldPlaySound = volume > 0f
    val shouldUseActiveColor = shouldPlaySound && playOrPause
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        viewModel.onBuiltInCardClick(index)
                    }
                )
                .background(
                    if (shouldUseActiveColor) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else Color.Gray.copy(alpha = 0.1f)
                )
        ) {
            Icon(
                painter = painterResource(id = cardItem.icon),
                contentDescription = stringResource(R.string.content_desc_sound_icon),
                modifier = Modifier.size(40.dp),
                tint = if (shouldUseActiveColor) MaterialTheme.colorScheme.primary else Color.Gray.copy(.5f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = cardItem.localizedTitle(),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        Slider(
            colors = SliderDefaults.colors(
                thumbColor = when (playOrPause) {
                    true -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    else -> Color.Gray.copy(.5f)
                },
                activeTrackColor = if (playOrPause) MaterialTheme.colorScheme.primary else Color.Gray.copy(.5f),
                inactiveTrackColor = Color.Gray.copy(.5f),
                disabledThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                disabledActiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                disabledInactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            ),
            value = volume,
            onValueChange = { newValue ->
                if (playOrPause) {
                    viewModel.setBuiltinVolume(index, newValue)
                }
            },
            onValueChangeFinished = {
                if (!playOrPause) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.cant_play_sound_on_pause),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            valueRange = 0f..1f,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
        )
    }
}
