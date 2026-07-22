package com.example.samil_delacruz_ap2_p2.domain.repository
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoRequest
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoResponse

interface GastosRepository {
    suspend fun getGastos(): Result<List<GastoResponse>>
    suspend fun getGastoById(id: Int): Result<GastoResponse>
    suspend fun createGasto(gasto: GastoRequest): Result<GastoRequest>
    suspend fun updateGasto(id: Int, gasto: GastoRequest): Result<Unit>
}