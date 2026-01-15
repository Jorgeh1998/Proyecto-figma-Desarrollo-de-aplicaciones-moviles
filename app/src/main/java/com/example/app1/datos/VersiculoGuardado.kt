package com.example.app1.datos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_verses")
data class VersiculoGuardado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val texto: String,
    val referencia: String
)
