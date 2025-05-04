package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSearchBar(navController: NavController) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val scope = rememberCoroutineScope()
    val showMenu = remember { mutableStateOf(false) }

    // Integración con reconocimiento de voz
    val (_, startSpeechRecognition) = rememberSpeechRecognition { spokenText ->
        textFieldState.setTextAndPlaceCursorAtEnd(spokenText)
    }

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                modifier = Modifier.fillMaxWidth(),
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
                placeholder = { Text("Buscar algún producto...") },
                leadingIcon = {
                    if (searchBarState.currentValue == SearchBarValue.Expanded) {
                        IconButton(
                            onClick = { scope.launch { searchBarState.animateToCollapsed() } }
                        ) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Atrás")
                        }
                    } else {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar")
                        }
                    }
                },
                trailingIcon = {
                    if (searchBarState.currentValue == SearchBarValue.Expanded) {
                        // Mostrar icono de micrófono cuando está expandida
                        IconButton(onClick = { startSpeechRecognition() }) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Entrada por voz"
                            )
                        }
                    } else {
                        // Mostrar icono de tres puntos cuando está colapsada
                        Box {
                            IconButton(onClick = { showMenu.value = !showMenu.value }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
                            }

                            DropdownMenu(
                                expanded = showMenu.value,
                                onDismissRequest = { showMenu.value = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Mi cuenta") },
                                    onClick = {
                                        showMenu.value = false
                                        navController.navigate("usuario")
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Outlined.AccountCircle,
                                            contentDescription = "Mi cuenta"
                                        )
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text("Configuración") },
                                    onClick = {
                                        showMenu.value = false
                                        navController.navigate("configuracion")
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Settings,
                                            contentDescription = "Configuración"
                                        )
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text("Acerca de") },
                                    onClick = {
                                        showMenu.value = false
                                        navController.navigate("acerca")
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Info,
                                            contentDescription = "Acerca de"
                                        )
                                    }
                                )
                            }
                        }
                    }
                },
            )
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(0.9f),
            state = searchBarState,
            inputField = inputField,
        )
    }

    // Mostrar ExpandedFullScreenSearchBar solo cuando esté expandido
    if (searchBarState.currentValue == SearchBarValue.Expanded) {
        ExpandedFullScreenSearchBar(
            state = searchBarState,
            inputField = inputField
        ) {
            /*SearchResults(
                onResultClick = { result ->
                    textFieldState.setTextAndPlaceCursorAtEnd(result)
                    scope.launch { searchBarState.animateToCollapsed() }
                }
            )*/
        }
    }
}