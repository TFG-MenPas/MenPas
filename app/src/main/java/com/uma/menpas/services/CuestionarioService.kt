package com.uma.menpas.services

import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive

class CuestionarioService {

    fun cuestionariosRealizados(nombreUsuario: String, password: String): SoapObject {
        val request = SoapBuilder.createSoapObject("cuestionariosRealizados")
        request.addProperty("username", nombreUsuario)
        request.addProperty("pwd", password)

        val result = PeticionSOAP.enviarPeticion(request)

        return result
    }

    fun listarCuestionarios(): SoapObject {
        val request = SoapBuilder.createSoapObject("lista_cuestionarios")

        val result = PeticionSOAP.enviarPeticion(request)

        return result
    }

    fun insertarCuestionario(query: String): SoapObject {
        val request = SoapBuilder.createSoapObject("InsertaTabla")
        request.addProperty("insert", query)

        val result = PeticionSOAP.enviarPeticion(request)
        return result
    }

    fun obtenerIdDisponible(table: String, field: String): String {
        val request = SoapBuilder.createSoapObject("getNewID")
        request.addProperty("table", table)
        request.addProperty("field", field)
        val result = PeticionSOAP.enviarPeticion(request)
        val getNewIDResponse = result.getProperty(0) as SoapPrimitive
        val id =  getNewIDResponse.value as String
        return id
    }

}