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
            val request = SoapBuilder.createSoapObject("AddCentro")

            val centro = SoapBuilder.createSoapObject("c","c")

            //Se ha puesto como ID el nombre del centro, si ya existe, da error
            centro.addProperty("ID_Centro", "nuevoCentro.nombreCentro")
            centro.addProperty("Nombre_Usuario1", nuevoCentro.nombreUsuario1)
            centro.addProperty("Nombre_Centro", nuevoCentro.nombreCentro)
            centro.addProperty("Localidad", nuevoCentro.localidad)
            centro.addProperty("Provincia", nuevoCentro.provincia)
            centro.addProperty("Pais", nuevoCentro.pais)
            centro.addProperty("Direccion", nuevoCentro.direccion)
            centro.addProperty("Telefono", nuevoCentro.telefono)
            centro.addProperty("Codigo_Postal", nuevoCentro.codigoPostal)

            request.addSoapObject(centro)

            val result = PeticionSOAP.enviarPeticion(request)

            return SoapBuilder.soapObjectABoolean(result)
        }
}