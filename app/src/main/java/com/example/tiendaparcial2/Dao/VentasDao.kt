package com.example.tiendaparcial2.Dao


import androidx.room.*
import com.example.tiendaparcial2.Model.Ventas
import kotlinx.coroutines.flow.Flow

@Dao
interface VentasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenta(venta: Ventas)

    @Update
    suspend fun updateVenta(venta: Ventas)

    @Delete
    suspend fun deleteVenta(venta: Ventas)

    @Query("SELECT * FROM ventas")
    fun getAllVentas(): Flow<List<Ventas>>
}
