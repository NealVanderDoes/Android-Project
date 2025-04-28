package com.example.pizzaclicker.model

import androidx.lifecycle.ViewModel
import com.example.pizzaclicker.data.UpgradeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UpgradeViewModel : ViewModel() {
    private val _upgradeUiState = MutableStateFlow(UpgradeUiState())
    val upgradeUiState: StateFlow<UpgradeUiState> = _upgradeUiState.asStateFlow()

    fun onUpgradeClicked() {
        _upgradeUiState.update { upgradeUiState ->
            upgradeUiState.copy(
                purchased = true
            )
        }
    }
}