package com.example.samil_delacruz_ap2_p2.data.remote.api

import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoRequest
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GastosApi {
    @GET("api/Gastos")
    suspend fun getGastos(): List<GastoResponse>

    @GET("api/Gastos/{id}")
    suspend fun getGastoById(@Path("id") id: Int): GastoResponse

    @POST("api/Gastos")
    suspend fun createGasto(@Body gasto: GastoRequest): GastoResponse

    @PUT("api/Gastos/{id}")
    suspend fun updateGasto(@Path("id") id: Int, @Body gasto: GastoRequest): Response<Unit>
}