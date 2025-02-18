package com.pronaycoding.blanket_mobile.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pronaycoding.blanket_mobile.common.components.NapifyTopAppBar
import com.pronaycoding.blanket_mobile.common.components.PrettyCardView
import com.pronaycoding.blanket_mobile.common.components.TitleCardView
import com.pronaycoding.blanket_mobile.common.model.CardItems

@Composable
fun HomeScreenRoute(
    navigateToAboutScreen: () -> Unit,
) {

    HomeScreen(
        navigateToAboutScreen = navigateToAboutScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    navigateToAboutScreen: () -> Unit,
) {
    var scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NapifyTopAppBar(
                scrollBehavior = scrollBehavior,
                navigateToAboutScreen = navigateToAboutScreen,
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
