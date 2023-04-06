package com.uma.menpas.controllers

import com.uma.menpas.activities.RecuperarContrasenya
import com.uma.menpas.services.UsuarioService

class InicioSesionController {

    companion object{
        fun comprobarUsuario(usuario: String, contrasenya: String){
            UsuarioService.getUser(usuario, contrasenya)

        }
    }

}