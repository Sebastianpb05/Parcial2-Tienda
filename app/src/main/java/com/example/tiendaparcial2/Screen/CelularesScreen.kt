package com.example.tiendaparcial2.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tiendaparcial2.Model.Productos
import com.example.tiendaparcial2.Repository.ProductosRepository
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelularesScreen(navController: NavHostController, productosRepository: ProductosRepository) {
    var productos by remember { mutableStateOf(listOf<Productos>()) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Productos?>(null) }
    var cantidad by remember { mutableStateOf(1) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        productosRepository.allProductos.collect { listaProductos ->
            productos = listaProductos
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Celulares y televisores")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF6200EE)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .border(1.dp, Color(0xFFBB86FC), shape = MaterialTheme.shapes.small)
                            .clickable { navController.navigate("productos_screen/{nombreCliente}") }
                    ) {
                        TextButton(
                            onClick = {},
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "Productos", tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text("Inicio")
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("ventas_screen")
                },
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Ir a Ventas")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Encuentra aquí las mejores ofertas:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.DarkGray
                )
                Text(
                    text = "Selecciona el celular o televisor que desees y añádelo al carrito.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Color.Gray
                )
            }

            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEDE7F6)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Nombre: ${producto.nombre}", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Precio: $${producto.precio}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Stock: ${producto.stock}", style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    selectedProduct = producto
                                    showDialog = true
                                    cantidad = 0
                                    errorMessage = ""
                                },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Text("Añadir al carrito")
                            }
                        }
                    }
                }
            }
        }

        if (showDialog && selectedProduct != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Cantidad a comprar") },
                text = {
                    Column {
                        Text("¿Cuántos ${selectedProduct!!.nombre} deseas comprar?")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Stock disponible: ${selectedProduct!!.stock}")
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = cantidad.toString(),
                            onValueChange = { value ->
                                cantidad = value.toIntOrNull() ?: 0
                                errorMessage = if (cantidad > selectedProduct!!.stock) {
                                    "No puede seleccionar más que el stock disponible."
                                } else {
                                    ""
                                }
                            },
                            label = { Text("Cantidad") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = VisualTransformation.None
                        )
                        if (errorMessage.isNotEmpty()) {
                            Text(
                                text = errorMessage,
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (cantidad <= selectedProduct!!.stock) {
                            navController.navigate("ventas_screen/${selectedProduct!!.id}/$cantidad")
                            showDialog = false
                        } else {
                            errorMessage = "No puede seleccionar más que el stock disponible."
                        }
                    }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
