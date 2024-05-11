package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils

class Modrian {
    companion object {
        fun calculateColores(respuestasUsuario: ArrayList<String>,  nombreUsuario: String): Map<String, String> {
            val keys = listOf(
                "ID_Modrian",
                "Nombre_Usuario",
                "Numero_Aciertos",
                "Numero_Fallos",
                "Numero_Blancos",
                "Filas",
                "Columnas",
                "Colores",
                "Tiempo_Espera",
                "Tiempo_Final",
                "Tiempo_Realizacion",
                "Fecha",
                "Tipo",
                "Sonido",
                "Idioma",
                "Version"
            )
            val id = CuestionarioService().obtenerIdDisponible("modrian", "ID_Modrian")
            //val nombreUsuarioFormateado = ParseUtils.formattedString(nombreUsuario)
            val nombreUsuarioFormateado = ParseUtils.formattedString("briss")
            val numero_aciertos = respuestasUsuario[0]
            val numero_fallos = respuestasUsuario[1]
            val numero_blancos = respuestasUsuario[2]
            val filas = respuestasUsuario[3]
            val columnas = respuestasUsuario[4]
            val colores = respuestasUsuario[5]
            val tiempo_espera = respuestasUsuario[6]
            val tiempo_final = respuestasUsuario[7]
            val tiempo_realizacion = respuestasUsuario[8]
            val fecha = ParseUtils.formattedString(ParseUtils.obtenerFechaActual())
            val tipo = ParseUtils.formattedString("Modrian Colores")
            val sonido = ParseUtils.formattedString("True")
            val idioma = ParseUtils.formattedString("es-es")
            val version = ParseUtils.formattedString("Android")
            val values = listOf(
                id,
                nombreUsuarioFormateado,
                numero_aciertos,
                numero_fallos,
                numero_blancos,
                filas,
                columnas,
                colores,
                tiempo_espera,
                tiempo_final,
                tiempo_realizacion,
                fecha,
                tipo,
                sonido,
                idioma,
                version
            )
            return keys.zip(values).toMap()
        }
        fun calculateParejas(respuestasUsuario: ArrayList<String>,  nombreUsuario: String): Map<String, String> {
            val keys = listOf(
                "ID_Modrian",
                "Nombre_Usuario",
                "Numero_Aciertos",
                "Numero_Fallos",
                "Numero_Blancos",
                "Filas",
                "Columnas",
                "Colores",
                "Tiempo_Espera",
                "Tiempo_Final",
                "Tiempo_Realizacion",
                "Fecha",
                "Tipo",
                "Sonido",
                "Idioma",
                "Version"
            )
            val id = CuestionarioService().obtenerIdDisponible("modrian", "ID_Modrian")
            //val nombreUsuarioFormateado = ParseUtils.formattedString(nombreUsuario)
            val nombreUsuarioFormateado = ParseUtils.formattedString("briss")
            val numero_aciertos = respuestasUsuario[0]
            val numero_fallos = respuestasUsuario[1]
            val numero_blancos = respuestasUsuario[2]
            val filas = respuestasUsuario[3]
            val columnas = respuestasUsuario[4]
            val colores = 0
            val tiempo_espera = 0
            val tiempo_final = respuestasUsuario[5]
            val tiempo_realizacion = respuestasUsuario[6]
            val fecha = ParseUtils.formattedString(ParseUtils.obtenerFechaActual())
            val tipo = ParseUtils.formattedString("Modrian Parejas")
            val sonido = ParseUtils.formattedString("True")
            val idioma = ParseUtils.formattedString("es-es")
            val version = ParseUtils.formattedString("Android")
            val values = listOf(
                id,
                nombreUsuarioFormateado,
                numero_aciertos,
                numero_fallos,
                numero_blancos,
                filas,
                columnas,
                colores.toString(),
                tiempo_espera.toString(),
                tiempo_final,
                tiempo_realizacion,
                fecha,
                tipo,
                sonido,
                idioma,
                version
            )
            return keys.zip(values).toMap()
        }

        fun calculateSimon(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "ID_Modrian",
                "Nombre_Usuario",
                "Numero_Aciertos",
                "Numero_Fallos",
                "Numero_Blancos",
                "Filas",
                "Columnas",
                "Colores",
                "Tiempo_Espera",
                "Tiempo_Final",
                "Tiempo_Realizacion",
                "Fecha",
                "Tipo",
                "Sonido",
                "Idioma",
                "Version"
            )
            val id = CuestionarioService().obtenerIdDisponible("modrian", "ID_Modrian")
            //val nombreUsuarioFormateado = ParseUtils.formattedString(nombreUsuario)
            val nombreUsuarioFormateado = ParseUtils.formattedString("briss")
            val numero_aciertos = respuestasUsuario[0]
            val numero_fallos = 0
            val numero_blancos = 0
            val filas = 0
            val columnas = 0
            val colores = 0
            val tiempo_espera = 0
            val tiempo_final = respuestasUsuario[1]
            val tiempo_realizacion = 1
            val fecha = ParseUtils.formattedString(ParseUtils.obtenerFechaActual())
            val tipo = ParseUtils.formattedString("Modrian Simon")
            val sonido = ParseUtils.formattedString("True")
            val idioma = ParseUtils.formattedString("es-es")
            val version = ParseUtils.formattedString("Android")
            val values = listOf(
                id,
                nombreUsuarioFormateado,
                numero_aciertos,
                numero_fallos.toString(),
                numero_blancos.toString(),
                filas.toString(),
                columnas.toString(),
                colores.toString(),
                tiempo_espera.toString(),
                tiempo_final,
                tiempo_realizacion.toString(),
                fecha,
                tipo,
                sonido,
                idioma,
                version
            )
            return keys.zip(values).toMap()
        }
    }
}