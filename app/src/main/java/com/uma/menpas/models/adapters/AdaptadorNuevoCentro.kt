package com.uma.menpas.models.adapters

import com.uma.menpas.models.NuevoCentro
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive

class AdaptadorNuevoCentro {

    companion object {
        fun soapListToCenterList(result: SoapObject): List<NuevoCentro>? {
            try {
                val centerList = mutableListOf<NuevoCentro>()
                return if(result.propertyCount != 0) {
                    val lista: SoapObject = result.getProperty(0) as SoapObject
                    if(lista.getProperty(0) is SoapPrimitive){ // Si es un solo centro debido al getByID()
                        centerList.add(soapObjectToCenter(lista))
                    } else{
                        for (i in 0 until lista.propertyCount) {
                            val centerToParse = lista.getProperty(i) as SoapObject
                            val newCenter = soapObjectToCenter(centerToParse)
                            centerList.add(newCenter)
                        }
                    }
                    return centerList
                }else{
                    null
                }
            }catch (e : Exception){

                e.printStackTrace()
                return null
            }
        }

        fun soapObjectToCenter(response: SoapObject) : NuevoCentro {
            return NuevoCentro(
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
