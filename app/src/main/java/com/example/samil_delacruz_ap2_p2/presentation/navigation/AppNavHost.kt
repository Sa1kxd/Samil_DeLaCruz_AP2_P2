package com.example.samil_delacruz_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.samil_delacruz_ap2_p2.presentation.gasto.GastoScreen
import com.example.samil_delacruz_ap2_p2.presentation.list.GastoListScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.GastoList
    ) {
        composable<Screen.GastoList> {
            GastoListScreen(
                onAddGasto = {
                    navHostController.navigate(Screen.GastoCreate)
                },
                onEditGasto = { id ->
                    navHostController.navigate(Screen.GastoEdit(id))
                }
            )
        }

        composable<Screen.GastoCreate> {
            GastoScreen(
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.GastoEdit> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.GastoEdit>()
            GastoScreen(
                id = args.id,
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
