package com.uma.menpas.models.mappers

import com.uma.menpas.models.Centro
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive

class CentroSoapMapper {

    companion object {
        fun soapListToCenterList(result: SoapObject): List<Centro> {
            val centerList = mutableListOf<Centro>()

            try {
                if(result.propertyCount != 0) {
                    val lista: SoapObject = result.getProperty(0) as SoapObject
                    if(lista.propertyCount != 0) { //Si no hay ningún centro
                        if (lista.getProperty(0) is SoapPrimitive) { // Si es un solo centro debido al getByID()
                            centerList.add(soapObjectToCenter(lista))
                        } else {
                            for (i in 0 until lista.propertyCount) {
                                val centerToParse = lista.getProperty(i) as SoapObject
                                val newCenter = soapObjectToCenter(centerToParse)
                                centerList.add(newCenter)
                            }
                        }
                    }
                }
                return centerList
            }catch (e : Exception){
                e.printStackTrace()
                return centerList
            }
        }

        fun soapObjectToCenter(response: SoapObject) : Centro {
            return Centro(
                response.getPrimitiveProperty("ID_Centro").toString(),
                response.getPrimitiveProperty("Nombre_Usuario1").toString(),
                response.getPrimitiveProperty("Nombre_Centro").toString(),
                response.getPrimitiveProperty("Localidad").toString(),
                response.getPrimitiveProperty("Provincia").toString(),
                response.getPrimitiveProperty("Pais").toString(),
                response.getPrimitiveProperty("Direccion").toString(),
                response.getPrimitiveProperty("Telefono").toString(),
                response.getPrimitiveProperty("Codigo_Postal").toString(),
                response.getPrimitiveProperty("Limitado").toString(),
                response.getPrimitiveProperty("Contador").toString()
            )
        }
    }
}
