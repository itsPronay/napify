package com.pronaycoding.blanket_mobile.common.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pronaycoding.blanket_mobile.R

sealed class CardItems(
    @StringRes val titleResId: Int,
    var icon: Int,
    val audioSource: Int,
    val type: String = "",
    val firstInType: Boolean = false,
    val customSoundId: Int? = null,
    val filePath: String? = null
) {
    @Composable
    fun localizedTitle(): String = when (this) {
        is CustomCardItem -> displayName
        else -> stringResource(titleResId)
    }

    data object Rain : CardItems(
        titleResId = R.string.sound_rain,
        icon = R.drawable.rain,
        audioSource = R.raw.nature_rain,
        type = "Nature",
        firstInType = true
    )

    data object SummerNight : CardItems(
        titleResId = R.string.sound_summer_night,
        icon = R.drawable.moon,
        audioSource = R.raw.nature_summernight,
        type = "Nature"
    )

    data object Wind : CardItems(
        titleResId = R.string.sound_wind,
        icon = R.drawable.wind,
        audioSource = R.raw.nature_wind,
        type = "Nature"
    )

    data object Wave : CardItems(
        titleResId = R.string.sound_wave,
        icon = R.drawable.wave,
        audioSource = R.raw.nature_waves,
        type = "Nature"
    )

    data object Stream : CardItems(
        titleResId = R.string.sound_stream,
        icon = R.drawable.stream,
        audioSource = R.raw.nature_stream,
        type = "Nature"
    )

    data object Storm : CardItems(
        titleResId = R.string.sound_storm,
        icon = R.drawable.storm,
        audioSource = R.raw.nature_storm,
        type = "Nature"
    )

    data object Birds : CardItems(
        titleResId = R.string.sound_birds,
        icon = R.drawable.birds,
        audioSource = R.raw.nature_birds,
        type = "Nature"
    )

    data object Train : CardItems(
        titleResId = R.string.sound_train,
        icon = R.drawable.train,
        audioSource = R.raw.travel_train,
        type = "Travel",
        firstInType = true
    )

    data object Boat : CardItems(
        titleResId = R.string.sound_boat,
        icon = R.drawable.sailboat,
        audioSource = R.raw.travel_boat,
        type = "Travel"
    )

    data object City : CardItems(
        titleResId = R.string.sound_city,
        icon = R.drawable.city,
        audioSource = R.raw.travel_city,
        type = "Travel"
    )

    data object CoffeeShop : CardItems(
        titleResId = R.string.sound_coffee_shop,
        icon = R.drawable.coffee,
        audioSource = R.raw.indoor_interior_coffeeshop,
        type = "Interiors",
        firstInType = true
    )

    data object FirePlace : CardItems(
        titleResId = R.string.sound_fireplace,
        icon = R.drawable.fireplace,
        audioSource = R.raw.indoor_interior_fireplace,
        type = "Interiors"
    )

    data object BusyRestaurant : CardItems(
        titleResId = R.string.sound_busy_restaurant,
        icon = R.drawable.food_delivery,
        audioSource = R.raw.indoor_busy_restaurant,
        type = "Interiors"
    )

    data object PinkNoise : CardItems(
        titleResId = R.string.sound_pink_noise,
        icon = R.drawable.pink_noise,
        audioSource = R.raw.noise_pink_noise,
        type = "Noise",
        firstInType = true
    )

    data object WhiteNoise : CardItems(
        titleResId = R.string.sound_white_noise,
        icon = R.drawable.white_noise,
        audioSource = R.raw.noise_white_noise,
        type = "Noise"
    )

    data object Custom : CardItems(
        titleResId = R.string.empty_string,
        icon = R.drawable.white_noise,
        audioSource = R.raw.noise_white_noise,
        type = "Custom"
    )

    data class CustomCardItem(
        val id: Int,
        val displayName: String,
        val soundFilePath: String
    ) : CardItems(
        titleResId = R.string.empty_string,
        icon = R.drawable.white_noise,
        audioSource = R.raw.noise_white_noise,
        type = "Custom",
        customSoundId = id,
        filePath = soundFilePath
    )
}
