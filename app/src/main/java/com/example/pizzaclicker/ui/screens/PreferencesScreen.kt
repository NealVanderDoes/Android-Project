package com.example.pizzaclicker.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizzaclicker.R
import com.example.pizzaclicker.model.PizzaViewModel
import com.example.pizzaclicker.model.UpgradeViewModel

@Composable
fun PreferencesScreen(
    onResetClicked: () -> Unit,
    onPrestigeClicked: () -> Unit,
    money: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF2962FF))
    ) {
        Column {
            DisplayMoney(money = money)
            Spacer(modifier = modifier.weight(1f))
            Card(
                shape = MaterialTheme.shapes.small,
                modifier = modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.preferences_warning),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                PrestigeButton(
                    onPrestigeClicked = onPrestigeClicked,
                    modifier = modifier
                )
                ResetButton(
                    onResetClicked = onResetClicked,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ResetButton(onResetClicked: () -> Unit, modifier: Modifier = Modifier) {
    val viewModel: PizzaViewModel = viewModel(LocalActivity.current as ComponentActivity)
    val upgradeViewModel: UpgradeViewModel = viewModel(LocalActivity.current as ComponentActivity)

    Button(
        onClick = {
            viewModel.onResetClicked()
            upgradeViewModel.resetUpgrades()
            onResetClicked()
        },
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF66BB6A)
        ),
        modifier = modifier
            .padding(24.dp)

    ) {
        Text(text = "Reset")
    }
}

@Composable
fun PrestigeButton(onPrestigeClicked: () -> Unit, modifier: Modifier = Modifier) {
    val viewModel: PizzaViewModel = viewModel(LocalActivity.current as ComponentActivity)
    val upgradeViewModel: UpgradeViewModel = viewModel(LocalActivity.current as ComponentActivity)
    val uiState = viewModel.pizzaUiState.collectAsState()
    val money = uiState.value.money
    val prestiged = uiState.value.prestiged

    val prestigeCost = 150 // Temp value. Correct number should be 10_000

    Button(
        onClick = {
            if (prestiged) {
                return@Button
            } else if (money >= prestigeCost) {
//                viewModel.updateMoney(0)
                viewModel.onResetClicked()
                upgradeViewModel.resetUpgrades()
                onPrestigeClicked()
            }
        },
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            if (prestiged) Color.Gray
            else if (money >= prestigeCost)
                Color(0xFF66BB6A) else Color.Gray,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(24.dp)
    ) {
        if (!prestiged) {
            Text(text = "Prestige: $$prestigeCost")
        } else
            Text(text = "Prestige: Unlocked")
    }
}