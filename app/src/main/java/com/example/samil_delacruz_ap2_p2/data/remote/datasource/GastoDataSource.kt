package com.example.samil_delacruz_ap2_p2.data.remote.datasource


import com.example.samil_delacruz_ap2_p2.data.remote.api.GastosApi
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoRequest
import javax.inject.Inject

class GastoDataSource @Inject constructor(
    private val api: GastosApi
) {
    suspend fun getGastos() = api.getGastos()
    suspend fun getGastoById(id: Int) = api.getGastoById(id)
    suspend fun createGasto(gasto: GastoRequest) = api.createGasto(gasto)
    suspend fun updateGasto(id: Int, gasto: GastoRequest) = api.updateGasto(id, gasto)
}
