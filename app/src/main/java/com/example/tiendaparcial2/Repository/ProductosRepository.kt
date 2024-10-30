package com.example.tiendaparcial2.Repository

import com.example.tiendaparcial2.Dao.ProductosDao
import com.example.tiendaparcial2.Model.Productos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductosRepository(private val productosDao: ProductosDao) {

    //val allProductos: Flow<List<Productos>> = productosDao.getAllProductos()

    suspend fun insert(producto: Productos) {
        productosDao.insertProducto(producto)
    }

    suspend fun update(producto: Productos) {
        productosDao.updateProducto(producto)
    }

    suspend fun delete(producto: Productos) {
        productosDao.deleteProducto(producto)
    }

    private val _allProductos = MutableStateFlow(
        listOf(
            Productos(nombre = "iPhone 13", precio = 3455000.0, stock = 8),
            Productos(nombre = "Samsung Galaxy S21", precio = 4500000.0, stock = 10),
            Productos(nombre = "Xiaomi Redmi Note 10", precio = 4700000.0, stock = 15),


            Productos(nombre = "Televisor LG 55''", precio = 2100000.0, stock = 20),
            Productos(nombre = "Samsung QLED 65''", precio = 3000000.0, stock = 15),
            Productos(nombre = "Sony Bravia 50''", precio = 1700000.0, stock = 10),
        )
    )
    val allProductos = _allProductos.asStateFlow()
}
