package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual
import kotlin.math.round

class Autoconcepto {
    companion object {
        fun calculateAF5(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_AF5", "Nombre_Usuario", "Académico", "Social", "Emocional", "Familiar", "Físico", "Tiempo", "Idioma", "Fecha", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
                "n10", "n11", "n12", "n13", "n14", "n15", "n16",
                "n17", "n18", "n19", "n20", "n21", "n22", "n23", "n24",
                "n25", "n26", "n27", "n28", "n29", "n30"
            )
            val id = CuestionarioService().obtenerIdDisponible("AF5", "Id_AF5")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            var Academico = 0
            var Social = 0
            var Emocional = 0
            var Familiar = 0
            var Fisico = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                itemList.add(respuesta)
                if (index in listOf(0, 5, 10, 15, 20, 25)){
                    Academico += respuesta.toInt()
                    continue
                }
                if (index in listOf(1, 6, 11, 16, 21, 26)) {
                    Social += respuesta.toInt()
                    continue
                }
                if (index in listOf(2, 7, 12, 17, 22, 27)) {
                    Emocional += respuesta.toInt()
                    continue
                }
                if (index in listOf(3, 8, 13, 18, 23, 28)) {
                    Familiar += respuesta.toInt()
                    continue
                }
                if (index in listOf(4, 9, 14, 19, 24, 29)) {
                    Fisico += respuesta.toInt()
                }
            }
            val values = listOf(
                id, nombreUsuario, round((Academico / 60).toDouble()).toString(),  round((Social / 60).toDouble()).toString(),  round((Emocional / 60).toDouble()).toString(),
                round((Familiar / 60).toDouble()).toString(),  round((Fisico / 60).toDouble()).toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
            )
            return keys.zip(values).toMap()
        }

        fun calculateBSQ(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_BSQ", "Nombre_Usuario", "Factor1", "Tiempo", "Idioma", "Fecha", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
                "n10", "n11", "n12", "n13", "n14"
            )
            val id = CuestionarioService().obtenerIdDisponible("BSQ", "Id_BSQ")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            var Factor1 = 0
            val itemList = mutableListOf<String>()
            for (respuesta in respuestasUsuario) {
                val valor = when (respuesta) {
                    "Nunca" -> "1"
                    "Raramente" -> "2"
                    "Algunas veces" -> "3"
                    "A menudo" -> "4"
                    "Muy a menudo" -> "5"
                    "Siempre" -> "6"
                    else -> "0"
                }
                itemList.add(valor)
                Factor1 += valor.toInt()
            }
            val values = listOf(
                id, nombreUsuario, Factor1.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
            )
            return keys.zip(values).toMap()
        }

        fun calculateCAF(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_CAF", "Nombre_Usuario", "Habilidad", "Condición", "Atractivo", "Fuerza", "AutoconceptoF", "AutoconceptoG", "Tiempo", "Idioma", "Fecha", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
                "n10", "n11", "n12", "n13", "n14",
                "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22",
                "n23", "n24", "n25", "n26", "n27",
                "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35",
                "n36"
            )
            val id = CuestionarioService().obtenerIdDisponible("CAF", "Id_CAF")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            var Habilidad = 0
            var Condicion = 0
            var Atractivo = 0
            var Fuerza = 0
            var AutoconceptoF = 0
            var AutoconceptoG = 0
            val itemList = mutableListOf<String>()
            for ((index,respuesta) in respuestasUsuario.withIndex()) {
                var valor = when (respuesta) {
                    "Falso" -> "1"
                    "Casi siempre falso" -> "2"
                    "A veces verdadero/falso" -> "3"
                    "Casi siempre verdadero" -> "4"
                    "Verdadero" -> "5"
                    else -> "0"
                }
                if (index in listOf(4, 5, 6, 7, 8, 14, 15, 21, 22, 23, 24, 25, 31, 32, 34, 35)){
                    valor = when (valor) {
                        "5" -> "1"
                        "4" -> "2"
                        "3" -> "3"
                        "2" -> "4"
                        "1" -> "5"
                        else -> "3"
                    }
                }
                itemList.add(valor)
                if (index in listOf(0, 5, 16, 22, 27, 32)){
                    Habilidad += valor.toInt()
                    continue
                }
                if (index in listOf(1, 6, 10, 17, 23, 28)){
                    Condicion += valor.toInt()
                    continue
                }
                if (index in listOf(7, 11, 18, 24, 29, 33)){
                    Atractivo += valor.toInt()
                    continue
                }
                if (index in listOf(2, 8, 12, 19, 30, 34)){
                    Fuerza += valor.toInt()
                    continue
                }
                if (index in listOf(3, 13, 15, 20, 25, 35)){
                    AutoconceptoF += valor.toInt()
                    continue
                }
                if (index in listOf(4, 9, 14, 21, 26, 31)){
                    AutoconceptoG += valor.toInt()
                }
            }
            val values = listOf(
                id, nombreUsuario, Habilidad.toString(), Condicion.toString(), Atractivo.toString(), Fuerza.toString(), AutoconceptoF.toString(), AutoconceptoG.toString(),tiempo, idioma, fecha, *itemList.toTypedArray()
            )
            return keys.zip(values).toMap()
        }
    }
}