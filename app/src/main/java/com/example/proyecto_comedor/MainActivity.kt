package com.example.proyecto_comedor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyecto_comedor.ui.theme.Proyecto_ComedorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_ComedorTheme {
                AppNavigation()
                //MainApp() // <- este inicia la navegación
            }
        }
    }
}

