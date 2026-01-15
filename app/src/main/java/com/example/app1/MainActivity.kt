package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app1.ui.theme.App1Theme
import com.example.app1.vista.PantallaFrases
import com.example.app1.vista.PantallaInicio
import com.example.app1.vista.PantallaVersiculosGuardados

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as AppCalma
        val factoria = FactoriaViewModels(app.repositorio)
        enableEdgeToEdge()
        setContent {
            App1Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") {
                        PantallaInicio(
                            alNavegarAFrases = { navController.navigate("frases") },
                            alNavegarAGuardados = { navController.navigate("guardados") }
                        )
                    }
                    composable("frases") {
                        PantallaFrases(
                            alVolver = { navController.popBackStack() },
                            viewModel = viewModel(factory = factoria)
                        )
                    }
                    composable("guardados") {
                        PantallaVersiculosGuardados(
                            alVolver = { navController.popBackStack() },
                            viewModel = viewModel(factory = factoria)
                        )
                    }
                }
            }
        }
    }
}
