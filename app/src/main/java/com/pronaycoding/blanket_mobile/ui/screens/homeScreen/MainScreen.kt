package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pronaycoding.blanket_mobile.common.components.BlanketTopBar
import com.pronaycoding.blanket_mobile.common.components.PrettyCardView
import com.pronaycoding.blanket_mobile.common.components.TitleCardView
import com.pronaycoding.blanket_mobile.ui.homeScreen.BlanketTabRow

@Composable
@Preview(showSystemUi = true)
fun PreviewScreen(
    viewModel: BlanketViewModel = viewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.initializeSoundpoll(context = context)
    }

    Dashboard()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    viewModel: BlanketViewModel = hiltViewModel()
) {
    var scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val cardLists = getCardList()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BlanketTopBar(
                scrollBehavior = scrollBehavior,
                onMusicActionButtonClick = {
                    if (viewModel.isAnySongPlaying()) viewModel.pauseAllSongs() else viewModel.resumeAllSound()
                },
                isPlaying = viewModel.isAnySongPlaying(),
                resetSongs = { viewModel.resetSongs() }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            BlanketTabRow()
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                items(cardLists) { cardItem ->
                    if (cardItem.firstInType) {
                        TitleCardView(cardItem.type)
                    }
                    if (cardItem.title != "") {
                        PrettyCardView(
                            viewModel = viewModel,
                            index = cardLists.indexOf(cardItem),
                            cardItem = cardItem,
                        )
                    }
                }
            }
        }
    }
}


