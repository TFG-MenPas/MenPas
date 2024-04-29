package com.uma.menpas.services

import com.uma.menpas.models.Cuestionario
import com.uma.menpas.models.mappers.CuestionarioSoapMapper
import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder
import org.ksoap2.serialization.SoapObject

class CuestionarioService {

    fun cuestionariosRealizados(nombreUsuario: String, password: String): List<Cuestionario> {
        val request = SoapBuilder.createSoapObject("cuestionariosRealizadosDate")
        request.addProperty("username", nombreUsuario)
        request.addProperty("pwd", password)

        val result = PeticionSOAP.enviarPeticion(request)

        return CuestionarioSoapMapper.soapListToListCuestionario(result)
    }

    fun listarCuestionarios(): SoapObject {
        val request = SoapBuilder.createSoapObject("lista_cuestionarios")

        val result = PeticionSOAP.enviarPeticion(request)

        return result
    }

}