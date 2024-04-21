package com.uma.menpas.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Dictionary

class CalculoResultados {

    fun calculate(jsonResourceName: String, respuestasUsuario: ArrayList<String>): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_csai2" -> calculateCSAI2(respuestasUsuario)
            else -> calculateCSAI2(respuestasUsuario)
        }
    }

    private fun calculateCSAI2(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf("id", "Nombre_Usuario","Cognitiva", "Somatica", "Autoconfianza", "Fecha", "Item1","Item2","Item3","Item4",
            "Item5","Item6","Item7","Item8","Item9","Item10","Item11","Item12","Item13","Item14",
            "Item15","Item16","Item17","Item18","Item19","Item20","Item21","Item22","Item23","Item24",
            "Item25","Item26","Item27","Idioma","Tipo","Tiempo")

        val id = listOf("10000")
        val nombreUsuario = listOf("'" + "manuel.agm" + "'")

        val values = mutableListOf(0,0,0)
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
        val finalValues = values.map {it.toString()}

        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")
        val formattedDateTime = currentDateTime.format(formatter)
        val fecha = listOf("'" + formattedDateTime + "'")

        val respuestasUsuarioFinal = respuestasUsuarioInt.map {it.toString()}

        val ultimosValores = listOf("'" + "es-es" + "'", "'" + "Csai2" + "'", "100")

        val listaFinal = id + nombreUsuario + finalValues + fecha + respuestasUsuarioFinal + ultimosValores

        return keys.zip(listaFinal).toMap()
    }
}