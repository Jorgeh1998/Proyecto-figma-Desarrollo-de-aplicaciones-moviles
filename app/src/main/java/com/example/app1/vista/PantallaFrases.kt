package com.example.app1.vista

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.app1.vistamodelo.FrasesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFrases(alVolver: () -> Unit, viewModel: FrasesViewModel) {
    val contexto = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reflexiones") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(viewModel.textoVersiculo)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(viewModel.referenciaVersiculo)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.buscarNuevoVersiculo() }) {
                Text("Nuevo")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = { 
                    viewModel.guardarVersiculoActual()
                    Toast.makeText(contexto, "Versículo guardado", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Guardar")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Guardar")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { 
                    val intentCompartir = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "${viewModel.textoVersiculo} ${viewModel.referenciaVersiculo}")
                        type = "text/plain"
                    }
                    val chooser = Intent.createChooser(intentCompartir, null)
                    contexto.startActivity(chooser)
                }) {
                    Icon(Icons.Default.Share, contentDescription = "Compartir")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Compartir")
                }
            }
        }
    }
}
