package com.example.tiendaparcial2.Repository

import com.example.tiendaparcial2.Dao.ClientesDao
import com.example.tiendaparcial2.Model.Clientes
import kotlinx.coroutines.flow.Flow

class ClientesRepository(private val clientesDao: ClientesDao) {
    val allClientes: Flow<List<Clientes>> = clientesDao.getAllClientes()

    suspend fun insert(cliente: Clientes) {
        clientesDao.insertCliente(cliente)
    }

    suspend fun update(cliente: Clientes) {
        clientesDao.updateCliente(cliente)
    }

    suspend fun delete(cliente: Clientes) {
        clientesDao.deleteCliente(cliente)
    }
}
