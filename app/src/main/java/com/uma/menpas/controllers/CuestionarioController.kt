package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.models.Cuestionario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.CuestionarioService
import org.ksoap2.serialization.SoapObject

class CuestionarioController {
    val cuestionarioService = CuestionarioService()

    fun getCuestionariosRealizados (context: Context): List<Cuestionario> {
        val usuario = UsuarioDB.getDatabase(context)!!.UsuarioDAO().getUsuario()
        return cuestionarioService.cuestionariosRealizados(usuario.nombreUsuario, usuario.contrasenya)
    }

    fun listarCuestionarios(): SoapObject {
        return cuestionarioService.listarCuestionarios();
    }
}