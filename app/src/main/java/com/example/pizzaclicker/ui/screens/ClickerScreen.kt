package com.example.pizzaclicker.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pizzaclicker.R


@Composable
fun PizzaClickerScreen(
    money: Int,
    @DrawableRes pizzaImageId: Int,
    onSettingsButtonClicked: () -> Unit,
    onUpgradeButtonClicked: () -> Unit,
    onPizzaClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = Color(0xFF2962FF))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DisplayMoney(money = money)
                DisplaySettingsButton(
                    onSettingsButtonClicked = onSettingsButtonClicked,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Image(
                    painter = painterResource(pizzaImageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .align(Alignment.Center)
                        .clickable { onPizzaClicked() },
                    contentScale = ContentScale.Crop,
                )
                DisplayUpgradesButton(
                    onUpgradeButtonClicked = onUpgradeButtonClicked,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun DisplayMoney(
    money: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFF2962FF)),
    ) {
        MoneyInfo(money)
    }
}

@Composable
fun MoneyInfo(
    money: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),

        ) {
        Image(
            painter = painterResource(R.drawable.currency),
            contentDescription = null,
            modifier = modifier
                .width(25.dp)
                .height(25.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "$money",
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = modifier

        )
    }
}

@Composable
fun DisplaySettingsButton(
    onSettingsButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onSettingsButtonClicked,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2962FF),
            contentColor = Color.Black
        ),
        modifier = modifier
            .padding(8.dp)
    ) {
        Image(
            painterResource(R.drawable.baseline_settings_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier
        )
    }
}

@Composable
fun DisplayUpgradesButton(
    onUpgradeButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onUpgradeButtonClicked,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF66BB6A)
        ),
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Upgrade")
    }
}
