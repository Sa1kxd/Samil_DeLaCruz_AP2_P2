package com.example.samil_delacruz_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.samil_delacruz_ap2_p2.presentation.list.ListScreen
import com.example.samil_delacruz_ap2_p2.presentation.detail.detailScreen
@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.ListScreen) {

        composable<Screen.ListScreen> {
            ListScreen(
                onItemClick = { id -> navHostController.navigate(Screen.DetailScreen(id)) },
            )
        }

        composable<Screen.DetailScreen> {
            Screen.DetailScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }

    }
}