package com.pronaycoding.blanket_mobile

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import com.pronaycoding.blanket_mobile.core.locale.AppLanguageStore
import com.pronaycoding.blanket_mobile.ui.nav.Navigation
import com.pronaycoding.blanket_mobile.ui.theme.NapifyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLanguageStore.wrapContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            NapifyAppTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }
}


