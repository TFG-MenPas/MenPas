package com.uma.menpas.controllers

import android.content.Context
import androidx.room.Room
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService

class InicioSesionController {

    companion object{
        fun comprobarUsuario(usuario: String, contrasenya: String): Usuario? {
            return UsuarioService.getUser(usuario, contrasenya)
        }

        fun validarDatos(usuario: String, contrasenya: String): Boolean{
            return !(usuario.isNullOrBlank() || contrasenya.isNullOrBlank())
        }

        fun guardarUsuario(context: Context, usuario: Usuario){
            val usuarioDB = UsuarioDB.getDatabase(context)
            usuarioDB?.UsuarioDAO()?.insertUsuario(usuario)
        }

        fun getUsuarioGuardado(context: Context): Usuario? {
            val usuarioGuardado = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()
            return usuarioGuardado
        }

    }

}