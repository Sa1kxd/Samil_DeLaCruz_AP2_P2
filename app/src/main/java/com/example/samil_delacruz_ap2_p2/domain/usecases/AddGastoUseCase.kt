package com.example.samil_delacruz_ap2_p2.domain.usecases

import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoRequest
import com.example.samil_delacruz_ap2_p2.domain.repository.GastosRepository
import javax.inject.Inject

class AddGastoUseCase @Inject constructor(
    private val repository: GastosRepository
) {
    suspend operator fun invoke(gasto: GastoRequest): Result<GastoRequest> {
        return repository.createGasto(gasto)
    }
}
