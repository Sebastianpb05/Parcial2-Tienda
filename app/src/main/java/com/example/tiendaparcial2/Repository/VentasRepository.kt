package com.example.tiendaparcial2.Repository

import com.example.tiendaparcial2.Dao.VentasDao
import com.example.tiendaparcial2.Model.Ventas
import kotlinx.coroutines.flow.Flow

class VentasRepository(private val ventasDao: VentasDao) {
    val allVentas: Flow<List<Ventas>> = ventasDao.getAllVentas()

    suspend fun insert(venta: Ventas) {
        ventasDao.insertVenta(venta)
    }

    suspend fun update(venta: Ventas) {
        ventasDao.updateVenta(venta)
    }

    suspend fun delete(venta: Ventas) {
        ventasDao.deleteVenta(venta)
    }
}
