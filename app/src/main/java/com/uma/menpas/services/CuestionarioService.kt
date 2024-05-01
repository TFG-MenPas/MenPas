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

    fun getCuestionarioById(nombreCuestionario: String, id: String): Map<String, String> {
        val request = SoapBuilder.createSoapObject("getCuestionarioById")
        request.addProperty("cuestionario", nombreCuestionario)
        request.addProperty("id", id)

        val result = PeticionSOAP.enviarPeticion(request)

        return CuestionarioSoapMapper.soapObjectToDetalleCuestionario(result)
    }

    fun insertarCuestionario(query: String): SoapObject {
        val request = SoapBuilder.createSoapObject("InsertaTabla")
        request.addProperty("insert", query)

        val result = PeticionSOAP.enviarPeticion(request)
        return result
    }

}
