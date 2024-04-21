package com.uma.menpas.controllers

import com.uma.menpas.models.Centro
import com.uma.menpas.services.CentroService

class CentroController {

    val centroService = CentroService()

    fun crearCentro(nuevoCentro: Centro): Boolean {
        return centroService.crearCentro(nuevoCentro)
    }
}