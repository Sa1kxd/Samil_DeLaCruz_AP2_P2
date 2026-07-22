package com.example.samil_delacruz_ap2_p2.domain.repository

import com.example.samil_delacruz_ap2_p2.domain.model.Borrame
import com.example.samil_delacruz_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow

interface
BorrameRepository {
    fun getBorrame(page: Int, limit: Int, name: String?): Flow<Resource<List<Borrame>>>

    fun getBorrameDetail(id: Int): Flow<Resource<Borrame>>

}