package com.example.app1.datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VersiculoGuardado::class], version = 2, exportSchema = false) // VERSIÓN INCREMENTADA
abstract class BaseDeDatosApp : RoomDatabase() {

    abstract fun versiculoGuardadoDao(): VersiculoGuardadoDao

    companion object {
        @Volatile
        private var INSTANCIA: BaseDeDatosApp? = null

        fun obtenerBaseDeDatos(contexto: Context): BaseDeDatosApp {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    BaseDeDatosApp::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}
