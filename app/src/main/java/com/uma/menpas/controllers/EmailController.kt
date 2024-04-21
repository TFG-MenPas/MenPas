package com.uma.menpas.controllers

import com.uma.menpas.services.EmailService

class EmailController {
    private val emailService = EmailService()
    fun enviarFormulario(nombre: String, email: String, mensaje: String, asunto: String): Boolean {
        return emailService.enviarCorreo(nombre, email, mensaje, asunto)
    }
}