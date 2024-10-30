package com.example.tiendaparcial2.Screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaparcial2.Model.Clientes
import com.example.tiendaparcial2.Repository.ClientesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientesScreen(navController: NavController, clientesRepository: ClientesRepository) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Iniciar sesión")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                modifier = Modifier.border(BorderStroke(1.dp, Color.Gray))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    coroutineScope.launch(Dispatchers.Main) {
                        if (nombre.isBlank() || correo.isBlank()) {
                            Toast.makeText(context, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                            return@launch
                        }

                        try {
                            val nuevoCliente = Clientes(nombre = nombre, correo = correo)
                            clientesRepository.insert(nuevoCliente)
                            // Navegar a ProductosScreen pasando el nombre del cliente
                            navController.navigate("productos_screen/${nombre}")
                        } catch (e: Exception) {
                            println("Error al registrar cliente: ${e.message}")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}
