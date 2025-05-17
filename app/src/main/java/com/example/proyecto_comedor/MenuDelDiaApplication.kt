package com.example.proyecto_comedor


import android.app.Application
import com.example.proyecto_comedor.DataMain.AppContainer
import com.example.proyecto_comedor.DataMain.DefaultAppContainer

class MenuDelDiaApplication : Application() {
    /** Instancia de AppContainer usada para proporcionar dependencias a la app */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}