package com.uma.menpas.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Dictionary

class CalculoResultados {

    fun calculate(jsonResourceName: String, respuestasUsuario: ArrayList<String>): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_csai2" -> calculateCSAI2(respuestasUsuario)
            "preguntas_scat" -> calculateSCAT(respuestasUsuario)
            else -> calculateCSAI2(respuestasUsuario)
        }
    }

    private fun calculateIPED(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "ID_IPED", "Nombre_Usuario", "AC", "CAN", "CAT", "CVI","NM", "CAP", "CACT", "Fecha",
            "Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9",
            "Item10", "Item11", "Item12", "Item13", "Item14", "Item15", "Item16", "Item17", "Item18",
            "Item19", "Item20", "Item21", "Item22", "Item23", "Item24", "Item25", "Item26", "Item27",
            "Item28", "Item29", "Item30", "Item31", "Item32", "Item33", "Item34", "Item35", "Item36",
            "Item37", "Item38", "Item39", "Item40", "Item41", "Item42", "Idioma", "Tiempo"
        )

        val id = "10000" //Método de getId
        val nombreUsuario = formattedString("pruebamenpas")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")

        val listaResultados = mutableListOf<String>()
        val indicesInvertidos = listOf(0,1,2,8,9,13,14,15,19,22,29,30,32,37)
        for ((index, respuesta) in respuestasUsuario.withIndex()) {
            val valor = when (respuesta) { //Se invierten los valores
                "Casi siempre" -> "5"
                "Muchas veces" -> "4"
                "A veces sí, a veces no" -> "3"
                "Pocas veces" -> "2"
                "Casi nunca" -> "1"
                else -> "0"
            }
            if(index in indicesInvertidos) {
                val valorInvertido = when (valor) {
                    "5" -> "1"
                    "4" -> "2"
                    "3" -> "3"
                    "2" -> "4"
                    "1" -> "5"
                    else -> "0"
                }
                listaResultados.add(valorInvertido)
            }
            listaResultados.add(valor)
        }

        val AC = listaResultados[0].toInt() +
                listaResultados[7].toInt() +
                listaResultados[14].toInt() +
                listaResultados[21].toInt() +
                listaResultados[28].toInt() + listaResultados[35].toInt()

        val CAN = listaResultados[1].toInt() +
                listaResultados[8].toInt() +
                listaResultados[15].toInt() +
                listaResultados[22].toInt() +
                listaResultados[29].toInt() + listaResultados[36].toInt()

        val CAT = listaResultados[2].toInt() +
                listaResultados[9].toInt() +
                listaResultados[16].toInt() +
                listaResultados[23].toInt() +
                listaResultados[30].toInt() + listaResultados[37].toInt()

        val CVI = listaResultados[3].toInt() +
                listaResultados[10].toInt() +
                listaResultados[17].toInt() +
                listaResultados[24].toInt() +
                listaResultados[31].toInt() + listaResultados[38].toInt()

        val NM = listaResultados[4].toInt() +
                listaResultados[11].toInt() +
                listaResultados[18].toInt() +
                listaResultados[25].toInt() +
                listaResultados[32].toInt() + listaResultados[39].toInt()

        val CAP = listaResultados[5].toInt() +
                listaResultados[12].toInt() +
                listaResultados[19].toInt() +
                listaResultados[26].toInt() +
                listaResultados[33].toInt() + listaResultados[40].toInt()

        val CACT = listaResultados[6].toInt() +
                listaResultados[13].toInt() +
                listaResultados[20].toInt() +
                listaResultados[27].toInt() +
                listaResultados[34].toInt() + listaResultados[41].toInt()

        val values = listOf(
            id, usuario, resultadoSCAT.toString(), fecha, *itemList.toTypedArray(), idioma, tiempo
        )

        return keys.zip(listaFinal).toMap()

        
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
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss")
        return currentDateTime.format(formatter)
    }

    private fun formattedString(s: String): String {
        return "'$s'"
    }
}