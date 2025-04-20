package com.example.pizzaclicker.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzaclicker.model.Upgrade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizzaclicker.model.PizzaViewModel


@Composable
fun UpgradesScreen(upgrades: List<Upgrade>, modifier: Modifier = Modifier) {
    UpgradesList(
        upgrades = upgrades,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF2962FF))
    )

}

@Composable
fun UpgradesList(upgrades: List<Upgrade>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    ) {
        items(upgrades) { upgrade ->
            UpgradeItem(
                upgrade = upgrade,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun UpgradeItem(upgrade: Upgrade, modifier: Modifier = Modifier) {
    // Important: Reads the current activity instead of creating a new one
    val viewModel: PizzaViewModel = viewModel(LocalActivity.current as ComponentActivity)
    val uiState = viewModel.pizzaUiState.collectAsState()

    val money = uiState.value.money
    val upgradePrice = LocalContext.current.getString(upgrade.price).toInt()
    var isPurchased = upgrade.purchased
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = Color.LightGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(upgrade.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(85.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = LocalContext.current.getString(upgrade.description),
                    modifier = Modifier,
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    if (!isPurchased) {
                        Button(
                            onClick = {
                                if (money >= upgradePrice) {
                                    val newMoney = money - upgradePrice
                                    viewModel.updateMoney(newMoney)
                                    viewModel.buyUpgrade(upgrade)

                                    isPurchased = true // Update the state to reflect the purchase (not working)
                                    println(isPurchased)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                if (money >= upgradePrice)
                                    Color.Green else Color.Gray,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(65.dp, 30.dp),

                            ) {
                            Text(
                                text = "Buy",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$upgradePrice",
                            modifier = Modifier,
                            style = MaterialTheme.typography.labelSmall
                        )
                    } else {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                Color.Gray,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(65.dp, 30.dp)
                        ) {
                            Text(
                                text = "Purchased",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

