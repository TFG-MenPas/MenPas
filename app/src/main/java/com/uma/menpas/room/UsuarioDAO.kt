package com.uma.menpas.room

import androidx.room.*
import com.uma.menpas.models.Usuario

@Dao
interface UsuarioDAO {
    @Query("SELECT * from Usuario")
    fun getUsuario(): Usuario

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsuario(usuario: Usuario)

    @Update
    fun updateUsuario(usuario: Usuario)

    @Query("DELETE FROM Usuario")
    fun limpiarUsuario()
}