package com.example.app1.vista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app1.vistamodelo.VersiculosGuardadosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaVersiculosGuardados(alVolver: () -> Unit, viewModel: VersiculosGuardadosViewModel) {
    val versiculosGuardados by viewModel.versiculosGuardados.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Versículos Guardados") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(versiculosGuardados) { versiculo ->
                Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(versiculo.texto)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(versiculo.referencia)
                    }
                }
            }
        }
    }
}
