package com.uma.menpas.services

import com.uma.menpas.models.NuevoCentro
import com.uma.menpas.models.Usuario
import com.uma.menpas.models.adapters.AdaptadorNuevoCentro
import com.uma.menpas.models.adapters.AdaptadorUsuario
import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder
import org.ksoap2.serialization.SoapObject

class CentroService {
    companion object {

        fun listCentersDetailed(): List<NuevoCentro>? {
            val request = SoapBuilder.createSoapObject("lista_datos_centros")

            return AdaptadorNuevoCentro.soapListToCenterList(PeticionSOAP.enviarPeticion(request))
        }
        fun getCenterById(id: String): NuevoCentro {
            val request = SoapBuilder.createSoapObject("getCentro")
            request.addProperty("ID", id);
            var centerList = listOf<NuevoCentro>()
            val result = PeticionSOAP.enviarPeticion(request)
            centerList = AdaptadorNuevoCentro.soapListToCenterList(result)!!
            return centerList.get(0)
            }
        }


}