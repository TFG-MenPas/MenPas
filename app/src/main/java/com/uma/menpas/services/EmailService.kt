package com.uma.menpas.services

import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder
import org.ksoap2.serialization.SoapObject

class EmailService {

    fun enviarCorreo(nombre: String, email: String, mensaje: String, asunto: String): Boolean {
        val request = SoapBuilder.createSoapObject("sendEmail")

        request.addProperty("Para", "nedebi8613@etopys.com")
        request.addProperty("CCO", "miguelangelcabanillassilva@gmail.com")
        request.addProperty("asunto", asunto)
        val mensajeCompleto = "$nombre ha enviado este mensaje a través de la APP: \n$mensaje"
        request.addProperty("cuerpo", mensajeCompleto)

        val response = PeticionSOAP.enviarPeticion(request)

        return SoapBuilder.soapObjectABoolean(response)
    }
}