package com.example.app1.datos

import kotlinx.coroutines.flow.Flow

class RepositorioVersiculos(private val versiculoGuardadoDao: VersiculoGuardadoDao) {

    val todosLosVersiculos: Flow<List<VersiculoGuardado>> = versiculoGuardadoDao.obtenerTodos()

    suspend fun insertar(versiculo: VersiculoGuardado) {
        versiculoGuardadoDao.insertar(versiculo)
    }
}
