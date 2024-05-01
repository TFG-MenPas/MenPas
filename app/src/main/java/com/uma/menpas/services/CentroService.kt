package com.uma.menpas.services

import android.os.StrictMode
import com.uma.menpas.models.Centro
import com.uma.menpas.models.mappers.CentroSoapMapper
import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

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


        fun crearCentro(nuevoCentro: Centro): Boolean {
            val request = SoapBuilder.createSoapObject("AddCentroValues")

            request.addProperty("Nombre_Usuario1", nuevoCentro.nombreUsuario1)
            request.addProperty("Nombre_Centro", nuevoCentro.nombreCentro)
            request.addProperty("Localidad", nuevoCentro.localidad)
            request.addProperty("Provincia", nuevoCentro.provincia)
            request.addProperty("Pais", nuevoCentro.pais)
            request.addProperty("Direccion", nuevoCentro.direccion)
            request.addProperty("Telefono", nuevoCentro.telefono)
            request.addProperty("Codigo_Postal", nuevoCentro.codigoPostal)

            val result = PeticionSOAP.enviarPeticion(request)

            return SoapBuilder.soapObjectABoolean(result)
        }
}