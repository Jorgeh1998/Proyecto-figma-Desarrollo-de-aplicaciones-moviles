package com.example.app1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app1.datos.RepositorioVersiculos
import com.example.app1.vistamodelo.FrasesViewModel
import com.example.app1.vistamodelo.VersiculosGuardadosViewModel

class FactoriaViewModels(private val repositorio: RepositorioVersiculos) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FrasesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FrasesViewModel(repositorio) as T
        }
        if (modelClass.isAssignableFrom(VersiculosGuardadosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VersiculosGuardadosViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Clase ViewModel Desconocida")
    }
}
