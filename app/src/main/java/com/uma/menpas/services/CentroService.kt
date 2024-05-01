package com.uma.menpas.services

import com.uma.menpas.models.Centro
import com.uma.menpas.models.mappers.CentroSoapMapper
import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder

class CentroService() {

    fun listCentersDetailed(): List<Centro>? {
        val request = SoapBuilder.createSoapObject("lista_datos_centros")
        return CentroSoapMapper.soapListToCenterList(PeticionSOAP.enviarPeticion(request))
    }

    fun getCenterById(id: String): Centro {
        val request = SoapBuilder.createSoapObject("getCentro")
        request.addProperty("ID", id);
        var centerList = listOf<Centro>()
        val result = PeticionSOAP.enviarPeticion(request)
        centerList = CentroSoapMapper.soapListToCenterList(result)!!
        return centerList.get(0)
    }

}