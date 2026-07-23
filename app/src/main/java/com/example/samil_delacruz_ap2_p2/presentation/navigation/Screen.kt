package com.example.samil_delacruz_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object GastoList : Screen()

    @Serializable
    data object GastoCreate : Screen()

    @Serializable
    data class GastoEdit(val id: Int) : Screen()
}
