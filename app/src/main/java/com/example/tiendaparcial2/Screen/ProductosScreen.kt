package com.example.tiendaparcial2.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaparcial2.Model.Productos
import com.example.tiendaparcial2.R
import com.example.tiendaparcial2.Repository.ProductosRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(navController: NavController, productosRepository: ProductosRepository, nombreCliente: String?) {
    var productos by remember { mutableStateOf(listOf<Productos>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            productosRepository.allProductos.collect { listaProductos ->
                productos = listaProductos
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tu tienda online", textAlign = TextAlign.Center)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Mensaje de bienvenida
            if (!nombreCliente.isNullOrEmpty()) {
                Text("¡Bienvenido, $nombreCliente!", modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Descripción de la tienda
            Text(
                text = "Somos tu destino para todo lo relacionado con la tecnología. Desde smartphones de última generación hasta accesorios inteligentes para el hogar, nuestra tienda online te ofrece una experiencia de compra única.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Text("Categorías", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
            Column {
                // Card para Celulares
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("celulares") }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.celular),
                            contentDescription = "Imagen de un celular",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 16.dp)
                        )
                        Column {
                            Text("Celulares", fontWeight = FontWeight.Bold)
                            Text("Explora nuestra colección de smartphones de última generación.")
                        }
                    }
                }

                // Card para Accesorios
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("smart_home") }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iphone15),
                            contentDescription = "Imagen de un accesorio",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 16.dp)
                        )
                        Column {
                            Text("Smart Home", fontWeight = FontWeight.Bold)
                            Text("Encuentra los mejores dispositivos inteligentes para tu hogar.")
                        }
                    }
                }
            }
        }
    }
}

