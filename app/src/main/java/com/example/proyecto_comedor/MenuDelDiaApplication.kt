package com.example.proyecto_comedor

import android.app.Application
import com.example.proyecto_comedor.DataMain.AppContainer
import com.example.proyecto_comedor.DataMain.AppContainer2
import com.example.proyecto_comedor.DataMain.AppContainer3
import com.example.proyecto_comedor.DataMain.DefaultAppContainer
import com.example.proyecto_comedor.DataMain.DefaultAppContainer2
import com.example.proyecto_comedor.DataMain.DefaultAppContainer3

class MenuDelDiaApplication : Application() {
    lateinit var container: AppContainer
    lateinit var container2: AppContainer2
    lateinit var container3: AppContainer3

    override fun onCreate() {
        super.onCreate()
        container  = DefaultAppContainer()
        container2 = DefaultAppContainer2()
        container3 = DefaultAppContainer3()
    }
}