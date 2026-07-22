package com.example.samil_delacruz_ap2_p2.data.repository

import com.example.samil_delacruz_ap2_p2.data.remote.api.BorrameApi
import com.example.samil_delacruz_ap2_p2.data.remote.datasource.borrameDataSource
import com.example.samil_delacruz_ap2_p2.domain.model.Borrame
import com.example.samil_delacruz_ap2_p2.domain.repository.BorrameRepository
import com.example.samil_delacruz_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorrameRepositoryImpl @Inject constructor(
    private val remoteData: borrameDataSource
): BorrameRepository {
    override fun getBorrame(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Borrame>>> {
        TODO("Not yet implemented")
    }

    override fun getBorrameDetail(id: Int): Flow<Resource<Borrame>> {
        TODO("Not yet implemented")
    }
}