package com.example.proyecto_comedor.ui.components

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

class SpeechRecognizer(
    private val onSpeechResult: (String) -> Unit,
    private val onError: (String) -> Unit
) {
    fun createSpeechIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora...")
        }
    }

    fun handleSpeechResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val speechResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            speechResult?.get(0)?.let { spokenText ->
                onSpeechResult(spokenText)
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            onError("Reconocimiento de voz cancelado")
        }
    }
}

/**
 * Hook Composable para utilizar el reconocimiento de voz
 */
@Composable
fun rememberSpeechRecognition(
    onSpeechResult: (String) -> Unit
): Pair<Intent, () -> Unit> {
    val context = LocalContext.current

    // Crear el reconocedor de voz
    val speechRecognizer = remember {
        SpeechRecognizer(
            onSpeechResult = onSpeechResult,
            onError = { errorMsg ->
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            }
        )
    }

    // Crear el intent para reconocimiento
    val speechIntent = remember {
        speechRecognizer.createSpeechIntent()
    }

    // Launcher para iniciar la actividad de reconocimiento
    val speechLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        speechRecognizer.handleSpeechResult(result.resultCode, result.data)
    }

    // Función para iniciar el reconocimiento de voz
    val startSpeechRecognition: () -> Unit = {
        try {
            speechLauncher.launch(speechIntent)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Error al iniciar el reconocimiento de voz: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    return Pair(speechIntent, startSpeechRecognition)
}