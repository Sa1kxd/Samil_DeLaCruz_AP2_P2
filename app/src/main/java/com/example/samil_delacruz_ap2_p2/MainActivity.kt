package com.example.samil_delacruz_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.samil_delacruz_ap2_p2.presentation.navigation.AppNavHost
import com.example.samil_delacruz_ap2_p2.ui.theme.Samil_DeLaCruz_AP2_P2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Samil_DeLaCruz_AP2_P2Theme {
                val navController = rememberNavController()
                AppNavHost(navHostController = navController)
            }
        }
    }
}
