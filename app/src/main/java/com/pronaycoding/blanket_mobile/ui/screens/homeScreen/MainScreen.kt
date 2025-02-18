package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pronaycoding.blanket_mobile.common.Utils
import com.pronaycoding.blanket_mobile.common.components.NapifyTopAppBar
import com.pronaycoding.blanket_mobile.common.components.PrettyCardView
import com.pronaycoding.blanket_mobile.common.components.TitleCardView
import com.pronaycoding.blanket_mobile.common.model.CardItems
import kotlinx.coroutines.launch

@Composable
fun HomeScreenRoute(
    viewModel: HomeViewmodel = hiltViewModel(),
    navigateToAboutScreen : () -> Unit,
    navigateToSettings : ( ) -> Unit
) {
    val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        resetAllSongs = viewModel::resetSongs,
        isAnySongPlaying = viewModel::isAnySongPlaying,
        pauseAllSongs = viewModel::pauseAllSongs,
        resumeAllSongs = viewModel::resumeAllSound,
        navigateToAboutScreen = navigateToAboutScreen,
        navigateToSettings = navigateToSettings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: MainUiState,
    resetAllSongs: () -> Unit,
    isAnySongPlaying: () -> Boolean,
    pauseAllSongs: () -> Unit,
    resumeAllSongs: () -> Unit,
    navigateToAboutScreen : () -> Unit,
    navigateToSettings : ( ) -> Unit
) {

    var scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val cardLists = getCardList()
    var previousCardTitle by rememberSaveable { mutableStateOf("") }
    var canPlayMusic by rememberSaveable { mutableStateOf(true) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selectedDrawerIndex by rememberSaveable { mutableStateOf(-1 ) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(200.dp)
            ) {
                Text("Napify", modifier = Modifier.padding(horizontal = 16.dp))

//                Icon(imageVector = Icons.Default.Info, contentDescription = "")
                HorizontalDivider()

                Utils.getDrawerItems().forEachIndexed { index, drawerItems ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        icon = {
                            Icon(drawerItems.icon, contentDescription = "")
                        },
                        label = { Text(text = drawerItems.title) },
                        selected = index == selectedDrawerIndex,
                        onClick = { selectedDrawerIndex = index}
                    )
                }
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                NapifyTopAppBar(
                    scrollBehavior = scrollBehavior,
                    resumeAllSounds = resumeAllSongs,
                    pauseAllSounds = pauseAllSongs,
                    resetSongs = resetAllSongs,
                    navigationButtonClicked = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    navigateToAboutScreen = navigateToAboutScreen,
                    navigateToSettingsScreen = navigateToSettings
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                        )
                ) {

                    item {
                        TitleCardView("Nature")

                        CustomCard {
                            getNatureCards().forEachIndexed { _, cardItems ->
                                PrettyCardView(
                                    uiState = uiState,
                                    index = getCardList().indexOf(cardItems),
                                    cardItem = cardItems,
                                )
                            }
                        }
                    }

                    item {
                        TitleCardView("Travel")

                        CustomCard {
                            getTravelCards().forEachIndexed { _, cardItems ->
                                PrettyCardView(
                                    uiState = uiState,
                                    index = getCardList().indexOf(cardItems),
                                    cardItem = cardItems,
                                )
                            }
                        }
                    }

                    item {
                        TitleCardView("Indoor")

                        CustomCard {
                            getIndoorCards().forEachIndexed { _, cardItems ->
                                PrettyCardView(
                                    uiState = uiState,
                                    index = getCardList().indexOf(cardItems),
                                    cardItem = cardItems,
                                )
                            }
                        }
                    }

                    item {
                        TitleCardView("Noise")

                        CustomCard {
                            getNoiseCards().forEachIndexed { _, cardItems ->
                                PrettyCardView(
                                    uiState = uiState,
                                    index = getCardList().indexOf(cardItems),
                                    cardItem = cardItems,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getNatureCards(): List<CardItems> {
    return listOf(
        CardItems.Rain,
        CardItems.Wind,
        CardItems.Storm,
        CardItems.Wave,
        CardItems.Stream,
        CardItems.Birds,
        CardItems.SummerNight
    )
}

fun getTravelCards(): List<CardItems> {
    return listOf(
        CardItems.Train,
        CardItems.Boat,
        CardItems.City
    )
}

fun getIndoorCards(): List<CardItems> {
    return listOf(
        CardItems.CoffeeShop,
        CardItems.FirePlace,
        CardItems.BusyRestaurant
    )
}

fun getNoiseCards(): List<CardItems> {
    return listOf(
        CardItems.PinkNoise,
        CardItems.WhiteNoise
    )
}

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            content()
        }
    }
}
