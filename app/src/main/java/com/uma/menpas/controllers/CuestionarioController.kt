package com.uma.menpas.controllers

import com.uma.menpas.services.CuestionarioService
import org.ksoap2.serialization.SoapObject

class CuestionarioController {
    val cuestionarioService = CuestionarioService()

    fun getCuestionariosRealizados (nombreUsuario: String, password: String): SoapObject {
        return cuestionarioService.cuestionariosRealizados(nombreUsuario, password)
    }

    fun listarCuestionarios(): SoapObject {
        return cuestionarioService.listarCuestionarios();
    }
}