package com.example.proyecto_comedor


import android.app.Application
import com.example.proyecto_comedor.DataMain.AppContainer
import com.example.proyecto_comedor.DataMain.AppContainer2
import com.example.proyecto_comedor.DataMain.DefaultAppContainer
import com.example.proyecto_comedor.DataMain.DefaultAppContainer2

class MenuDelDiaApplication : Application() {
    /** Para Menú del Día */
    lateinit var container: AppContainer
    /** Para Información Nutrimental */
    lateinit var container2: AppContainer2

    override fun onCreate() {
        super.onCreate()
        container  = DefaultAppContainer()
        container2 = DefaultAppContainer2()
    }
}