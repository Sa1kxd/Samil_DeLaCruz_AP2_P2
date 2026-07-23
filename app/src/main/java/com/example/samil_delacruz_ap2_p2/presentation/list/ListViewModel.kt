package com.example.samil_delacruz_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samil_delacruz_ap2_p2.domain.usecases.GetGastoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getGastoUseCase: GetGastoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GastoListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadGastos()
    }

    fun loadGastos() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getGastoUseCase()
                .onSuccess { gastos ->
                    _uiState.update { it.copy(gastos = gastos, isLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                }
        }
    }
}
