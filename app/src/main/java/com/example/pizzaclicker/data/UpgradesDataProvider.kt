package com.example.pizzaclicker.data

import com.example.pizzaclicker.model.Upgrade
import com.example.pizzaclicker.R

object UpgradesDataProvider {
    val upgrades = listOf(
        Upgrade(
            name = R.string.larger_size,
            price = R.string.larger_size_price,
            description = R.string.larger_size_description,
            icon = R.drawable.larger_size
        )
    )
}
