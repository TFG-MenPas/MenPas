package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class CalidadDeVida {
    companion object {
        fun calculateSF36(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_SF36", "Nombre_Usuario", "F_Física", "Rol_Físico", "Dolor", "Salud", "Vitalidad", "FunciónS", "Rol_Emocional", "Salud_Mental", "Cambio_Salud", "Tiempo", "Idioma", "Fecha", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
                "n10", "n11", "n12", "n13", "n14",
                "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22",
                "n23", "n24", "n25", "n26", "n27",
                "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35",
                "n36"
            )
            val id = CuestionarioService().obtenerIdDisponible("SF36", "Id_SF36")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            val F_Fisica = mutableListOf<Int>()
            val Rol_Fisico = mutableListOf<Int>()
            val Dolor = mutableListOf<Int>()
            val Salud = mutableListOf<Int>()
            val Vitalidad = mutableListOf<Int>()
            val FuncionS = mutableListOf<Int>()
            val Rol_Emocional = mutableListOf<Int>()
            val Salud_Mental = mutableListOf<Int>()
            var Cambio_Salud = 0
            val itemList = mutableListOf<String>()
            for ((index,respuesta) in respuestasUsuario.withIndex()) {
                if (index in listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                    val valor = when (respuesta) {
                        "Sí, me limita mucho" -> "0"
                        "Sí, me limita un poco" -> "50"
                        "No, no me limita nada" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    F_Fisica.add(valor.toInt())
                    continue
                }
                if (index in listOf(12, 13, 14, 15)) {
                    val valor = when (respuesta) {
                        "si" -> "0"
                        "no" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Rol_Fisico.add(valor.toInt())
                    continue
                }
                if (index in listOf(20, 21)) {
                    var valor = ""
                    if (index == 20){
                        valor = when (respuesta) {
                            "No, ninguno" -> "100"
                            "Sí, muy poco" -> "80"
                            "Sí, un poco" -> "60"
                            "Sí, moderado" -> "40"
                            "Sí, mucho" -> "20"
                            "Sí, muchísimo" -> "0"
                            else -> "0"
                        }
                    }else{
                        valor = when (respuesta) {
                            "Nada" -> "100"
                            "Un poco" -> "75"
                            "Regular" -> "50"
                            "Bastante" -> "25"
                            "Mucho" -> "0"
                            else -> "0"
                        }
                    }
                    itemList.add(valor)
                    Dolor.add(valor.toInt())
                    continue
                }
                if (index in listOf(0, 32, 33, 34, 35)) {
                    var valor = ""
                    if (index in listOf(0, 33, 35)){
                        valor = when (respuesta) {
                            "Excelente" -> "100"
                            "Muy buena" -> "75"
                            "Buena" -> "50"
                            "Regular" -> "25"
                            "Mala" -> "0"
                            else -> {
                                when (respuesta){
                                    "Totalmente cierta" -> "100"
                                    "Bastante cierta" -> "75"
                                    "No lo sé" -> "50"
                                    "Bastante falsa" -> "25"
                                    "Totalmente falsa" -> "0"
                                    else -> "0"
                                }
                            }
                        }
                    }
                    if (index in listOf(32, 34)){
                        valor = when (respuesta){
                            "Totalmente cierta" -> "0"
                            "Bastante cierta" -> "25"
                            "No lo sé" -> "50"
                            "Bastante falsa" -> "75"
                            "Totalmente falsa" -> "100"
                            else -> "0"
                        }
                    }
                    itemList.add(valor)
                    Salud.add(valor.toInt())
                    continue
                }
                if (index in listOf(22, 26, 28, 30)) {
                    var valor = ""
                    if (index in listOf(22, 26)){
                        valor = when (respuesta){
                            "Siempre" -> "100"
                            "Casi siempre" -> "80"
                            "Muchas veces" -> "60"
                            "Algunas veces" -> "40"
                            "Sólo alguna vez" -> "20"
                            "Nunca" -> "0"
                            else -> "0"
                        }
                    }
                    if (index in listOf(28, 30)){
                        valor = when (respuesta){
                            "Siempre" -> "0"
                            "Casi siempre" -> "20"
                            "Muchas veces" -> "40"
                            "Algunas veces" -> "60"
                            "Sólo alguna vez" -> "80"
                            "Nunca" -> "100"
                            else -> "0"
                        }
                    }
                    itemList.add(valor)
                    Vitalidad.add(valor.toInt())
                    continue
                }
                if (index in listOf(19, 31)) {
                    var valor = ""
                    if (index == 19){
                        valor = when (respuesta) {
                            "Nada" -> "100"
                            "Un poco" -> "75"
                            "Regular" -> "50"
                            "Bastante" -> "25"
                            "Mucho" -> "0"
                            else -> "0"
                        }
                    } else {
                        valor = when (respuesta){
                            "Siempre" -> "0"
                            "Casi siempre" -> "20"
                            "Muchas veces" -> "40"
                            "Algunas veces" -> "60"
                            "Sólo alguna vez" -> "80"
                            "Nunca" -> "100"
                            else -> "0"
                        }
                    }
                    itemList.add(valor)
                    FuncionS.add(valor.toInt())
                    continue
                }
                if (index in listOf(16, 17, 18)) {
                    val valor = when (respuesta) {
                        "si" -> "0"
                        "no" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Rol_Emocional.add(valor.toInt())
                    continue
                }
                if (index in listOf(23, 24, 25, 27, 29)) {
                    var valor = ""
                    if (index in listOf(23, 24, 27)){
                        valor = when (respuesta){
                            "Siempre" -> "0"
                            "Casi siempre" -> "20"
                            "Muchas veces" -> "40"
                            "Algunas veces" -> "60"
                            "Sólo alguna vez" -> "80"
                            "Nunca" -> "100"
                            else -> "0"
                        }
                    }else{
                        valor = when (respuesta){
                            "Siempre" -> "100"
                            "Casi siempre" -> "80"
                            "Muchas veces" -> "60"
                            "Algunas veces" -> "40"
                            "Sólo alguna vez" -> "20"
                            "Nunca" -> "0"
                            else -> "0"
                        }
                    }
                    itemList.add(valor)
                    Salud_Mental.add(valor.toInt())
                    continue
                }
                if (index == 1){
                    val valor = when (respuesta){
                        "Mucho mejor ahora que hace un año" -> "100"
                        "Algo mejor ahora que hace un año" -> "75"
                        "Más o menos igual que hace un año" -> "50"
                        "Algo peor ahora que hace un año" -> "25"
                        "Mucho peor ahora que hace un año" -> "0"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Cambio_Salud = valor.toInt()
                }
            }
            val values = listOf(
                id, nombreUsuario, F_Fisica.average().toInt().toString(), Rol_Fisico.average().toInt().toString(), Dolor.average().toInt().toString(), Salud.average().toInt().toString(),
                Vitalidad.average().toInt().toString(), FuncionS.average().toInt().toString(), Rol_Emocional.average().toInt().toString(), Salud_Mental.average().toInt().toString(),
                Cambio_Salud.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
            )
            return keys.zip(values).toMap()
        }

        fun calculateSF12(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_SF12", "Nombre_Usuario", "Medida_S_Físico", "Medida_S_Mental", "Tiempo", "Idioma", "Fecha", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
                "n10", "n11", "n12"
            )
            val id = CuestionarioService().obtenerIdDisponible("SF12", "Id_SF12")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            val Medida_S_Fisica = mutableListOf<Int>()
            val Medida_S_Mental = mutableListOf<Int>()
            val itemList = mutableListOf<String>()
            for ((index,respuesta) in respuestasUsuario.withIndex()) {
                if (index == 0) {
                    val valor = when (respuesta) {
                        "Excelente" -> "100"
                        "Muy Buena" -> "75"
                        "Buena" -> "50"
                        "Regular" -> "25"
                        "Mala" -> "0"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Fisica.add(valor.toInt())
                    continue
                }
                if (index in listOf(1, 2)) {
                    val valor = when (respuesta) {
                        "Sí, me limita mucho" -> "0"
                        "Sí, me limita un poco" -> "50"
                        "No, no me limita nada" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Fisica.add(valor.toInt())
                    continue
                }
                if (index in listOf(3, 4)) {
                    val valor = when (respuesta) {
                        "si" -> "0"
                        "no" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Fisica.add(valor.toInt())
                    continue
                }
                if (index == 7) {
                    val valor = when (respuesta) {
                        "Nada" -> "100"
                        "Un poco" -> "75"
                        "Regular" -> "50"
                        "Bastante" -> "25"
                        "Mucho" -> "0"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Fisica.add(valor.toInt())
                    continue
                }
                if (index in listOf(5, 6)) {
                    val valor = when (respuesta) {
                        "si" -> "0"
                        "no" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Mental.add(valor.toInt())
                    continue
                }
                if (index in listOf(8, 9, 10)) {
                    val valor = when (respuesta) {
                        "Siempre" -> "100"
                        "Casi siempre" -> "80"
                        "Muchas veces" -> "60"
                        "Algunas veces" -> "40"
                        "Solo una vez" -> "20"
                        "Nunca" -> "0"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Mental.add(valor.toInt())
                    continue
                }
                if (index == 11) {
                    val valor = when (respuesta) {
                        "Siempre" -> "0"
                        "Casi siempre" -> "25"
                        "Algunas veces" -> "50"
                        "Solo una vez" -> "75"
                        "Nunca" -> "100"
                        else -> "0"
                    }
                    itemList.add(valor)
                    Medida_S_Mental.add(valor.toInt())
                }
            }
            val values = listOf(
                id, nombreUsuario, Medida_S_Fisica.average().toInt().toString(), Medida_S_Mental.average().toInt().toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
            )
            return keys.zip(values).toMap()
        }

        fun calculateVS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_Vitalidad", "Nombre_Usuario", "sol", "n1", "n2", "n3", "n4", "n5", "n6", "Tiempo", "Idioma", "Fecha"
            )
            val id = CuestionarioService().obtenerIdDisponible("Vitalidad", "Id_Vitalidad")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            val valores = mutableListOf<Int>()
            val itemList = mutableListOf<String>()
            for (respuesta in respuestasUsuario) {
                val valor = when (respuesta) {
                    "Totalmente en desacuerdo" -> "1"
                    "Muy en desacuerdo" -> "2"
                    "Algo en desacuerdo" -> "3"
                    "Neutral" -> "4"
                    "Algo de acuerdo" -> "5"
                    "Muy de acuerdo" -> "6"
                    "Totalmente de acuerdo" -> "7"
                    else -> "0"
                }
                itemList.add(valor)
                valores.add(valor.toInt())
            }
            val values = listOf(
                id, nombreUsuario, valores.average().toInt().toString(), *itemList.toTypedArray(), tiempo, idioma, fecha,
            )
            return keys.zip(values).toMap()
        }
    }
}