package com.example.tiendaparcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendaparcial2.Database.BasedeDatos
import com.example.tiendaparcial2.Repository.ClientesRepository
import com.example.tiendaparcial2.Repository.ProductosRepository
import com.example.tiendaparcial2.Screen.CelularesScreen
import com.example.tiendaparcial2.Screen.ClientesScreen
import com.example.tiendaparcial2.Screen.ProductosScreen
import com.example.tiendaparcial2.Screen.VentasScreen

class MainActivity : ComponentActivity() {
    private lateinit var clientesRepository: ClientesRepository
    private lateinit var productosRepository: ProductosRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = BasedeDatos.getDatabase(applicationContext)
        clientesRepository = ClientesRepository(database.clientesDao())
        productosRepository = ProductosRepository(database.productosDao())

        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "clientes_screen") {
                        composable("clientes_screen") {
                            ClientesScreen(navController, clientesRepository)
                        }
                        composable("productos_screen/{nombreCliente}") { backStackEntry ->
                            val nombreCliente = backStackEntry.arguments?.getString("nombreCliente")
                            ProductosScreen(navController, productosRepository, nombreCliente)
                        }
                        composable("celulares") {
                            CelularesScreen(navController, productosRepository)
                        }

                        composable("ventas_screen") {
                            VentasScreen(navController, productoId = 0, cantidad = 1)
                        }
                        composable("ventas_screen/{productoId}/{cantidad}") { backStackEntry ->
                            val productoId = backStackEntry.arguments?.getString("productoId")?.toInt() ?: 0
                            val cantidad = backStackEntry.arguments?.getString("cantidad")?.toInt() ?: 1
                            VentasScreen(navController, productoId, cantidad)
                        }

                    }
                }
            }
        }
    }
}