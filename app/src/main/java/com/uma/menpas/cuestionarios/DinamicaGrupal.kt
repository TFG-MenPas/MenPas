package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class DinamicaGrupal {
    companion object {
        fun calculateCCD(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("Id_CCDeportiva", "Nombre_Usuario", "DCC", "DCI","SC",
                "SE", "SF", "n1", "n2", "n3", "n4",
                "n5", "n6", "n7", "n8", "n9", "n10", "n11", "n12", "n13", "n14", "n15", "Fecha", "Idioma",
                "Tiempo")
            val id = CuestionarioService().obtenerIdDisponible("CCDeportiva", "Id_CCDeportiva")
            val nombreUsuario = formattedString(usuario)
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val fecha = formattedString(obtenerFechaActual())
            var dcc = 0
            var dci = 0
            var sc = 0
            var se = 0
            var sf = 0
            var itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Nada" -> "0"
                    "Muy poco" -> "1"
                    "Algo" -> "2"
                    "Bastante" -> "3"
                    "Mucho" -> "4"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(0,3,7,11)) {
                    dcc += valor.toInt()
                } else if (index in listOf(1,10,13,14)) {
                    dci += valor.toInt()
                }else if (index in listOf(5,12)) {
                    sc += valor.toInt()
                } else if (index in listOf(4,6,8)) {
                    se += valor.toInt()
                }else {
                    sf += valor.toInt()
                }
            }
            val values = listOf(id, nombreUsuario, dcc.toString(), dci.toString(),
                sc.toString(), se.toString(), sf.toString(), *itemList.toTypedArray(), fecha,idioma,tiempo)
            return keys.zip(values).toMap()
        }
    }
}