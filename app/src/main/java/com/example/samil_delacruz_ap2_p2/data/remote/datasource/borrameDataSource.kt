package com.example.samil_delacruz_ap2_p2.data.remote.datasource

import com.example.samil_delacruz_ap2_p2.data.remote.api.BorrameApi
import com.example.samil_delacruz_ap2_p2.domain.repository.BorrameRepository
import javax.inject.Inject

class borrameDataSource @Inject constructor(
    private val api: BorrameApi
) {
}