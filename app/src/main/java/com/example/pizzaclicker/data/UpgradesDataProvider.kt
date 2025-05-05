package com.example.pizzaclicker.data

import com.example.pizzaclicker.model.Upgrade
import com.example.pizzaclicker.R

object UpgradesDataProvider {
    val upgrades = listOf(
        Upgrade(
            name = R.string.pepperoni_and_cheese_pizza,
            price = R.string.pepperoni_and_cheese_pizza_price,
            description = R.string.pepperoni_and_cheese_pizza_description,
            icon = R.drawable.pepperoni_and_cheese_pizza,
        )
    )
}
