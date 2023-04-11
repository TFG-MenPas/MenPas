package com.uma.menpas.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uma.menpas.models.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)

abstract class UsuarioDB : RoomDatabase() {
    abstract fun UsuarioDAO(): UsuarioDAO
}