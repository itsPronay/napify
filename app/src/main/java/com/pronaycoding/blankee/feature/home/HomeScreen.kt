package com.pronaycoding.blankee.feature.home

/**
 * Home screen composable for the Blankee application.
 *
 * The main feature screen displaying:
 * - Sound categories (Nature, Travel, Interiors, Noise)
 * - Built-in ambient sounds with individual volume controls
 * - Custom user-uploaded sounds
 * - Sound selection and mixing interface
 * - Integration with playback controls (play/pause, reset, timer)
 *
 * Features:
 * - LazyColumn layout for efficient scrolling through sounds
 * - Real-time volume adjustments
 * - Custom sound upload capability
 * - Preset management (save/load/delete)
 * - Top app bar with media controls
 *
 * State management via [HomeViewmodel]:
 * - Built-in sound volumes
 * - Custom sound volumes
 * - Playback state
 * - Preset management
 * - Sleep timer
 *
 * @see HomeViewmodel for screen state and business logic
 * @see BlankeeTopAppBar for media controls
 * @see PrettyCardView for sound card UI
 * @see CustomSoundCard for custom sound card UI
 */

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pronaycoding.blankee.R
import com.pronaycoding.blankee.core.common.util.findActivity
import com.pronaycoding.blankee.core.database.entities.CustomSoundEntity
import com.pronaycoding.blankee.core.model.CardItems
import com.pronaycoding.blankee.core.ui.components.BlankeeTopAppBar
import com.pronaycoding.blankee.core.ui.components.CustomSoundCard
import com.pronaycoding.blankee.core.ui.components.PrettyCardView
import com.pronaycoding.blankee.core.ui.components.TitleCardView
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoute(
    navigateToSettings: () -> Unit,
    viewmodel: HomeViewmodel = koinViewModel()
) {
    val canPlaySound by viewmodel.canPlay.collectAsStateWithLifecycle()
    val customSounds by viewmodel.customSounds.collectAsStateWithLifecycle()
    val customSoundsUnlocked by viewmodel.customSoundsUnlocked.collectAsStateWithLifecycle()
    val sleepTimerRemainingMillis by viewmodel.sleepTimerRemainingMillis.collectAsStateWithLifecycle()

    HomeScreen(
        navigateToSettings = navigateToSettings,
        canPlaySound = canPlaySound,
        customSoundsUnlocked = customSoundsUnlocked,
        customSounds = customSounds,
        onAddCustomSound = { displayName, filePath -> viewmodel.addCustomSound(displayName, filePath) },
        onDeleteCustomSound = { soundId -> viewmodel.removeCustomSound(soundId) },
        onLaunchPremiumPurchase = { activity -> viewmodel.launchPremiumPurchase(activity) },
        sleepTimerRemainingMillis = sleepTimerRemainingMillis,
        onCancelSleepTimer = { viewmodel.cancelSleepTimer() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    navigateToSettings: () -> Unit,
    canPlaySound: Boolean,
    customSoundsUnlocked: Boolean,
    customSounds: List<CustomSoundEntity> = emptyList(),
    onAddCustomSound: (String, String) -> Unit = { _, _ -> },
    onDeleteCustomSound: (Int) -> Unit = {},
    onLaunchPremiumPurchase: (Activity) -> Unit = {},
    sleepTimerRemainingMillis: Long? = null,
    onCancelSleepTimer: () -> Unit = {}
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var showCancelTimerDialog by remember { mutableStateOf(false) }

    val audioPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val defaultName = context.getString(
                R.string.custom_sound_default_name,
                customSounds.size + 1
            )
            val displayName = defaultName
            val filePath = it.toString()
            if (filePath.isNotEmpty()) {
                onAddCustomSound(displayName, filePath)
                Toast.makeText(
                    context,
                    context.getString(R.string.sound_added, displayName),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.could_not_add_sound),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    val scheme = MaterialTheme.colorScheme

    Scaffold(
//        containerColor = scheme.background,
//        topBar = {
//            BlankeeTopAppBar(
//                navigateToSettings = navigateToSettings
//            )
//        },
        bottomBar = {
            BlankeeTopAppBar(
                navigateToSettings = navigateToSettings
            )
//            val madeBy = AnnotatedString.Builder().apply {
//                append("Made with ❤️ by ")
//                pushStringAnnotation(tag = "pronay", annotation = "pronay")
//                withStyle(
//                    SpanStyle(
//                        color = MaterialTheme.colorScheme.primary,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                ) {
//                    append("Pronay")
//                }
//                pop()
//            }.toAnnotatedString()
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(MaterialTheme.colorScheme.background)
//                    .padding(horizontal = 16.dp, vertical = 10.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                ClickableText(
//                    text = madeBy,
//                    style = MaterialTheme.typography.bodySmall.copy(
//                        color = MaterialTheme.colorScheme.onSurfaceVariant,
//                        textAlign = TextAlign.Center
//                    ),
//                    onClick = { offset ->
//                        madeBy.getStringAnnotations(
//                            tag = "pronay",
//                            start = offset,
//                            end = offset
//                        ).firstOrNull()?.let {
//                            openExternalUrl(context, Constants.PRONAY_GITHUB)
//                        }
//                    }
//                )
//            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                content = {
                    if (sleepTimerRemainingMillis != null) {
                        item {
                            CustomCard {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = stringResource(
                                            R.string.timer_countdown_label,
                                            formatTimer(sleepTimerRemainingMillis)
                                        ),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    IconButton(onClick = { showCancelTimerDialog = true }) {
                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            contentDescription = stringResource(R.string.timer_cancel)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        CustomCard {
                            TwoColumnGrid(
                                items = homeGridCardItems(customSoundsUnlocked),
                                canPlaySound = canPlaySound
                            )
                        }
                    }

                    if (customSoundsUnlocked && customSounds.isNotEmpty()) {
                        item {
                            TitleCardView(stringResource(R.string.category_custom))
                            CustomCard {
                                val chunks = customSounds.chunked(2)
                                chunks.forEach { chunk ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        chunk.forEach { customSound ->
                                            CustomSoundCard(
                                                modifier = Modifier.weight(1f),
                                                soundId = customSound.id,
                                                displayName = customSound.displayName,
                                                playOrPause = canPlaySound,
                                                onDeleteClick = onDeleteCustomSound
                                            )
                                        }
                                        // Fill empty slot if odd number
                                        if (chunk.size == 1) {
                                            Spacer(modifier = Modifier.weight(1f))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))

                        if (!customSoundsUnlocked) {
                            Text(
                                text = stringResource(R.string.custom_sounds_premium_message),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    context.findActivity()?.let { onLaunchPremiumPurchase(it) }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    stringResource(R.string.buy_premium),
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        } else {
                            Button(
                                onClick = { audioPickerLauncher.launch("audio/*") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = stringResource(R.string.content_desc_add_custom_sound),
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    stringResource(R.string.add_custom_sound),
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            )
        }
    }

    if (showCancelTimerDialog) {
        AlertDialog(
            onDismissRequest = { showCancelTimerDialog = false },
            title = { Text(stringResource(R.string.timer_cancel_confirm_title)) },
            text = { Text(stringResource(R.string.timer_cancel_confirm_message)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onCancelSleepTimer()
                        showCancelTimerDialog = false
                    }
                ) {
                    Text(stringResource(R.string.timer_cancel))
                }
            },
            dismissButton = {
                TextButton(onClick = { showCancelTimerDialog = false }) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        )
    }
}

private fun formatTimer(remainingMillis: Long): String {
    val totalSeconds = (remainingMillis / 1000L).coerceAtLeast(0L)
    val hours = totalSeconds / 3600L
    val minutes = (totalSeconds % 3600L) / 60L
    val seconds = totalSeconds % 60L
    return if (hours > 0L) {
        "%d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%02d:%02d".format(minutes, seconds)
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
private fun TwoColumnGrid(
    items: List<CardItems>,
    canPlaySound: Boolean
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        // 'this' is BoxWithConstraintsScope, maxWidth is available here
        val columns = when {
            maxWidth >= 840.dp -> 4
            maxWidth >= 600.dp -> 3
            else -> 2
        }

        Column {
            val chunks = items.chunked(columns)
            chunks.forEach { chunk ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    chunk.forEach { cardItem ->
                        PrettyCardView(
                            modifier = Modifier.weight(1f),
                            index = getCardList().indexOf(cardItem),
                            cardItem = cardItem,
                            playOrPause = canPlaySound
                        )
                    }
                    repeat(columns - chunk.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

fun getCardList(): List<CardItems> = listOf(
    CardItems.Rain, CardItems.Wind, CardItems.Storm,
    CardItems.Wave, CardItems.Stream, CardItems.Birds, CardItems.SummerNight,
    CardItems.Train, CardItems.Boat, CardItems.City,
    CardItems.CoffeeShop, CardItems.FirePlace, CardItems.BusyRestaurant,
    CardItems.PinkNoise, CardItems.WhiteNoise, CardItems.Custom
)

private fun homeGridCardItems(customSoundsUnlocked: Boolean): List<CardItems> =
    if (!customSoundsUnlocked) {
        getCardList().filter { it != CardItems.Custom }
    } else {
        getCardList()
    }

@Composable
fun CustomCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}
