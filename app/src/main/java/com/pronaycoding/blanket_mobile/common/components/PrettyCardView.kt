package com.pronaycoding.blanket_mobile.common.components

//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pronaycoding.blanket_mobile.common.model.CardItems
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.BlanketViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrettyCardView(
    index: Int,
    cardItem: CardItems,
    viewModel: BlanketViewModel = viewModel()
) {
    var isPlaying by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    var sliderValue by rememberSaveable { mutableFloatStateOf(0F) }
    var tempValue by rememberSaveable { mutableFloatStateOf(0F) }


    LaunchedEffect(key1 = isPlaying) {
        if (isPlaying) {
            viewModel.playSound(index, tempValue)
        } else viewModel.stopSound(index)
    }

//    if (!isPlaying) {
//        Log.d("debugUwU", "playing sound  1 ")
//        viewModel.playSound(index)
//    }

    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = cardItem.icon),
                    contentDescription = "Icon",
                    modifier = Modifier.size(32.dp),
                    tint = if (isPlaying) Color(0xFF27a157) else MaterialTheme.colorScheme.onBackground
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
                    thumbColor = Color(0xFF27a157),
                    activeTrackColor = Color(0xFF27a157),
                    inactiveTrackColor = Color(0xFF27a157).copy(alpha = .2f)
                ),
                value = tempValue,
                onValueChange = { newValue ->
                    tempValue = newValue
                    isPlaying = if (newValue > 0) {
                        true
                    } else false
                    /**
                     * sets volume if playing
                     * else it skips
                     */
                    viewModel.setVolume(index, tempValue)
                },
                onValueChangeFinished = {

                },
                valueRange = 0f..1f,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(20.dp)
            )
        }
    }
}
