package com.example.samil_delacruz_ap2_p2.presentation.list

import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoResponse

data class GastoListUiState(
    val isLoading: Boolean = false,
    val gastos: List<GastoResponse> = emptyList(),
    val error: String? = null
)
