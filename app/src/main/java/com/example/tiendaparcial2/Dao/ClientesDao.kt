package com.example.tiendaparcial2.Dao

import androidx.room.*
import com.example.tiendaparcial2.Model.Clientes
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: Clientes)

    @Update
    suspend fun updateCliente(cliente: Clientes)

    @Delete
    suspend fun deleteCliente(cliente: Clientes)

    @Query("SELECT * FROM clientes")
    fun getAllClientes(): Flow<List<Clientes>>
}
