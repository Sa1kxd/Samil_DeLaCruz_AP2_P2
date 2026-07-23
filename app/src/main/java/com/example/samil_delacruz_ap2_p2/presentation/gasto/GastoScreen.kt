package com.example.samil_delacruz_ap2_p2.presentation.gasto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoScreen(
    id: Int? = null,
    viewModel: GastoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state = viewModel.state

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.loadGasto(id)
        }
    }

    LaunchedEffect(viewModel.eventFlow) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GastoUiEvent.SaveSuccess -> {
                    onNavigateBack()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (id == null) "Nuevo Gasto" else "Editar Gasto") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.saveGasto(id) }) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            OutlinedTextField(
                value = state.suplidor,
                onValueChange = viewModel::onSuplidorChange,
                label = { Text("Suplidor") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.suplidorError != null,
                supportingText = { state.suplidorError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.ncf,
                onValueChange = viewModel::onNcfChange,
                label = { Text("NCF") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = if (state.itbis == 0.0) "" else state.itbis.toString(),
                    onValueChange = viewModel::onItbisChange,
                    label = { Text("ITBIS") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = if (state.monto == 0.0) "" else state.monto.toString(),
                    onValueChange = viewModel::onMontoChange,
                    label = { Text("Monto") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    isError = state.montoError != null,
                    supportingText = { state.montoError?.let { Text(it) } }
                )
            }

            OutlinedTextField(
                value = state.fecha,
                onValueChange = viewModel::onFechaChange,
                label = { Text("Fecha") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("2026-07-22T00:00:00") }
            )

            state.error?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
