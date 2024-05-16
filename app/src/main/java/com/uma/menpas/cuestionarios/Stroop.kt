package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class Stroop {
    companion object {
        fun calculateStroop(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("ID_Stroop", "Nombre_Usuario", "T_medio","Fecha", "Idioma", "Version",  "Aciertos", "Fallos", "T_total",
                "Ncolores", "Tipo", "Fondo", "TExpo", "NPresentaciones", "E_Omision")
            val id = CuestionarioService().obtenerIdDisponible("stroop", "ID_Stroop")
            val nombreUsuario = formattedString(usuario)

            val tiempoMedioFloat = (respuestasUsuario[2].toFloat() / respuestasUsuario[7].toFloat())

            val tiempoMedio = String.format("%.2f", tiempoMedioFloat)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val version = formattedString("Android")
            respuestasUsuario[2] = String.format("%.2f", respuestasUsuario[2].toFloat())
            respuestasUsuario[4] = formattedString(respuestasUsuario[4])
            respuestasUsuario[5] = if (respuestasUsuario[5] == "false") formattedString("NO") else formattedString("SI")
            val tiempoExpo = respuestasUsuario[6].toFloat() / 1000
            respuestasUsuario[6] = String.format("%.2f",tiempoExpo)

            val values = listOf(id, nombreUsuario, tiempoMedio, fecha, idioma, version, *respuestasUsuario.toTypedArray())
            return keys.zip(values).toMap()
        }
    }
}