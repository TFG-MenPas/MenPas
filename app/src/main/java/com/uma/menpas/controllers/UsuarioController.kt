package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService
import com.uma.menpas.utils.CalculoResultados

class UsuarioController {
    val usuarioService = UsuarioService()

    fun editarNombre(context: Context): Boolean {
        val usuario = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()!!
        var response = usuarioService.editarNombre(usuario.nombreUsuario, usuario.contrasenya, "NuevoMiguel")
        return true
    }

    fun getUsuario(context: Context): Usuario {
        return UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()!!
    }

    fun realizarActualizacion(context: Context, opcion: Int, nuevoValor: String): Boolean {
        val usuario = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()!!
        return when(opcion) {
            1 -> usuarioService.editarNombreDeUsuario(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            2 -> usuarioService.editarNombre(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            3 -> usuarioService.editarApellidos(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            else -> false
        }
    }

    fun editarSuscripcionCorreo(context: Context, valor: Boolean): Boolean {

        return false
    }



}