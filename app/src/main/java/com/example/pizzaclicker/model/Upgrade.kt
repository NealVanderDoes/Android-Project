package com.example.pizzaclicker.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Upgrade(
    @StringRes val name: Int,
    @StringRes var price: Int,
    @StringRes val description: Int,
    @DrawableRes val icon: Int,
    var purchased: Boolean = false
    )
