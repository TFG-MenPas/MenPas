package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class Burnout {
    companion object {
        fun calculateMaslach(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "ID_Burnout", "Nombre_Usuario", "Escala_CE", "Escala_DP", "Escala_RP", "Fecha", "Item1",
                "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9",
                "Item10", "Item11", "Item12", "Item13", "Item14", "Item15", "Item16",
                "Item17", "Item18", "Item19", "Item20", "Item21", "Item22",
                "Idioma", "Tiempo"
            )
            val id = CuestionarioService().obtenerIdDisponible("burnout", "ID_Burnout")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            var escalaCE = 0
            var escalaDP = 0
            var escalaRP = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Nunca" -> "0"
                    "Pocas veces al año o menos" -> "1"
                    "Una vez al mes o menos" -> "2"
                    "Unas pocas veces al mes" -> "3"
                    "Una vez a la semana" -> "4"
                    "Pocas veces a la semana" -> "5"
                    "Todos los días" -> "6"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(0, 1, 2, 5, 7, 12, 13, 15, 19)) {
                    escalaCE += valor.toInt()
                    continue
                }
                if (index in listOf(3, 6, 8, 11, 16, 17, 18, 20)) {
                    escalaRP += valor.toInt()
                    continue
                }
                if (index in listOf(4, 9, 10, 14, 21)) {
                    escalaDP += valor.toInt()
                }
            }
            val values = listOf(
                id, nombreUsuario, escalaCE.toString(), escalaDP.toString(), escalaRP.toString(), fecha, *itemList.toTypedArray(), idioma, tiempo
            )
            return keys.zip(values).toMap()
        }

        fun calculateABQ(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "id_ABQ", "Nombre_Usuario", "AFE", "RSL", "DPD", "AFE_T", "RSL_T", "DPD_T", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
                "n10", "n11", "n12", "n13", "n14", "n15",
                "Fecha", "Idioma", "Tiempo"
            )
            val id = CuestionarioService().obtenerIdDisponible("ABQ", "Id_ABQ")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            var AFE = 0
            var RSL = 0
            var DPD = 0
            var AFE_T = 0
            var RSL_T = 0
            var DPD_T = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                var valor = when (respuesta) {
                    "Casi nunca" -> "1"
                    "Pocas veces" -> "2"
                    "Algunas veces" -> "3"
                    "A menudo" -> "4"
                    "Casi siempre" -> "5"
                    else -> "0"
                }
                //En la DB se añade el valor directamente
                //Para calcular el RSL se hace el inverso al 1 y 14
                itemList.add(valor)
                if (index in listOf(0, 4, 6, 12, 13)) {
                    if (index in listOf(0, 13)){
                        valor = when (valor) {
                            "5" -> "1"
                            "4" -> "2"
                            "3" -> "3"
                            "2" -> "4"
                            "1" -> "5"
                            else -> "3"
                        }
                    }
                    RSL += valor.toInt()
                    continue
                }
                if (index in listOf(1, 3, 7, 9, 11)) {
                    AFE += valor.toInt()
                    continue
                }
                if (index in listOf(2, 5, 8, 10, 14)) {
                    DPD += valor.toInt()
                }
            }
            when (DPD) {
                5 -> DPD_T = 40
                6 -> DPD_T = 43
                7 -> DPD_T = 45
                8 -> DPD_T = 48
                9 -> DPD_T = 50
                10 -> DPD_T = 53
                11 -> DPD_T = 55
                12 -> DPD_T = 57
                13 -> DPD_T = 60
                14 -> DPD_T = 62
                15 -> DPD_T = 64
                16 -> DPD_T = 67
                17 -> DPD_T = 69
                18 -> DPD_T = 72
                19 -> DPD_T = 74
                20 -> DPD_T = 76
                21 -> DPD_T = 79
                22 -> DPD_T = 81
                23 -> DPD_T = 84
                24 -> DPD_T = 86
                25 -> DPD_T = 88
                else -> DPD_T = 80
            }
            when (RSL) {
                5 -> RSL_T = 26
                6 -> RSL_T = 29
                7 -> RSL_T = 32
                8 -> RSL_T = 34
                9 -> RSL_T = 37
                10 -> RSL_T = 40
                11 -> RSL_T = 43
                12 -> RSL_T = 46
                13 -> RSL_T = 49
                14 -> RSL_T = 52
                15 -> RSL_T = 54
                16 -> RSL_T = 57
                17 -> RSL_T = 60
                18 -> RSL_T = 63
                19 -> RSL_T = 66
                20 -> RSL_T = 69
                21 -> RSL_T = 71
                22 -> RSL_T = 74
                23 -> RSL_T = 77
                24 -> RSL_T = 80
                25 -> RSL_T = 83
                else -> RSL_T = 80
            }
            when (AFE) {
                5 -> AFE_T = 35
                6 -> AFE_T = 41
                7 -> AFE_T = 44
                8 -> AFE_T = 47
                9 -> AFE_T = 50
                10 -> AFE_T = 53
                11 -> AFE_T = 55
                12 -> AFE_T = 58
                13 -> AFE_T = 61
                14 -> AFE_T = 64
                15 -> AFE_T = 67
                16 -> AFE_T = 69
                17 -> AFE_T = 72
                18 -> AFE_T = 75
                19 -> AFE_T = 78
                20 -> AFE_T = 81
                21 -> AFE_T = 83
                22 -> AFE_T = 86
                23 -> AFE_T = 89
                24 -> AFE_T = 92
                25 -> AFE_T = 95
                else -> AFE_T = 80
            }
            val values = listOf(
                id, nombreUsuario, AFE.toString(), RSL.toString(), DPD.toString(), AFE_T.toString(), RSL_T.toString(), DPD_T.toString(), *itemList.toTypedArray(), fecha, idioma, tiempo
            )
            return keys.zip(values).toMap()
        }

        fun calculatePreliminarABQ(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_DatosABQ", "Nombre_Usuario", "Años", "Antes_de", "Club", "categoria", "Nivel", "Numero", "Duracion",
                "Meses", "Fecha", "Idioma"
            )
            val id = CuestionarioService().obtenerIdDisponible("DatosABQ", "Id_DatosABQ")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()){
                if (index in listOf(1, 2, 3, 4)) { //Respuestas de texto
                    itemList.add(formattedString(respuesta))
                }else{
                    itemList.add(respuesta)
                }
            }
            val values = listOf(
                id, nombreUsuario, *itemList.toTypedArray(), fecha, idioma
            )
            return keys.zip(values).toMap()
        }
    }
}