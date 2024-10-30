package com.example.tiendaparcial2.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaparcial2.Model.Productos
import com.example.tiendaparcial2.Repository.ProductosRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartHomeScreen(productosRepository: ProductosRepository) {
    var productos by remember { mutableStateOf(listOf<Productos>()) }

    LaunchedEffect(Unit) {
        productosRepository.allProductos.collect { listaProductos ->
            productos = listaProductos.filter { it.nombre.contains("smart home", ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Smart Home") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Nombre: ${producto.nombre}")
                        Text("Precio: ${producto.precio}")
                        Text("Stock: ${producto.stock}")
                    }
                }
            }
        }
    }
}
