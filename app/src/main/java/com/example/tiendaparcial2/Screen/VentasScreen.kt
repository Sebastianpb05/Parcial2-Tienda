package com.example.tiendaparcial2.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentasScreen(navController: NavController, productoId: Int, cantidad: Int) {
    var total by remember { mutableStateOf(0.0) }
    var mostrarSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    if (mostrarSnackbar) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar("Venta realizada con éxito")
            mostrarSnackbar = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        "Ventas",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
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
                    Button(
                        onClick = { navController.navigate("productos_screen/{nombreCliente}") },
                        modifier = Modifier
                            .height(50.dp)
                            .width(110.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Home, contentDescription = "Inicio")
                        Text(text = "Inicio", textAlign = TextAlign.Center)
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    Button(
                        onClick = {
                            mostrarSnackbar = true
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Comprar")
                        Text(text = "Confirmar Venta", textAlign = TextAlign.Center)
                    }
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Producto ID: $productoId", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cantidad: $cantidad", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}
