package com.example.pizzaclicker.data

import androidx.annotation.DrawableRes
import com.example.pizzaclicker.data.PizzaListDatasource.pizzaList

data class PizzaUiState(
    var prestiged: Boolean = false,
    val currentPizzaIndex: Int = 0,
    val upgradesPurchased: Int = 0,
    val money: Int = 0,
    val moneyPerClick: Int = pizzaList[currentPizzaIndex].price,
    @DrawableRes val pizzaImage: Int = pizzaList[currentPizzaIndex].imageId,
)
