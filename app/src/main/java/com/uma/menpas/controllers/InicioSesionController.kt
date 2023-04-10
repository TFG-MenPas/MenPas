package com.uma.menpas.controllers

import com.uma.menpas.models.Usuario
import com.uma.menpas.services.UsuarioService

class InicioSesionController {

    companion object{
        fun comprobarUsuario(usuario: String, contrasenya: String): Usuario? {
            return UsuarioService.getUser(usuario, contrasenya)
        }

        fun validarDatos(usuario: String, contrasenya: String): Boolean{
            return !(usuario.isNullOrBlank() || contrasenya.isNullOrBlank())
        }
    }

}