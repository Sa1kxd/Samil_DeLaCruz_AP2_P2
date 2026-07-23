package com.example.samil_delacruz_ap2_p2.presentation.gasto

data class GastoUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val fecha: String = "2026-07-22T00:00:00",
    val suplidor: String = "",
    val ncf: String = "",
    val itbis: Double = 0.0,
    val monto: Double = 0.0,
    val suplidorError: String? = null,
    val montoError: String? = null
)
