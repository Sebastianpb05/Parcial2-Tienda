package com.example.tiendaparcial2.Dao

import androidx.room.*
import com.example.tiendaparcial2.Model.Productos
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducto(producto: Productos)

    @Update
    suspend fun updateProducto(producto: Productos)

    @Delete
    suspend fun deleteProducto(producto: Productos)

    @Query("SELECT * FROM productos")
    fun getAllProductos(): Flow<List<Productos>>

}
