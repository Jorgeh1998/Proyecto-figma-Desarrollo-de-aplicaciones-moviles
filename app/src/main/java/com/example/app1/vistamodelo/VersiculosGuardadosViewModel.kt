package com.example.app1.vistamodelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app1.datos.RepositorioVersiculos
import com.example.app1.datos.VersiculoGuardado
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class VersiculosGuardadosViewModel(repositorio: RepositorioVersiculos) : ViewModel() {
    val versiculosGuardados: StateFlow<List<VersiculoGuardado>> = repositorio.todosLosVersiculos.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}
