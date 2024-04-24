package com.uma.menpas.controllers

import android.content.Context
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.EmailService
import com.uma.menpas.services.UsuarioService

class EmailController {
    private val emailService = EmailService()
    fun enviarDudaSugerencia(nombre: String, email: String, mensaje: String, asunto: String): Boolean {
        return emailService.enviarDudaSugerencia(nombre, email, mensaje, asunto)
    }

    fun enviarSolicitudAdministrador(context: Context, cuestionariosNecesarios: String,
                         tituloInvestigacion: String,
                         estimacionParticipantes: String,
                         duracionEstimada: String,
                         tipoPublicacion: String,
                         motivo: String,
                         observaciones: String): Boolean {

        val usuario = UsuarioDB.getDatabase(context)!!.UsuarioDAO().getUsuario()

        return emailService.enviarSolicitudAdministrador(usuario.nombreUsuario,
            cuestionariosNecesarios,
            tituloInvestigacion,
            estimacionParticipantes,
            duracionEstimada,
            tipoPublicacion,
            motivo,
            observaciones)
    }
}