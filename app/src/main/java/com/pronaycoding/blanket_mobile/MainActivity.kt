package com.pronaycoding.blanket_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pronaycoding.blanket_mobile.ui.nav.Navigation
import com.pronaycoding.blanket_mobile.ui.screens.about.AboutScreen
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.HomeScreenRoute
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.HomeViewmodel
import com.pronaycoding.blanket_mobile.ui.theme.NapifyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

        }
        setContent {
            NapifyAppTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }
}


