package com.example.app1.vistamodelo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app1.datos.RepositorioVersiculos
import com.example.app1.datos.VersiculoGuardado
import kotlinx.coroutines.launch

data class Versiculo(val texto: String, val referencia: String)

class FrasesViewModel(private val repositorio: RepositorioVersiculos) : ViewModel() {
    var textoVersiculo by mutableStateOf("Cargando...")
        private set
    var referenciaVersiculo by mutableStateOf("")
        private set

    private val versiculos = listOf(
        Versiculo("Porque para Dios no hay nada imposible.", "Lucas 1:37"),
        Versiculo("El Señor es mi pastor, nada me faltará.", "Salmo 23:1"),
        Versiculo("Todo lo puedo en Cristo que me fortalece.", "Filipenses 4:13"),
        Versiculo("Mas buscad primeramente el reino de Dios y su justicia, y todas estas cosas os serán añadidas.", "Mateo 6:33"),
        Versiculo("Porque yo sé los pensamientos que tengo acerca de vosotros, dice Jehová, pensamientos de paz, y no de mal, para daros el fin que esperáis.", "Jeremías 29:11"),
        Versiculo("El amor es paciente, es bondadoso. El amor no es envidioso ni jactancioso ni orgulloso.", "1 Corintios 13:4"),
        Versiculo("Confía en el Señor de todo corazón, y no en tu propia inteligencia.", "Proverbios 3:5"),
        Versiculo("Y sabemos que a los que aman a Dios, todas las cosas les ayudan a bien, esto es, a los que conforme a su propósito son llamados.", "Romanos 8:28")
    )

    init {
        buscarNuevoVersiculo()
    }

    fun buscarNuevoVersiculo() {
        val versiculo = versiculos.random()
        textoVersiculo = "\"${versiculo.texto}\""
        referenciaVersiculo = "- ${versiculo.referencia}"
    }

    fun guardarVersiculoActual() {
        viewModelScope.launch {
            val textoAGuardar = textoVersiculo.removeSurrounding("\"")
            val referenciaAGuardar = referenciaVersiculo.removePrefix("- ")
            val versiculoGuardado = VersiculoGuardado(texto = textoAGuardar, referencia = referenciaAGuardar)
            repositorio.insertar(versiculoGuardado)
        }
    }
}
