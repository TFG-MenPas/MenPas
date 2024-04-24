package com.uma.menpas.services

import android.util.Log
import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder

class EmailService {
    fun enviarDudaSugerencia(nombre: String, email: String, mensaje: String, asunto: String): Boolean {
        val mensajeCompleto = "Una persona llamada $nombre ha enviado este mensaje de duda y sugerencia a través de la APP: <br>" +
                "Asunto: $asunto <br>" +
                "Email: $email <br>" +
                "Mensaje: $mensaje <br>"

        val asuntoCorreo = "Menpas: Duda y sugerencia"

        return enviarCorreo(asuntoCorreo, mensajeCompleto)
    }

    fun enviarSolicitudAdministrador(
        nombreUsuario: String,
        cuestionariosNecesarios: String,
        tituloInvestigacion: String,
        estimacionParticipantes: String,
        duracionEstimada: String,
        tipoPublicacion: String,
        motivo: String,
        observaciones: String
    ): Boolean {

        val mensajeCompleto = "El usuario $nombreUsuario ha enviado este mensaje de solicitud de administrador a través de la APP: <br>" +
                "Cuestionarios necesarios: $cuestionariosNecesarios <br>" +
                "Título investigación: $tituloInvestigacion <br>" +
                "Estimación participantes: $estimacionParticipantes <br>" +
                "Duración estimada: $duracionEstimada <br>" +
                "Tipo publicación: $tipoPublicacion <br>" +
                "Motivo: $motivo <br>" +
                "Observaciones: $observaciones <br>"

        val asunto = "Menpas: Solicitud de administrador"

        return enviarCorreo(asunto, mensajeCompleto)
    }

    private fun enviarCorreo(asunto: String, mensaje: String): Boolean {
        val request = SoapBuilder.createSoapObject("sendEmail")

        request.addProperty("Para", "miguelangelcabanillassilva@gmail.com")
        request.addProperty("CCO", "miguelangelcabanillassilva32131@gmail.com")
        request.addProperty("asunto", asunto)
        request.addProperty("cuerpo", mensaje)

        val response = PeticionSOAP.enviarPeticion(request)

        return SoapBuilder.soapObjectABoolean(response)
    }
}