package com.example.proyecto_comedor.ui.feature.beca

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto_comedor.R

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

@Composable
fun BecaScreen() {
    val url = "https://www.fundacionuv.org/que_hacemos/otorgamos_becas/beca_alimentos.php?tipo=1"
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.somoscu), // reemplaza por tu imagen
            contentDescription = "Imagen informativa de becas",
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cada comienzo de semestre se realiza la convocatoria de la beca a través de Fundación UV, se indican fechas y el paso a paso a seguir ingresando al siguiente link:",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))


        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Ir al sitio de la beca",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}