package com.example.app1.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VersiculoGuardadoDao {
    @Insert
    suspend fun insertar(versiculo: VersiculoGuardado)

    @Query("SELECT * FROM saved_verses ORDER BY id DESC")
    fun obtenerTodos(): Flow<List<VersiculoGuardado>>
}
