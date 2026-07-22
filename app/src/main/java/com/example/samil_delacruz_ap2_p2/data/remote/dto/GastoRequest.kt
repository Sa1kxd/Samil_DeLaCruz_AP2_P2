package com.example.samil_delacruz_ap2_p2.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GastoRequest
    (
    val fecha: String,
    val suplidor: String,
    val ncf: String?,
    val itbis: Double?,
    val monto: Double
)
