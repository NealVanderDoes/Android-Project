package com.example.pizzaclicker

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pizzaclicker.data.UpgradesDataProvider
import com.example.pizzaclicker.model.PizzaViewModel
import com.example.pizzaclicker.ui.screens.HelpScreen
import com.example.pizzaclicker.ui.screens.PizzaClickerScreen
import com.example.pizzaclicker.ui.screens.PreferencesScreen
import com.example.pizzaclicker.ui.screens.UpgradesScreen

enum class PizzaClickerAppScreens(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Help(title = R.string.help),
    Preferences(title = R.string.preferences),
    Upgrades(title = R.string.upgrades)
}

@Composable
fun PizzaClickerApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val viewModel: PizzaViewModel = viewModel()
    val uiState by viewModel.pizzaUiState.collectAsStateWithLifecycle()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        // Broken right now
        topBar = {
            PizzaClickerAppBar(
                currentScreen = PizzaClickerAppScreens.valueOf(
                    backStackEntry?.destination?.route ?: PizzaClickerAppScreens.Start.name
                ),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = modifier
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PizzaClickerAppScreens.Start.name,
            modifier = modifier
                .padding(innerPadding)
        ) {
            composable(route = PizzaClickerAppScreens.Start.name) {
                PizzaClickerScreen(
                    money = uiState.money,
                    pizzaImageId = uiState.pizzaImage,
                    onPizzaClicked = { viewModel.onPizzaClicked() },
                    onUpgradeButtonClicked = {
                        navController
                            .navigate(PizzaClickerAppScreens.Upgrades.name)
                    },
                    onSettingsButtonClicked = {
                        navController
                            .navigate(PizzaClickerAppScreens.Help.name)
                    },
                    modifier = modifier
                )
            }
            composable(route = PizzaClickerAppScreens.Help.name) {
                HelpScreen(
                    onPreferencesButtonClicked = {
                        navController
                            .navigate(PizzaClickerAppScreens.Preferences.name)
                    },
                )
            }
            composable(route = PizzaClickerAppScreens.Preferences.name) {
                PreferencesScreen(
                    onResetClicked = {
                        viewModel.onResetClicked()
                        navController.navigate(
                            PizzaClickerAppScreens.Start.name
                        )
                    },
                    onPrestigeClicked = {
                        viewModel.onPrestigeClicked()
                        navController.navigate(
                            PizzaClickerAppScreens.Start.name
                        )
                    }
                )
            }
            composable(route = PizzaClickerAppScreens.Upgrades.name) {
                UpgradesScreen(
                    upgrades = UpgradesDataProvider.upgrades,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaClickerAppBar(
    currentScreen: PizzaClickerAppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { if (canNavigateBack) {
            Text(stringResource(currentScreen.title)) }},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF2962FF),
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

