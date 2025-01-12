package com.pronaycoding.blanket_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.BlanketViewModel
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.Dashboard
import com.pronaycoding.blanket_mobile.ui.theme.BlanketmobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            BlanketmobileTheme {
                // A surface container using the 'background' color from the theme
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
//                Scaffold(
//                    topBar = {
//                        BlanketTopBar(scrollBehavior = scrollBehavior)
//                    }
//                ) {
                Surface {
                    val viewModel : BlanketViewModel = viewModel()

                    Dashboard()

                }
            }
        }
    }
}


