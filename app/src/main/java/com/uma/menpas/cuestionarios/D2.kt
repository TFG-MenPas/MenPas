package com.uma.menpas.cuestionarios

import android.content.Context
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils

class D2 {
    companion object{
        fun calculate(respuestasUsuario: ArrayList<String>, nombreUsuario: String): Map<String, String> {
            val keys = listOf(
                "Id_d2",
                "Nombre_Usuario",
                "Tipo",
                "TOT",
                "CON",
                "VAR",
                "TR",
                "O",
                "C",
                "TA",
                "TRmax",
                "TRmin",
                "TR1_14",
                "TA1_14",
                "O1_14",
                "C1_14",
                "Tiempo",
                "Idioma",
                "Fecha",
                "TipoUser"
            )
            val Tipo = ParseUtils.formattedString(respuestasUsuario[0])
            val TR1_14 = respuestasUsuario[1]
            val TA1_14 = respuestasUsuario[2]
            val O1_14 = respuestasUsuario[3]
            val C1_14 = respuestasUsuario[4]
            val id = CuestionarioService().obtenerIdDisponible("d2", "Id_d2")
            val nombreUsuarioFormateado = ParseUtils.formattedString(nombreUsuario)
            val fecha = ParseUtils.formattedString(ParseUtils.obtenerFechaActual())
            val idioma = ParseUtils.formattedString("es-es")
            val tiempo = 281.1
            val tipoUser = ParseUtils.formattedString("Registrado")
            val TR = sumar(TR1_14)
            val O = sumar(O1_14)
            val C = sumar(C1_14)
            val TA = sumar(TA1_14)
            val TRmax = maximo(TR1_14)
            val TRmin = minimo(TR1_14)
            val TOT = TR - (O + C)
            val CON = TA - C
            val VAR = TRmax - TRmin
            val values = listOf(
                id,
                nombreUsuarioFormateado,
                Tipo,
                TOT.toString(),
                CON.toString(),
                VAR.toString(),
                TR.toString(),
                O.toString(),
                C.toString(),
                TA.toString(),
                TRmax.toString(),
                TRmin.toString(),
                ParseUtils.formattedString(TR1_14),
                ParseUtils.formattedString(TA1_14),
                ParseUtils.formattedString(O1_14),
                ParseUtils.formattedString(C1_14),
                tiempo.toString(),
                idioma,
                fecha,
                tipoUser
                )
            return keys.zip(values).toMap()
        }
        private fun sumar(lista: String): Int {
            var sum = 0
            val valores = lista.split(';')
            for(value in valores){
                sum += value.toInt()
            }
            return sum
        }
        private fun maximo(lista: String): Int{
            var max = 0
            val valores = lista.split(';')
            for(value in valores){
                if(value.toInt() > max){
                    max = value.toInt()
                }
            }
            return max
        }
        private fun minimo(lista: String): Int{
            var min = 0
            val valores = lista.split(';')
            for(value in valores){
                if(value.toInt() < min){
                    min = value.toInt()
                }
            }
            return min
        }
    }
}