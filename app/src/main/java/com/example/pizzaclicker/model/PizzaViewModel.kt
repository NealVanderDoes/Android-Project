package com.example.pizzaclicker.model

import androidx.lifecycle.ViewModel
import com.example.pizzaclicker.data.Datasource.pizzaList
import com.example.pizzaclicker.data.PizzaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzaViewModel : ViewModel() {
    private val _pizzaUiState = MutableStateFlow(PizzaUiState())
    val pizzaUiState: StateFlow<PizzaUiState> = _pizzaUiState.asStateFlow()

    fun onPizzaClicked() {
        _pizzaUiState.update { pizzaUiState ->
            val upgradesPurchased = pizzaUiState.upgradesPurchased
            val nextPizzaIndex = determineNextPizzaIndex(upgradesPurchased)
            pizzaUiState.copy(
                currentPizzaIndex = nextPizzaIndex,
                money = pizzaUiState.money + pizzaUiState.moneyPerClick,
                upgradesPurchased = upgradesPurchased,
                pizzaImage = pizzaList[nextPizzaIndex].imageId,
                moneyPerClick = if (_pizzaUiState.value.prestiged) {
                    pizzaList[nextPizzaIndex].price * 2
                } else pizzaList[nextPizzaIndex].price
            )
        }
    }

    private fun determineNextPizzaIndex(upgradesPurchased: Int): Int {
        var pizzaIndex = 0
        for (index in pizzaList.indices) {
            if (upgradesPurchased >= pizzaList[index].upgradeNumber) {
                pizzaIndex = index
            } else {
                break
            }
        }
        return pizzaIndex
    }

    fun updateMoney(newAmount: Int) {
        _pizzaUiState.update { it.copy(money = newAmount) }
    }

    fun buyUpgrade() {
        _pizzaUiState.update { pizzaUiState ->
            pizzaUiState.copy(upgradesPurchased = pizzaUiState.upgradesPurchased + 1)
        }
    }

    fun onResetClicked() {
        TODO()
    }

    fun onPrestigeClicked() {
        _pizzaUiState.update { pizzaUiState ->
            pizzaUiState.copy(
                money = pizzaUiState.money,
                moneyPerClick = pizzaUiState.moneyPerClick,
                prestiged = true
            )
        }
    }
}
