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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.pronaycoding.blanket_mobile.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "About us")
        }, navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
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
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(
                    id = R.drawable.logo_
                ),
                contentDescription = "",
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Blanket Mobile",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Listen to different sounds. Improve focus and increase your productivity.",
                color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
            )


            Spacer(modifier = Modifier.height(16.dp))

            Card(
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "App Version 1.0", style = MaterialTheme.typography.bodyLarge
                    )
                }
            }


            Spacer(modifier = Modifier.height(2.dp))

            Card(
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF27a157).copy(alpha = .6f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "View License", style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Card(
                onClick = {

                    val url = "https://github.com/itsPronay/Blanket-Mobile/issues"
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No application can handle this request. Please install a web browser.",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "An unexpected error occurred.", Toast.LENGTH_LONG)
                            .show()
                    }

                }, elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Request feature", style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Card(
                onClick = {
                    val url = "https://github.com/itsPronay/Blanket-Mobile"
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No application can handle this request. Please install a web browser.",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "An unexpected error occurred.", Toast.LENGTH_LONG)
                            .show()
                    }
                }, elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Source code", style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "View source code of this app in github",
                        color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Card(
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Credits", style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "This app is a clone of Rafael Mardojai's Blanket, which is originally a GNOME application.",
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