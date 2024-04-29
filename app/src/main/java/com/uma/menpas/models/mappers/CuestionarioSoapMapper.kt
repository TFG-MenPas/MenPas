package com.uma.menpas.models.mappers

import com.uma.menpas.models.Cuestionario
import com.uma.menpas.models.TipoCuestionario
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import java.text.SimpleDateFormat

class CuestionarioSoapMapper {

    companion object {
        val relacionTiposCuestionario = TipoCuestionario()
        fun soapListToListCuestionario(result: SoapObject): List<Cuestionario> {
            val listaCuestionario = mutableListOf<Cuestionario>()
            try {
                if(result.propertyCount != 0) {
                    val lista: SoapObject = result.getProperty(0) as SoapObject
                    if(lista.propertyCount != 0) { //Si no hay ningún cuestionario
                        if (lista.getProperty(0) is SoapPrimitive) { // Si es un solo un cuestionario debido al getByID()
                            listaCuestionario.add(soapObjectToCuestionario(lista))
                        } else {
                            for (i in 0 until lista.propertyCount) {
                                val cuestionarioToParse = lista.getProperty(i) as SoapObject
                                val nuevoCuestionario = soapObjectToCuestionario(cuestionarioToParse)
                                listaCuestionario.add(nuevoCuestionario)
                            }
                        }
                    }
                }
                return listaCuestionario
            }catch (e : Exception){
                e.printStackTrace()
                return listaCuestionario
            }
        }

        fun soapObjectToCuestionario(response: SoapObject) : Cuestionario {
            val nombreYTipoCuestionario = relacionTiposCuestionario.obtenerNombreYTipo(response.getProperty(0).toString()) // Par<Tipo, Nombnre>
            return Cuestionario(
                nombreYTipoCuestionario.second,
                nombreYTipoCuestionario.first,
                formatearFecha(response.getProperty(1).toString()) // Fecha
            )
        }

        private fun formatearFecha(fecha: String): String {
            val formatoOriginal = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
            val fechaAntigua = formatoOriginal.parse(fecha)

            return fechaAntigua?.let { fechaFormateada ->
                val nuevoFormato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                nuevoFormato.format(fechaFormateada)
            } ?: ""
        }
    }
}