package com.example.samil_delacruz_ap2_p2.presentation.gasto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoRequest
import com.example.samil_delacruz_ap2_p2.domain.usecases.AddGastoUseCase
import com.example.samil_delacruz_ap2_p2.domain.usecases.GetGastoByIdUseCase
import com.example.samil_delacruz_ap2_p2.domain.usecases.UpdateGastoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastoViewModel @Inject constructor(
    private val getGastoByIdUseCase: GetGastoByIdUseCase,
    private val addGastoUseCase: AddGastoUseCase,
    private val updateGastoUseCase: UpdateGastoUseCase
) : ViewModel() {

    var state by mutableStateOf(GastoUiState())
        private set

    private val _eventFlow = MutableSharedFlow<GastoUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onFechaChange(fecha: String) { state = state.copy(fecha = fecha) }
    fun onSuplidorChange(suplidor: String) { state = state.copy(suplidor = suplidor, suplidorError = null) }
    fun onNcfChange(ncf: String) { state = state.copy(ncf = ncf) }
    fun onItbisChange(itbis: String) { state = state.copy(itbis = itbis.toDoubleOrNull() ?: 0.0) }
    fun onMontoChange(monto: String) { state = state.copy(monto = monto.toDoubleOrNull() ?: 0.0, montoError = null) }

    fun loadGasto(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getGastoByIdUseCase(id)
                .onSuccess { gasto ->
                    state = state.copy(
                        isLoading = false,
                        fecha = gasto.fecha,
                        suplidor = gasto.suplidor,
                        ncf = gasto.ncf ?: "",
                        itbis = gasto.itbis ?: 0.0,
                        monto = gasto.monto
                    )
                }
                .onFailure { error ->
                    state = state.copy(isLoading = false, error = error.message)
                }
        }
    }

    fun saveGasto(id: Int? = null) {
        if (!validate()) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val request = GastoRequest(
                fecha = state.fecha,
                suplidor = state.suplidor,
                ncf = state.ncf,
                itbis = state.itbis,
                monto = state.monto
            )

            val result = if (id == null) {
                addGastoUseCase(request)
            } else {
                updateGastoUseCase(id, request)
            }

            result.onSuccess {
                state = state.copy(isLoading = false, success = true)


                _eventFlow.emit(GastoUiEvent.SaveSuccess)
            }.onFailure { error ->
                state = state.copy(isLoading = false, error = error.message)
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (state.suplidor.isBlank()) {
            state = state.copy(suplidorError = "El suplidor es obligatorio")
            isValid = false
        }
        if (state.monto <= 0) {
            state = state.copy(montoError = "El monto debe ser mayor a 0")
            isValid = false
        }
        return isValid
    }
}
