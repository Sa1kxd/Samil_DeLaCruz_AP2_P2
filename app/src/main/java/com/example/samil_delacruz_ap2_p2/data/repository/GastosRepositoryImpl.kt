package com.example.samil_delacruz_ap2_p2.data.repository

import com.example.samil_delacruz_ap2_p2.data.remote.api.GastosApi
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoRequest
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoResponse
import com.example.samil_delacruz_ap2_p2.domain.repository.GastosRepository
import javax.inject.Inject

class GastosRepositoryImpl @Inject constructor(
    private val api: GastosApi
) : GastosRepository {

    override suspend fun getGastos(): Result<List<GastoResponse>> {
        return try {
            Result.success(api.getGastos())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getGastoById(id: Int): Result<GastoResponse> {
        return try {
            Result.success(api.getGastoById(id))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createGasto(gasto: GastoRequest): Result<GastoRequest> {
        return try {
            api.createGasto(gasto)
            Result.success(gasto)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateGasto(id: Int, gasto: GastoRequest): Result<Unit> {
        return try {
            val response = api.updateGasto(id, gasto)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error al actualizar gasto"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
