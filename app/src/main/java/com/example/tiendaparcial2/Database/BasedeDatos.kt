package com.example.tiendaparcial2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tiendaparcial2.Dao.ClientesDao
import com.example.tiendaparcial2.Dao.ProductosDao
import com.example.tiendaparcial2.Dao.VentasDao
import com.example.tiendaparcial2.Model.Clientes
import com.example.tiendaparcial2.Model.Productos
import com.example.tiendaparcial2.Model.Ventas

@Database(entities = [Clientes::class, Productos::class, Ventas::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class BasedeDatos : RoomDatabase() {

    abstract fun clientesDao(): ClientesDao
    abstract fun productosDao(): ProductosDao
    abstract fun ventasDao(): VentasDao

    companion object {
        @Volatile
        private var INSTANCE: BasedeDatos? = null

        fun getDatabase(context: Context): BasedeDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BasedeDatos::class.java,
                    "base_de_datos"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
