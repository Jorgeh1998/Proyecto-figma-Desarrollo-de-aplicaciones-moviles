package com.example.app1.vista

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app1.ui.theme.App1Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(
    modifier: Modifier = Modifier,
    alNavegarAFrases: () -> Unit,
    alNavegarAGuardados: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var estadoAnimo by remember { mutableStateOf(5f) }
    var mostrarError by remember {mutableStateOf(false)}


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hola, Bienvenido")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it
                            if(mostrarError) mostrarError = false
                            },
            label = { Text("Tu nombre") },
            isError = nombre.isBlank() && mostrarError,

            supportingText = {
                if (nombre.isBlank() && mostrarError) {
                    Text("El nombre no puede estar vacío",
                        color = androidx.compose.ui.graphics.Color.Red
                    )
                }
            }
        )



        Spacer(modifier = Modifier.height(16.dp))
        Text("¿Cómo te sientes hoy?")
        Slider(
            value = estadoAnimo,
            onValueChange = { estadoAnimo = it },
            valueRange = 1f..10f
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (nombre.isNotBlank()) {
                    alNavegarAFrases()
                } else {
                    mostrarError = true
                }
            }
        ){
            Text("Buscar calma")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = alNavegarAGuardados) {
            Text("Versículos Guardados")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreviaPantallaInicio() {
    App1Theme {
        PantallaInicio(alNavegarAFrases = {}, alNavegarAGuardados = {})
    }
}
