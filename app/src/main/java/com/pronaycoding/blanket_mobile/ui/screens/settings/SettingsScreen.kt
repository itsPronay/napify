package com.pronaycoding.blanket_mobile.ui.screens.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.BrightnessAuto
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pronaycoding.blanket_mobile.R
import com.pronaycoding.blanket_mobile.core.locale.AppLanguageStore
import com.pronaycoding.blanket_mobile.core.theme.AppThemeStore

private data class ThemeChoice(
    val mode: String,
    val labelRes: Int
)

private data class LanguageChoice(
    val tag: String,
    val labelRes: Int
)

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val scheme = MaterialTheme.colorScheme
    val themeChoices = remember {
        listOf(
            ThemeChoice(AppThemeStore.MODE_LIGHT, R.string.theme_light),
            ThemeChoice(AppThemeStore.MODE_DARK, R.string.theme_dark),
            ThemeChoice(AppThemeStore.MODE_SYSTEM, R.string.theme_system),
        )
    }
    val languageChoices = remember {
        listOf(
            LanguageChoice(AppLanguageStore.TAG_SYSTEM, R.string.language_system),
            LanguageChoice(AppLanguageStore.TAG_ENGLISH, R.string.language_english),
            LanguageChoice(AppLanguageStore.TAG_HINDI, R.string.language_hindi),
            LanguageChoice(AppLanguageStore.TAG_BENGALI, R.string.language_bengali),
            LanguageChoice(AppLanguageStore.TAG_SPANISH, R.string.language_spanish),
        )
    }
    var selectedTheme by remember {
        mutableStateOf(AppThemeStore.getSavedMode(context))
    }
    var selectedLanguage by remember {
        mutableStateOf(AppLanguageStore.getSavedTag(context))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = scheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = scheme.surface,
                    scrolledContainerColor = scheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.settings_section_display),
                style = MaterialTheme.typography.labelLarge,
                color = scheme.primary,
                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = scheme.surfaceContainerLow
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.settings_theme),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = stringResource(R.string.settings_theme_hint),
                        style = MaterialTheme.typography.bodySmall,
                        color = scheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 6.dp, bottom = 16.dp)
                    )

                    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                        themeChoices.forEachIndexed { index, choice ->
                            val selected = selectedTheme == choice.mode
                            SegmentedButton(
                                selected = selected,
                                onClick = {
                                    if (selectedTheme == choice.mode) return@SegmentedButton
                                    selectedTheme = choice.mode
                                    AppThemeStore.setSavedMode(context, choice.mode)
                                    context.findActivity()?.recreate()
                                },
                                shape = SegmentedButtonDefaults.itemShape(
                                    index = index,
                                    count = themeChoices.size
                                ),
                                colors = SegmentedButtonDefaults.colors(
                                    activeContainerColor = scheme.primaryContainer,
                                    activeContentColor = scheme.onPrimaryContainer,
                                    inactiveContainerColor = scheme.surfaceContainerHighest,
                                    inactiveContentColor = scheme.onSurface
                                )
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = when (choice.mode) {
                                            AppThemeStore.MODE_LIGHT -> Icons.Outlined.LightMode
                                            AppThemeStore.MODE_DARK -> Icons.Outlined.DarkMode
                                            else -> Icons.Outlined.BrightnessAuto
                                        },
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.size(6.dp))
                                    Text(
                                        text = stringResource(choice.labelRes),
                                        style = MaterialTheme.typography.labelLarge,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.settings_section_language),
                style = MaterialTheme.typography.labelLarge,
                color = scheme.primary,
                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp, top = 4.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = scheme.surfaceContainerLow
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = stringResource(R.string.settings_language),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                    )
                    Text(
                        text = stringResource(R.string.settings_language_hint),
                        style = MaterialTheme.typography.bodySmall,
                        color = scheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 8.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        languageChoices.forEach { choice ->
                            val label = stringResource(choice.labelRes)
                            val selected = selectedLanguage == choice.tag
                            Surface(
                                onClick = {
                                    if (selectedLanguage == choice.tag) return@Surface
                                    selectedLanguage = choice.tag
                                    AppLanguageStore.setSavedTag(context, choice.tag)
                                    context.findActivity()?.recreate()
                                },
                                shape = MaterialTheme.shapes.large,
                                color = if (selected) {
                                    scheme.primary.copy(alpha = 0.14f)
                                } else {
                                    scheme.surfaceContainerHighest.copy(alpha = 0.45f)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                                        color = scheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (selected) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = scheme.primary,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
