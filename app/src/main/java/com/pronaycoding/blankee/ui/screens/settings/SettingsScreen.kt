package com.pronaycoding.blankee.ui.screens.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pronaycoding.blankee.R
import com.pronaycoding.blankee.core.PreferenceManagerRepositoryImpl
import com.pronaycoding.blankee.core.constants.AppLinks
import com.pronaycoding.blankee.core.locale.AppLanguageStore
import com.pronaycoding.blankee.core.util.openExternalUrl
import org.koin.androidx.compose.koinViewModel

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
    onBackPressed: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val scheme = MaterialTheme.colorScheme
    val themeChoices = remember {
        listOf(
            ThemeChoice(PreferenceManagerRepositoryImpl.MODE_LIGHT, R.string.theme_light),
            ThemeChoice(PreferenceManagerRepositoryImpl.MODE_DARK, R.string.theme_dark),
            ThemeChoice(PreferenceManagerRepositoryImpl.MODE_SYSTEM, R.string.theme_system),
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
    val selectedTheme by viewModel.selectedTheme.collectAsStateWithLifecycle()
    val selectedLanguage by viewModel.selectedLanguage.collectAsStateWithLifecycle()

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
                                    viewModel.updateTheme(choice.mode)
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
                                            PreferenceManagerRepositoryImpl.MODE_LIGHT -> Icons.Outlined.LightMode
                                            PreferenceManagerRepositoryImpl.MODE_DARK -> Icons.Outlined.DarkMode
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
                                    viewModel.updateLanguage(choice.tag)
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

            Text(
                text = stringResource(R.string.settings_section_others),
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
                Surface(
                    onClick = { openExternalUrl(context, AppLinks.GITHUB_REPO) },
                    color = androidx.compose.ui.graphics.Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.settings_report_bug_feature),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = stringResource(R.string.settings_report_bug_feature_hint),
                            style = MaterialTheme.typography.bodySmall,
                            color = scheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            AttributionFooter(
                onOpenRafael = { openExternalUrl(context, AppLinks.RAFAEL_MARDOJAI_GITHUB) },
                onOpenPronay = { openExternalUrl(context, AppLinks.PRONAY_GITHUB) },
            )
        }
    }
}

@Composable
private fun AttributionFooter(
    onOpenRafael: () -> Unit,
    onOpenPronay: () -> Unit,
) {
    val scheme = MaterialTheme.colorScheme

    Spacer(modifier = Modifier.height(12.dp))

    val linkStyle = SpanStyle(
        color = scheme.primary,
        fontWeight = FontWeight.SemiBold
    )

    val inspired = AnnotatedString.Builder().apply {
        append("Inspired by ")
        pushStringAnnotation(tag = "rafael", annotation = "rafael")
        withStyle(linkStyle) { append("Rafael Mardojai") }
        pop()
        append("'s Blanket")
    }.toAnnotatedString()

    ClickableText(
        text = inspired,
        style = MaterialTheme.typography.bodySmall.copy(
            color = scheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = { offset ->
            inspired.getStringAnnotations(tag = "rafael", start = offset, end = offset)
                .firstOrNull()
                ?.let { onOpenRafael() }
        }
    )

    val madeBy = AnnotatedString.Builder().apply {
        append("Made with ❤️ by ")
        pushStringAnnotation(tag = "pronay", annotation = "pronay")
        withStyle(linkStyle) { append("Pronay") }
        pop()
    }.toAnnotatedString()

    ClickableText(
        text = madeBy,
        style = MaterialTheme.typography.bodySmall.copy(
            color = scheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 8.dp),
        onClick = { offset ->
            madeBy.getStringAnnotations(tag = "pronay", start = offset, end = offset)
                .firstOrNull()
                ?.let { onOpenPronay() }
        }
    )
}
