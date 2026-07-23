package com.example.samil_delacruz_ap2_p2.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samil_delacruz_ap2_p2.data.remote.dto.GastoResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onAddGasto: () -> Unit,
    onEditGasto: (Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Gastos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddGasto) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Gasto")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
                    Button(onClick = { viewModel.loadGastos() }) {
                        Text("Reintentar")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.gastos) { gasto ->
                        GastoItem(gasto = gasto, onClick = { onEditGasto(gasto.gastoId) })
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Conteo: ${uiState.gastos.size}")
                    val total = uiState.gastos.sumOf { it.monto }
                    Text(text = "Total: $${total}")
                }
            }
        }
    }
}

@Composable
fun GastoItem(gasto: GastoResponse, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "ID: ${gasto.gastoId}", style = MaterialTheme.typography.labelSmall)
                Text(text = gasto.fecha, style = MaterialTheme.typography.labelSmall)
            }
            Text(text = gasto.suplidor, style = MaterialTheme.typography.titleMedium)
            Text(text = "Monto: $${gasto.monto}", style = MaterialTheme.typography.bodyLarge)
            if (!gasto.ncf.isNullOrBlank()){
                Text(text = "NCF: ${gasto.ncf}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
