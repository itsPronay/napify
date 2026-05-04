package com.pronaycoding.blanket_mobile.ui.screens.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pronaycoding.blanket_mobile.BuildConfig
import com.pronaycoding.blanket_mobile.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(R.string.about_title))
        }, navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_back)
                )
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally).clip(
                        RoundedCornerShape(30.dp)
                    ),
                painter = painterResource(
                    id = R.drawable.logo
                ),
                contentDescription = stringResource(R.string.content_desc_logo),
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.app_description),
                color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedCard(
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.about_version, BuildConfig.VERSION_NAME),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedCard(
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.inspired_text),
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedCard(
                onClick = {
                    val url = "https://github.com/itsPronay/napify"
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.no_browser_installed),
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_unexpected),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.about_source_code),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = stringResource(R.string.about_source_code_subtitle),
                        color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                    )
                }
            }
        }
    }
}



@Composable
@Preview(showSystemUi = true)
fun AboutScreenPreview(modifier: Modifier = Modifier) {
    AboutScreen(onBackPressed = {})
}