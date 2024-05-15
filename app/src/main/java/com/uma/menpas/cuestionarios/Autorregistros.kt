package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class Autorregistros {
    companion object {
        fun calculateAutoComida(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_Autorregistro", "Nombre_Usuario", "Dia", "Hora", "Tiempo", "Cantidad", "Actividad", "LugarComida", "Comida_tipo", "Calorias", "Categoria", "Idioma", "Fecha", "Tipo"
            )
            val id = CuestionarioService().obtenerIdDisponible("Autorregistros", "Id_Autorregistro")
            val nombreUsuario = formattedString(usuario)
            val tipo = formattedString("A Comida")
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val itemList = mutableListOf<String>()
            for ((index,respuesta) in respuestasUsuario.withIndex()) {
                if(index == 0){
                    //Fecha
                    val c = Calendar.getInstance()
                    c.timeInMillis = respuesta.toLong()
                    val currentDateTime = LocalDateTime.of( c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(
                        Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR), c.get(Calendar.MINUTE))
                    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
                    itemList.add(formattedString(currentDateTime.format(formatter)))
                    continue
                }
                if(index in listOf(4, 5, 6, 7, 8)){
                    itemList.add(formattedString(respuesta))
                }else{
                    itemList.add(respuesta)
                }
            }
            val values = listOf(
                id, nombreUsuario, *itemList.toTypedArray(), idioma, fecha, tipo
            )
            return keys.zip(values).toMap()
        }

        fun calculateAutorregistroDiario(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("ID_Autorregistro", "Nombre_Usuario", "Fecha", "Tipo",
                "Tiempo", "Idioma", "Dia", "Peso", "HorasSueño", "Pulsaciones",
                "Animo", "T_Minima", "T_Maxima", "T_P_Deportiva", "Contenido_Prac",
                "P_Esfuerzo", "C_Ejercicio", "I_Ejercicio", "EventoDestacado", "Estatura")

            val idAutorregistro = CuestionarioService().obtenerIdDisponible("Autorregistros", "ID_Autorregistro")
            val fecha = obtenerFechaActual()
            val tipo = "A Diario"
            val tiempo = "100"
            val idioma = "es-es"
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            respuestasUsuario[0] = dateFormat.format(Date(respuestasUsuario[0].toLong()))
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                if (index in listOf(1,2,4,7,13)) {
                    respuestasUsuario[index] = formattedString(respuesta)
                    if (index == 4) {
                        respuestasUsuario[index] = when (respuestasUsuario[index]) {
                            "Deprimido" -> "1"
                            "Cabreado" -> "2"
                            "Triste" -> "3"
                            "Aburrido" -> "4"
                            "Neutro" -> "5"
                            "Contento" -> "6"
                            "Alegre" -> "7"
                            "Feliz" -> "8"
                            else -> "1"
                        }
                    }
                }
            }

            val values = listOf(idAutorregistro, usuario, fecha, tipo, tiempo.toString(), idioma, *respuestasUsuario.toTypedArray())

            return keys.zip(values).toMap()
        }

        fun calculateAutorregistroEntrenamiento(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            var keys = mutableListOf("ID_Autorregistro", "Nombre_Usuario", "Fecha", "Tipo",
                "Tiempo", "Idioma", "Dia", "Ob_general", "Tarea", "Deporte",
                "Objetivo")

            val idAutorregistro = CuestionarioService().obtenerIdDisponible("Autorregistros", "ID_Autorregistro")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val tipo = formattedString("A Diario")
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")

            respuestasUsuario[0] = dateFormat.format(Date(respuestasUsuario[0].toLong()))
            val series = respuestasUsuario[5].split("|").drop(1).dropLast(1)
            respuestasUsuario.removeAt(5)
            val respuestas = respuestasUsuario.map { it -> formattedString(it)}

            var i = 1
            var j = 0
            var seriesValues = mutableListOf<String>()
            while (j < series.size) {
                keys.add("S" + i.toString() + "A")
                keys.add("S" + i.toString() + "E")
                keys.add("S" + i.toString() + "D")

                seriesValues.add(series[j])
                seriesValues.add(series[j+1])
                val sd = series[j].toInt() - series[j+1].toInt()
                seriesValues.add(sd.toString())

                i++
                j += 2
            }

            val values = listOf(idAutorregistro, nombreUsuario, fecha, tipo, tiempo, idioma, *respuestas.toTypedArray(), *seriesValues.toTypedArray())

            return keys.zip(values).toMap()
        }

        fun calculateAutorregistroLibre(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("ID_Autorregistro", "Nombre_Usuario", "Fecha", "Tipo", "campo1","campo2","campo3","campo4",
                "campo5","campo6","campo7","campo8","campo9","campo10")
            val id = CuestionarioService().obtenerIdDisponible("Autorregistros", "ID_Autorregistro")
            val fecha = formattedString(obtenerFechaActual())
            val tipo = formattedString("A Libre")
            val itemList = respuestasUsuario.map {it -> formattedString(it)}
            val values = listOf(id, formattedString(usuario), fecha, tipo, *itemList.toTypedArray())
            return keys.zip(values).toMap()
        }

        fun calculateAutorregistroPN(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("ID_Autorregistro", "Nombre_Usuario", "Fecha", "Tipo",
                "Tiempo", "Idioma", "Dia", "Hora", "Lugar", "Conducta_Previa",
                "Pensamiento_Negativo", "Intensidad", "Conducta_Posterior", "Pensamiento_Positivo")
            val idAutorregistro = CuestionarioService().obtenerIdDisponible("Autorregistros", "ID_Autorregistro")
            val fecha = obtenerFechaActual()
            val tipo = "A Diario"
            val tiempo = "100"
            val idioma = "es-es"
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            respuestasUsuario[0] = dateFormat.format(Date(respuestasUsuario[0].toLong()))
            val values = listOf(idAutorregistro, usuario, fecha, tipo, tiempo, idioma, *respuestasUsuario.toTypedArray())
            return keys.zip(values).toMap()
        }
    }
}