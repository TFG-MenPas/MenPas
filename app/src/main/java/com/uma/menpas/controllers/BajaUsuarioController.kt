package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService

class BajaUsuarioController {

    companion object{
        fun bajaDeUsuario(context: Context): Boolean {
            val usuario = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()
            if(UsuarioService.darDeBaja(usuario?.nombreUsuario, usuario?.contrasenya)){
                UsuarioDB.getDatabase(context)?.UsuarioDAO()?.limpiarUsuario()
                return true
            }
            return false
        }

    }
}