package com.uma.menpas.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Dictionary

class CalculoResultados {

    fun calculate(jsonResourceName: String, respuestasUsuario: ArrayList<String>): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_csai2" -> calculateCSAI2(respuestasUsuario)
            "preguntas_scat" -> calculateSCAT(respuestasUsuario)
            "preguntas_acsi_28" -> calculateACSI28(respuestasUsuario)
            else -> calculateCSAI2(respuestasUsuario)
        }
    }


    private fun calculateACSI28(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf("Id_ACSI_28", "Nombre_Usuario", "Rendimiento", "Ausencia", "Confrontacion",
        "Concentracion", "Formulacion", "Confianza", "Capacidad", "Tiempo", "Idioma", "Fecha",
        "n1","n2","n3","n4","n5","n6","n7","n8","n9","n10","n11","n12","n13","n14","n15","n16","n17","n18",
            "n19","n20","n21","n22","n23","n24","n25","n26","n27","n28")
        val id = "10000"
        val nombre_usuario = formattedString("manuel.agm")
        val tiempo = "100"
        val idioma = formattedString("es-es")
        val fecha = formattedString(obtenerFechaActual())
        val valores = mutableListOf(0,0,0,0,0,0,0)
        val itemList = mutableListOf<String>()
        for ((index, respuesta) in respuestasUsuario.withIndex()) {
            val valor = when (respuesta) {
                "Casi nunca" -> "0"
                "A veces" -> "1"
                "A menudo" -> "2"
                "Casi siempre de acuerdo" -> "3"
                else -> "0"
            }
            itemList.add(valor)
            if (index in listOf(6,18,22,28)) {
                valores[0] += valor.toInt()
            } else if (index in listOf(7,12,19,23)) {
                valores[1] += valor.toInt()
            }else if (index in listOf(5,17,21,24)) {
                valores[2] += valor.toInt()
            }else if (index in listOf(4,11,16,25)) {
                valores[3] += valor.toInt()
            }else if (index in listOf(1,8,13,20)) {
                valores[4] += valor.toInt()
            }else if (index in listOf(2,9,14,26)) {
                valores[5] += valor.toInt()
            }else if (index in listOf(3,10,15,27)) {
                valores[6] += valor.toInt()
            }
        }
        val valoresString = valores.map {it -> it.toString()}
        val values = listOf(id,nombre_usuario, *valoresString.toTypedArray(), tiempo, idioma, fecha, *itemList.toTypedArray() )
        return keys.zip(values).toMap()
    }

    private fun calculateSCAT(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "ID_Scat", "Nombre_Usuario", "Resultado_Scat", "Fecha", "Item1",
            "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9",
            "Item10", "Item11", "Item12", "Item13", "Item14", "Item15", "Idioma", "Tiempo"
        )
        val id = "10000"
        val usuario = formattedString("manuel.agm")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        var resultadoSCAT = 0
        val itemList = mutableListOf<String>()
        for ((index, respuesta) in respuestasUsuario.withIndex()) {
            val valor = when (respuesta) {
                "Casi nunca" -> "1"
                "A veces" -> "2"
                "A menudo" -> "3"
                else -> "0"
            }
            itemList.add(valor)
            if (index in listOf(1, 2, 4, 7, 8, 11, 13, 14)) {
                resultadoSCAT += valor.toInt()
            } else if (index in listOf(5, 10)) {
                resultadoSCAT += when (valor.toInt()) {
                    3 -> 1
                    1 -> 3
                    else -> 2
                }
            }
        }
        val values = listOf(
            id, usuario, resultadoSCAT.toString(), fecha, *itemList.toTypedArray(), idioma, tiempo
        )
        return keys.zip(values).toMap()
    }

    private fun calculateCSAI2(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "ID_CSAI2", "Nombre_Usuario", "Cognitiva", "Somatica", "Autoconfianza", "Fecha", "Item1", "Item2", "Item3", "Item4",
            "Item5", "Item6", "Item7", "Item8", "Item9", "Item10", "Item11", "Item12", "Item13", "Item14",
            "Item15", "Item16", "Item17", "Item18", "Item19", "Item20", "Item21", "Item22", "Item23", "Item24",
            "Item25", "Item26", "Item27", "Idioma", "Tipo", "Tiempo"
        )

        val id = listOf("10000")
        val nombreUsuario = listOf("'" + "manuel.agm" + "'")

        val values = mutableListOf(0, 0, 0)
        var i = 0
        val respuestasUsuarioInt = respuestasUsuario.map {
            when (it) {
                "Siempre" -> 5
                "Casi siempre" -> 4
                "A veces" -> 3
                "Casi nunca" -> 2
                "Nunca" -> 1
                else -> 0
            }
        }
        respuestasUsuarioInt.forEach {
            values[i] += it
            when (i) {
                0 -> i = 1
                1 -> i = 2
                2 -> i = 0
            }
        }

        val fecha = listOf("'" + obtenerFechaActual() + "'")

        val idioma = listOf("'" + "es-es" + "'")
        val tipo = listOf("'" + "Csai2" + "'")
        val tiempo = listOf("100")

        val listaFinal = id + nombreUsuario + values.map { it.toString() } + fecha + respuestasUsuarioInt.map { it.toString() } + idioma + tipo + tiempo

        return keys.zip(listaFinal).toMap()
    }

    private fun obtenerFechaActual(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")
        return currentDateTime.format(formatter)
    }

    private fun formattedString(s: String): String {
        return "'$s'"
    }
}