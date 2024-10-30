package com.example.tiendaparcial2.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "ventas",
    foreignKeys = [
        ForeignKey(entity = Productos::class, parentColumns = ["id"], childColumns = ["productoId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Clientes::class, parentColumns = ["id"], childColumns = ["clienteId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class Ventas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productoId: Int,
    val clienteId: Int,
    val cantidad: Int,
    val fecha: Date
)
