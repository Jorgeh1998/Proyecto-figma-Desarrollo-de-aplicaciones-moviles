package com.example.app1

import android.app.Application
import com.example.app1.datos.BaseDeDatosApp
import com.example.app1.datos.RepositorioVersiculos

class AppCalma : Application() {
    val baseDeDatos by lazy { BaseDeDatosApp.obtenerBaseDeDatos(this) }
    val repositorio by lazy { RepositorioVersiculos(baseDeDatos.versiculoGuardadoDao()) }
}
