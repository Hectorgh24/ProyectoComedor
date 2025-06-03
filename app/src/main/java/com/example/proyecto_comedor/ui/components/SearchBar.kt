package com.example.proyecto_comedor.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL

// Modelo de datos para los platillos
data class Platillo(
    val id: String,
    val idCategoria: String,
    val nombre: String,
    val descripcion: String,
    val precio: String,
    val imgUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSearchBar(navController: NavController) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val scope = rememberCoroutineScope()
    val showMenu = remember { mutableStateOf(false) }

    // Estados para la búsqueda y resultados
    val searchResults = remember { mutableStateOf<List<Platillo>>(emptyList()) }
    val isLoading = remember { mutableStateOf(false) }
    val searchQuery = remember { mutableStateOf("") }

    val (_, startSpeechRecognition) = rememberSpeechRecognition { spokenText ->
        textFieldState.setTextAndPlaceCursorAtEnd(spokenText)
        searchQuery.value = spokenText
    }

    // Función para realizar la búsqueda en la API
    suspend fun searchFood(query: String) {
        if (query.isBlank()) {
            searchResults.value = emptyList()
            return
        }

        isLoading.value = true
        try {
            val results = withContext(Dispatchers.IO) {
                performApiSearch(query)
            }
            searchResults.value = results
        } catch (e: Exception) {
            e.printStackTrace()
            searchResults.value = emptyList()
        } finally {
            isLoading.value = false
        }
    }

    // Efecto para buscar cuando cambia el texto
    LaunchedEffect(textFieldState.text.toString()) {
        val query = textFieldState.text.toString()
        if (query != searchQuery.value) {
            searchQuery.value = query
            searchFood(query)
        }
    }

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                modifier = Modifier.fillMaxWidth(),
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = {
                    scope.launch {
                        searchFood(textFieldState.text.toString())
                        // searchBarState.animateToCollapsed() <-- eliminado
                    }
                },
                placeholder = { Text("Buscar algún producto...") },
                leadingIcon = {
                    if (searchBarState.currentValue == SearchBarValue.Expanded) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    searchBarState.animateToCollapsed()
                                    textFieldState.setTextAndPlaceCursorAtEnd("")
                                }
                            }
                        )
                        {
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
                        IconButton(onClick = { startSpeechRecognition() }) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Entrada por voz"
                            )
                        }
                    } else {
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

    if (searchBarState.currentValue == SearchBarValue.Expanded) {
        ExpandedFullScreenSearchBar(
            state = searchBarState,
            inputField = inputField
        ) {
            SearchResults(
                results = searchResults.value,
                isLoading = isLoading.value
            )
        }
    }
}

@Composable
fun SearchResults(
    results: List<Platillo>,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (results.isEmpty()) {
            Text(
                text = "No se encontraron resultados",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(results) { platillo ->
                    PlatilloItem(platillo = platillo)
                }
            }
        }
    }
}

@Composable
fun PlatilloItem(
    platillo: Platillo
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            // Imagen del platillo
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(platillo.imgUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = platillo.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Información del platillo
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = platillo.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (platillo.descripcion.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = platillo.descripcion,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "${platillo.precio}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


// Función para realizar la búsqueda en la API
suspend fun performApiSearch(query: String): List<Platillo> {
    return try {
        // URL de tu API específica
        val apiUrl = "https://vmonge.me/api/comedores/producto_carta/obtenerPlatillos"
        val url = URL(apiUrl)
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Accept", "application/xml")
        connection.connectTimeout = 10000
        connection.readTimeout = 10000

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val xmlResponse = connection.inputStream.bufferedReader().use { it.readText() }
            val allPlatillos = parseXmlResponse(xmlResponse)
            // Filtrar los platillos por el query de búsqueda
            allPlatillos.filter { platillo ->
                platillo.nombre.contains(query, ignoreCase = true) ||
                        platillo.descripcion.contains(query, ignoreCase = true)
            }
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

// Función para parsear la respuesta XML
fun parseXmlResponse(xmlString: String): List<Platillo> {
    val platillos = mutableListOf<Platillo>()

    try {
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(StringReader(xmlString))

        var eventType = parser.eventType
        var currentTag = ""

        var id = ""
        var idCategoria = ""
        var nombre = ""
        var descripcion = ""
        var precio = ""
        var imgUrl = ""

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    currentTag = parser.name
                    if (currentTag == "platillo") {
                        // Reiniciar valores para un nuevo platillo
                        id = ""
                        idCategoria = ""
                        nombre = ""
                        descripcion = ""
                        precio = ""
                        imgUrl = ""
                    }
                }

                XmlPullParser.TEXT -> {
                    val text = parser.text?.trim() ?: ""
                    if (text.isNotEmpty()) {
                        when (currentTag) {
                            "id" -> id = text
                            "id_categoria" -> idCategoria = text
                            "nombre" -> nombre = text
                            "descripcion" -> descripcion = text
                            "precio" -> precio = text
                            "img_url" -> imgUrl = text
                        }
                    }
                }

                XmlPullParser.END_TAG -> {
                    if (parser.name == "platillo") {
                        // Crear y agregar el platillo a la lista
                        if (id.isNotEmpty() && nombre.isNotEmpty()) {
                            platillos.add(
                                Platillo(
                                    id = id,
                                    idCategoria = idCategoria,
                                    nombre = nombre,
                                    descripcion = descripcion,
                                    precio = precio,
                                    imgUrl = imgUrl
                                )
                            )
                        }
                    }
                }
            }
            eventType = parser.next()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return platillos
}