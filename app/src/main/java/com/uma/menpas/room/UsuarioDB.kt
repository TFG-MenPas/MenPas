package com.uma.menpas.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uma.menpas.models.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)

abstract class UsuarioDB : RoomDatabase() {
    abstract fun UsuarioDAO(): UsuarioDAO

    companion object{
        private var instancia: UsuarioDB? = null
        fun getDatabase(context: Context): UsuarioDB?{
            if(instancia == null){
                synchronized(this){
                    instancia = Room.databaseBuilder(context, UsuarioDB::class.java, "usuario").allowMainThreadQueries().build()
                }
            }
            return instancia
        }

    }
}