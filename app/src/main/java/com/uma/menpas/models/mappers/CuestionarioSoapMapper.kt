package com.uma.menpas.models.mappers

import com.uma.menpas.models.Cuestionario
import com.uma.menpas.models.TipoCuestionario
import org.ksoap2.serialization.PropertyInfo
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

        fun soapObjectToDetalleCuestionario(result: SoapObject): MutableMap<String, String> {
            val mapaResultados = mutableMapOf<String, String>()
            try {
                if(result.propertyCount != 0) {
                    val cuestionario: SoapObject = result.getProperty(0) as SoapObject

                    val valoresObject = cuestionario.getProperty(2) as SoapObject

                    val nombreCuestionario = (cuestionario.getProperty(0) as SoapPrimitive).value.toString() // El nombre por si es necesario
                    val cabeceras = cuestionario.getProperty(1) as SoapObject
                    val valores = valoresObject.getProperty(0) as SoapObject

                    for(i in 0 until  cabeceras.propertyCount) {
                        val tipoDato = cabeceras.getProperty(i) as SoapPrimitive
                        val dato = valores.getProperty(i)
                        val  d = 2
                        mapaResultados[tipoDato.value.toString()] = dato.toString()
                    }

                }
                return mapaResultados
            }catch (e : Exception){
                e.printStackTrace()
                return mapaResultados
            }
        }

        private fun soapObjectToCuestionario(response: SoapObject) : Cuestionario {
            val nombreYTipoCuestionario = relacionTiposCuestionario.obtenerNombreYTipo(response.getProperty(0).toString()) // Par<Tipo, Nombnre>
            return Cuestionario(
                response.getProperty(1).toString(),
                nombreYTipoCuestionario.second,
                nombreYTipoCuestionario.first,
                formatearFecha(response.getProperty(2).toString()) // Fecha
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