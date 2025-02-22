package com.pronaycoding.blanket_mobile.common.model

import com.pronaycoding.blanket_mobile.R

sealed class CardItems(
    var title: String,
    var icon: Int,
    val audioSource: Int,
    val type: String = "",
    val firstInType: Boolean = false
) {
    /*
     * Nature
     */
    data object Rain : CardItems(
        title = "Rain",
        icon = R.drawable.rain,
        audioSource = R.raw.nature_rain,
        type = "Nature",
        firstInType = true
    )

    data object SummerNight : CardItems(
        title = "Summer night",
        icon = R.drawable.moon,
        audioSource = R.raw.nature_summernight,
        type = "Nature"
    )

    data object Wind : CardItems(
        title = "Wind",
        icon = R.drawable.wind,
        audioSource = R.raw.nature_wind,
        type = "Nature"
    )

    data object Wave : CardItems(
        title = "Waves",
        icon = R.drawable.wave,
        audioSource = R.raw.nature_waves,
        type = "Nature"
    )

    data object Stream : CardItems(
        title = "Stream",
        icon = R.drawable.stream,
        audioSource = R.raw.nature_stream,
        type = "Nature"
    )

    data object Storm : CardItems(
        title = "Storm",
        icon = R.drawable.storm,
        audioSource = R.raw.nature_storm,
        type = "Nature"
    )

    data object Birds : CardItems(
        title = "Birds",
        icon = R.drawable.birds,
        audioSource = R.raw.nature_birds,
        type = "Nature"
    )

    /*
    Travel
     */

    data object Train : CardItems(
        title = "Train",
        icon = R.drawable.train,
        audioSource = R.raw.travel_train,
        type = "Travel",
        firstInType = true
    )

    data object Boat : CardItems(
        title = "Boat",
        icon = R.drawable.sailboat,
        audioSource = R.raw.travel_boat,
        type = "Travel"
    )

    data object City : CardItems(
        title = "City",
        icon = R.drawable.city,
        audioSource = R.raw.travel_city,
        type = "Travel"
    )


    /*
    Interiors
     */
    data object CoffeeShop : CardItems(
        title = "Coffee Shop",
        icon = R.drawable.coffee,
        audioSource = R.raw.indoor_interior_coffeeshop,
        type = "Interiors",
        firstInType = true
    )

    data object FirePlace : CardItems(
        title = "Fireplace",
        icon = R.drawable.fireplace,
        audioSource = R.raw.indoor_interior_fireplace,
        type = "Interiors"
    )

    data object BusyRestaurant : CardItems(
        title = "Busy Restaurant",
        icon = R.drawable.food_delivery,
        audioSource = R.raw.indoor_busy_restaurant,
        type = "Interiors"
    )


    /*
    Noise
     */
    data object PinkNoise : CardItems(
        title = "Pink Noise",
        icon = R.drawable.pink_noise,
        audioSource = R.raw.noise_pink_noise,
        type = "Noise",
        firstInType = true
    )

    data object WhiteNoise : CardItems(
        title = "White Noise",
        icon = R.drawable.white_noise,
        audioSource = R.raw.noise_white_noise,
        type = "Noise"
    )

    /*
    custom
     */
    data object Custom : CardItems(
        title = "",
        icon = R.drawable.white_noise,
        audioSource = R.raw.noise_white_noise,
        type = "Custom"
    )
}
