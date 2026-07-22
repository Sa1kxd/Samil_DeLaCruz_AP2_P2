package com.example.samil_delacruz_ap2_p2.domain.usecases

import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoResponse
import com.example.samil_delacruz_ap2_p2.domain.repository.GastosRepository
import javax.inject.Inject

class GetGastoByIdUseCase @Inject constructor(
    private val repository: GastosRepository
) {
    suspend operator fun invoke(id: Int): Result<GastoResponse> {
        return repository.getGastoById(id)
    }
}
