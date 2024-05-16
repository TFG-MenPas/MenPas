package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService
import com.uma.menpas.utils.CalculoResultados

class UsuarioController {
    val usuarioService = UsuarioService()

    fun getUsuario(context: Context): Usuario? {
        return UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()
    }

    fun realizarActualizacion(context: Context, opcion: Int, nuevoValor: String): Boolean {
        var usuario = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()!!
        var actualizado = false
        when(opcion) {
            1 -> actualizado = usuarioService.editarNombreDeUsuario(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            2 -> actualizado = usuarioService.editarNombre(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
            3 -> actualizado = usuarioService.editarApellidos(usuario.nombreUsuario, usuario.contrasenya, nuevoValor)
        }

        if(actualizado){
            val contrasenya = usuario.contrasenya
            usuario = if(opcion == 1){
                usuarioService.getUser(nuevoValor, usuario.contrasenya)!!
            } else {
                usuarioService.getUser(usuario.nombreUsuario, usuario.contrasenya)!!
            }
            usuario.contrasenya = contrasenya
            //usuarioService.borrarUsuarioEnBD(context)
            usuarioService.guardarUsuarioEnBD(context, usuario)
        }

        val prueba = getUsuario(context)

        return actualizado
    }

    fun editarSuscripcionCorreo(context: Context, valor: Boolean): Boolean {
        val usuario = UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()!!
        return usuarioService.editarSuscripcion(usuario.nombreUsuario,usuario.contrasenya, valor)
    }



}