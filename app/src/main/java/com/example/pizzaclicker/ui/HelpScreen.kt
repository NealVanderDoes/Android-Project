package com.example.pizzaclicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.pizzaclicker.R

@Composable
fun HelpScreen(onPreferencesButtonClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2962FF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            AppDesc()
            PrestigeDesc()
            ResetDesc()
            Spacer(modifier = Modifier.weight(1f))
            DisplayPreferencesButton(
                onPreferencesButtonClicked = onPreferencesButtonClicked,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
fun AppDesc(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = "App Description",
            style = MaterialTheme.typography.titleLarge,
            textDecoration = TextDecoration.Underline,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.app_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)

        )
    }
}

@Composable
fun PrestigeDesc(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = "Prestige Description",
            style = MaterialTheme.typography.titleLarge,
            textDecoration = TextDecoration.Underline,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.prestige_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)

        )
    }
}

@Composable
fun ResetDesc(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = "Reset Description",
            style = MaterialTheme.typography.titleLarge,
            textDecoration = TextDecoration.Underline,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.reset_desc),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)

        )
    }
}

@Composable
fun DisplayPreferencesButton(
    onPreferencesButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onPreferencesButtonClicked,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF66BB6A)
        ),
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Preferences")
    }
}
