package com.pronaycoding.blanket_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import com.pronaycoding.blanket_mobile.ui.screens.about.AboutScreen
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.HomeScreenRoute
import com.pronaycoding.blanket_mobile.ui.theme.NapifyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NapifyAppTheme {
                Surface {
                    AboutScreen(
                        onBackPressed = {}
                    )
                }
            }
        }
    }
}


