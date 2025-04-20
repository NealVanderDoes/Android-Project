package com.example.pizzaclicker.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pizzaclicker.R

// DISPLAY THIS BUTTON INSIDE OF THE SETTINGS BUTTON
@Composable
fun PreferencesScreen(
    onResetClicked: () -> Unit,
    onPrestigeClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2962FF))
    ) {
        Column {
            Spacer(modifier = Modifier.weight(1f))
            Card(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.preferences_warning),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                PrestigeButton(
                    onPrestigeClicked = onPrestigeClicked,
                    modifier = Modifier
                )
                ResetButton(
                    onResetClicked = onResetClicked,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun ResetButton(onResetClicked: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onResetClicked,
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
    Button(
        onClick = onPrestigeClicked,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF66BB6A)
        ),
        modifier = Modifier
            .padding(24.dp)
    ) {
        Text(text = "Prestige")
    }
}