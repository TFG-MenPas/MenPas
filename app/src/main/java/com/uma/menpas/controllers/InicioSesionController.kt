package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService

class InicioSesionController {
    val usuarioService = UsuarioService()

    fun comprobarUsuario(usuario: String, contrasenya: String): Usuario? {
        return usuarioService.comprobarUsuario(usuario, contrasenya)
    }

    fun validarDatos(usuario: String, contrasenya: String): Boolean{
        return !(usuario.isNullOrBlank() || contrasenya.isNullOrBlank())
    }

    fun guardarUsuario(context: Context, usuario: Usuario){
        return usuarioService.guardarUsuarioEnBD(context, usuario);
    }

    fun getUsuarioGuardado(context: Context): Usuario? {
        return UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()
    }

}