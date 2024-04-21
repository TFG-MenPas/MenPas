package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService

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

        var usuarioNuevo = usuario.copy(nombre = "NuevoMiguel", infoEmail = true)

        val actualizado = usuarioService.editarUsuario(usuario, usuarioNuevo)
        /*
        return when(opcion) {
            1 -> usuarioService.editarNombreDeUsuario(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            2 -> usuarioService.editarNombre(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            3 -> usuarioService.editarApellidos(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            else -> false
        }
        */

        return actualizado
    }

    fun editarSuscripcionCorreo(context: Context, valor: Boolean): Boolean {
        val usuario = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()!!
        val actualizado = usuarioService.editarSuscripcion(usuario.nombreUsuario,usuario.contrasenya, valor)
        return false
    }



}