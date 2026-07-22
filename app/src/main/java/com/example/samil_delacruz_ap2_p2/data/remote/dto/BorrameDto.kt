package com.example.samil_delacruz_ap2_p2.data.remote.dto

import com.example.samil_delacruz_ap2_p2.domain.model.Borrame
import com.squareup.moshi.JsonClass

data class BorrameResponseDto(
    val items: List<BorrameDto>
)

@JsonClass(generateAdapter = true)
data class BorrameDto(
    val id: Int
){
    fun toDomain() = Borrame(
        id
    )
}