package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.room.UsuarioDB

class MenuPrincipalController {

    companion object{
        fun getNombre(context: Context): String? {
            return UsuarioDB.getDatabase(context)?.UsuarioDAO()?.getUsuario()?.nombre
        }
    }

}