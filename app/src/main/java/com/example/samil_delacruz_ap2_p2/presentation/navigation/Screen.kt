package com.example.samil_delacruz_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ListScreen : Screen()

    @Serializable
    data class DetailScreen(val id: Int) : Screen()

}