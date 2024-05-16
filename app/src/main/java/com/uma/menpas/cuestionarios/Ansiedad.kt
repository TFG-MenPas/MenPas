package com.uma.menpas.cuestionarios

import android.content.Context
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class Ansiedad {
    companion object {
        fun calculateCSAI2(respuestasUsuario: ArrayList<String>, usuario: String, jsonResourceName: String): Map<String, String> {
            val keys = listOf(
                "ID_CSAI2", "Nombre_Usuario", "Cognitiva", "Somatica", "Autoconfianza", "Fecha", "Item1", "Item2", "Item3", "Item4",
                "Item5", "Item6", "Item7", "Item8", "Item9", "Item10", "Item11", "Item12", "Item13", "Item14",
                "Item15", "Item16", "Item17", "Item18", "Item19", "Item20", "Item21", "Item22", "Item23", "Item24",
                "Item25", "Item26", "Item27", "Idioma", "Tipo", "Tiempo"
            )

            val id = CuestionarioService().obtenerIdDisponible("csai2", "ID_CSAI2")
            val nombreUsuario = formattedString(usuario)
            var cognitiva = 0
            var somatica = 0
            var autoconfianza = 0
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tiempo = "100"
            var itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Siempre" -> "5"
                    "Casi siempre" -> "4"
                    "A veces" -> "3"
                    "Casi nunca" -> "2"
                    "Nunca" -> "1"
                    else -> "0"
                }
                if (jsonResourceName.equals("preguntas_csai2")) {
                    if (index % 3 == 0) {
                        cognitiva += valor.toInt()
                    } else if (index % 3 == 1) {
                        somatica += valor.toInt()
                    } else {
                        autoconfianza += valor.toInt()
                    }
                    itemList.add(valor)
                } else {
                    if (jsonResourceName.equals("preguntas_csai2_cognitiva")) {
                        itemList.add(valor)
                        itemList.add("0")
                        itemList.add("0")
                        cognitiva += valor.toInt()
                    } else if (jsonResourceName.equals("preguntas_csai2_somatica")) {
                        itemList.add("0")
                        itemList.add(valor)
                        itemList.add("0")
                        somatica += valor.toInt()
                    } else if (jsonResourceName.equals("preguntas_csai2_autoconfianza")) {
                        itemList.add("0")
                        itemList.add("0")
                        itemList.add(valor)
                        autoconfianza += valor.toInt()
                    }
                }
            }
            val tipo = when (jsonResourceName) {
                "preguntas_csai2_cognitiva" -> formattedString("Cognitiva")
                "preguntas_csai2_autoconfianza" -> formattedString("Autoconfianza")
                "preguntas_csai2_somatica" -> formattedString("Somática")
                else -> formattedString("Csai2")
            }
            val values = listOf(id, nombreUsuario, cognitiva.toString(), somatica.toString(), autoconfianza.toString(),
                fecha, *itemList.toTypedArray(), idioma, tipo, tiempo)

            return keys.zip(values).toMap()
        }

        fun calculateSCAT(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "ID_Scat", "Nombre_Usuario", "Resultado_Scat", "Fecha", "Item1",
                "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9",
                "Item10", "Item11", "Item12", "Item13", "Item14", "Item15", "Idioma", "Tiempo"
            )
            val id = CuestionarioService().obtenerIdDisponible("scat", "ID_Scat")
            val usuario = formattedString(usuario)
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
        fun calculateSTAI(respuestasUsuario: ArrayList<String>, usuario: String, isAE: Boolean, context: Context): Map<String, String> {
            val keys = listOf("ID_Stai", "Nombre_Usuario", "Stai_A_R", "Stai_A_E", "Centiles", "Fecha",
                "Item1","Item2","Item3","Item4","Item5","Item6","Item7","Item8","Item9","Item10","Item11",
                "Item12","Item13","Item14","Item15","Item16","Item17","Item18","Item19","Item20","CentilesA_E",
                "CentilesA_R", "Idioma", "Tipo", "Tiempo")
            val id = CuestionarioService().obtenerIdDisponible("stai", "ID_Stai")
            val nombreUsuario = formattedString(usuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")
            val tipo = if (isAE) {formattedString("Stai_A_E")} else {formattedString("Stai_A_R")}
            val tiempo = "100"
            var staiAR = 0
            var centilesStaiAR = "0"
            var staiAE = 0
            var centilesStaiAE = "0"
            val itemList = mutableListOf<String>()
            if (isAE) {
                for ((index, respuesta) in respuestasUsuario.withIndex()) {
                    var valor = "0"
                    if (index in listOf(0, 1, 4, 7, 9, 10, 14, 15, 18, 19)) {
                        valor = when (respuesta) {
                            "Nada" -> "3"
                            "Algo" -> "2"
                            "Bastante" -> "1"
                            "Mucho" -> "0"
                            else -> "0"
                        }
                    } else if (index in listOf(2, 3, 5, 6, 8, 11, 12, 13, 16, 17)) {
                        valor = when (respuesta) {
                            "Nada" -> "0"
                            "Algo" -> "1"
                            "Bastante" -> "2"
                            "Mucho" -> "3"
                            else -> "0"
                        }
                    }
                    staiAE += valor.toInt()
                    itemList.add(valor)
                }
            } else {
                for ((index, respuesta) in respuestasUsuario.withIndex()) {
                    var valor = "0"
                    if (index in listOf(0,5,6,9,12,15,18)) {
                        valor = when (respuesta) {
                            "Casi nunca" -> "3"
                            "A veces" -> "2"
                            "A menudo" -> "1"
                            "Casi siempre" -> "0"
                            else -> "0"
                        }
                    } else if (index in listOf(1,2,3,4,7,8,10,11,13,14,16,17,19)) {
                        valor = when (respuesta) {
                            "Casi nunca" -> "0"
                            "A veces" -> "1"
                            "A menudo" -> "2"
                            "Casi siempre" -> "3"
                            else -> "0"
                        }
                    }
                    staiAR += valor.toInt()
                    itemList.add(valor)
                }
            }
            val usuario = UsuarioController().getUsuario(context)
            if (isAE) {
                centilesStaiAE = calculoCentilesSTAI(usuario?.sexo ?: "Otro", (usuario?.edad ?: 0) >= 18, staiAE)
            } else {
                centilesStaiAR = calculoCentilesSTAI(usuario?.sexo ?: "Otro",
                    (usuario?.edad ?: 0) >= 18, staiAR)
            }
            val values = listOf(id, nombreUsuario, staiAR.toString(), staiAE.toString(),
                "50", fecha, *itemList.toTypedArray(), centilesStaiAE, centilesStaiAR,
                idioma, tipo, tiempo)
            return keys.zip(values).toMap()
        }

        fun calculoCentilesSTAI(sexo: String, mayorEdad: Boolean, valor: Int): String {
            val sol: String
            if (!mayorEdad) {
                if (sexo.equals("Masculino")) {
                    sol = when (valor) {
                        0 -> "0"
                        1 -> "1"
                        2 -> "1"
                        3 -> "2"
                        4 -> "8"
                        5 -> "9"
                        6 -> "9"
                        7 -> "9"
                        8 -> "9"
                        9 -> "9"
                        10 -> "10"
                        11 -> "10"
                        12 -> "10"
                        13 -> "10"
                        14 -> "10"
                        15 -> "11"
                        16 -> "11"
                        17 -> "11"
                        18 -> "11"
                        19 -> "11"
                        20 -> "12"
                        21 -> "12"
                        22 -> "12"
                        23 -> "12"
                        24 -> "12"
                        25 -> "13"
                        26 -> "13"
                        27 -> "13"
                        28 -> "13"
                        29 -> "13"
                        30 -> "14"
                        31 -> "15"
                        32 -> "15"
                        33 -> "15"
                        34 -> "15"
                        35 -> "16"
                        36 -> "16"
                        37 -> "16"
                        38 -> "16"
                        39 -> "16"
                        40 -> "17"
                        41 -> "18"
                        42 -> "18"
                        43 -> "18"
                        44 -> "18"
                        45 -> "19"
                        46 -> "19"
                        47 -> "19"
                        48 -> "19"
                        49 -> "19"
                        50 -> "20"
                        51 -> "21"
                        52 -> "21"
                        53 -> "21"
                        54 -> "21"
                        55 -> "22"
                        56 -> "23"
                        57 -> "23"
                        58 -> "23"
                        59 -> "23"
                        60 -> "24"
                        61 -> "25"
                        62 -> "25"
                        63 -> "25"
                        64 -> "25"
                        65 -> "26"
                        66 -> "27"
                        67 -> "27"
                        68 -> "27"
                        69 -> "27"
                        70 -> "28"
                        71 -> "29"
                        72 -> "30"
                        73 -> "30"
                        74 -> "30"
                        75 -> "31"
                        76 -> "31"
                        77 -> "32"
                        78 -> "33"
                        79 -> "33"
                        80 -> "34"
                        81 -> "35"
                        82 -> "35"
                        83 -> "35"
                        84 -> "35"
                        85 -> "36"
                        86 -> "36"
                        87 -> "36"
                        88 -> "36"
                        89 -> "37"
                        90 -> "38"
                        91 -> "41"
                        92 -> "41"
                        93 -> "41"
                        94 -> "41"
                        95 -> "43"
                        96 -> "44"
                        97 -> "45"
                        98 -> "46"
                        99 -> "47"
                        else -> "80"
                    }
                } else {
                    sol =  when (valor) {
                        0 -> "0"
                        1 -> "1"
                        2 -> "2"
                        3 -> "3"
                        4 -> "7"
                        5 -> "8"
                        6 -> "9"
                        7 -> "9"
                        8 -> "9"
                        9 -> "9"
                        10 -> "11"
                        11 -> "12"
                        12 -> "12"
                        13 -> "12"
                        14 -> "12"
                        15 -> "13"
                        16 -> "13"
                        17 -> "13"
                        18 -> "13"
                        19 -> "13"
                        20 -> "14"
                        21 -> "14"
                        22 -> "14"
                        23 -> "15"
                        24 -> "15"
                        25 -> "16"
                        26 -> "16"
                        27 -> "16"
                        28 -> "16"
                        29 -> "16"
                        30 -> "17"
                        31 -> "17"
                        32 -> "17"
                        33 -> "17"
                        34 -> "17"
                        35 -> "18"
                        36 -> "18"
                        37 -> "18"
                        38 -> "18"
                        39 -> "18"
                        40 -> "19"
                        41 -> "19"
                        42 -> "19"
                        43 -> "19"
                        44 -> "19"
                        45 -> "20"
                        46 -> "21"
                        47 -> "21"
                        48 -> "21"
                        49 -> "21"
                        50 -> "22"
                        51 -> "22"
                        52 -> "22"
                        53 -> "22"
                        54 -> "22"
                        55 -> "23"
                        56 -> "24"
                        57 -> "24"
                        58 -> "24"
                        59 -> "24"
                        60 -> "25"
                        61 -> "25"
                        62 -> "25"
                        63 -> "25"
                        64 -> "25"
                        65 -> "26"
                        66 -> "27"
                        67 -> "27"
                        68 -> "27"
                        69 -> "27"
                        70 -> "28"
                        71 -> "29"
                        72 -> "30"
                        73 -> "30"
                        74 -> "30"
                        75 -> "31"
                        76 -> "31"
                        77 -> "33"
                        78 -> "33"
                        79 -> "33"
                        80 -> "34"
                        81 -> "35"
                        82 -> "35"
                        83 -> "35"
                        84 -> "35"
                        85 -> "36"
                        86 -> "36"
                        87 -> "36"
                        88 -> "36"
                        89 -> "38"
                        90 -> "39"
                        91 -> "40"
                        92 -> "40"
                        93 -> "40"
                        94 -> "40"
                        95 -> "41"
                        96 -> "42"
                        97 -> "44"
                        98 -> "49"
                        99 -> "53"
                        else -> "80"
                    }
                }
            } else {
                if (sexo.equals("Masculino")) {
                    sol = when (valor) {
                        0 -> "0"
                        1 -> "1"
                        2 -> "1"
                        3 -> "2"
                        4 -> "5"
                        5 -> "6"
                        6 -> "7"
                        7 -> "7"
                        8 -> "7"
                        9 -> "7"
                        10 -> "8"
                        11 -> "9"
                        12 -> "9"
                        13 -> "9"
                        14 -> "9"
                        15 -> "10"
                        16 -> "11"
                        17 -> "11"
                        18 -> "11"
                        19 -> "11"
                        20 -> "12"
                        21 -> "12"
                        22 -> "12"
                        23 -> "13"
                        24 -> "13"
                        25 -> "14"
                        26 -> "14"
                        27 -> "14"
                        28 -> "14"
                        29 -> "14"
                        30 -> "14"
                        31 -> "14"
                        32 -> "14"
                        33 -> "14"
                        34 -> "14"
                        35 -> "15"
                        36 -> "15"
                        37 -> "15"
                        38 -> "15"
                        39 -> "15"
                        40 -> "16"
                        41 -> "17"
                        42 -> "17"
                        43 -> "17"
                        44 -> "17"
                        45 -> "18"
                        46 -> "18"
                        47 -> "18"
                        48 -> "18"
                        49 -> "18"
                        50 -> "19"
                        51 -> "19"
                        52 -> "19"
                        53 -> "19"
                        54 -> "19"
                        55 -> "20"
                        56 -> "21"
                        57 -> "21"
                        58 -> "21"
                        59 -> "21"
                        60 -> "21"
                        61 -> "22"
                        62 -> "22"
                        63 -> "22"
                        64 -> "22"
                        65 -> "23"
                        66 -> "24"
                        67 -> "24"
                        68 -> "24"
                        69 -> "24"
                        70 -> "25"
                        71 -> "26"
                        72 -> "27"
                        73 -> "27"
                        74 -> "27"
                        75 -> "28"
                        76 -> "28"
                        77 -> "29"
                        78 -> "29"
                        79 -> "29"
                        80 -> "30"
                        81 -> "31"
                        82 -> "31"
                        83 -> "31"
                        84 -> "31"
                        85 -> "33"
                        86 -> "34"
                        87 -> "35"
                        88 -> "35"
                        89 -> "36"
                        90 -> "37"
                        91 -> "38"
                        92 -> "39"
                        93 -> "39"
                        94 -> "39"
                        95 -> "40"
                        96 -> "42"
                        97 -> "43"
                        98 -> "46"
                        99 -> "47"
                        else -> "80"
                    }
                } else {
                    sol = when (valor) {
                        0 -> "0"
                        1 -> "1"
                        2 -> "1"
                        3 -> "2"
                        4 -> "6"
                        5 -> "7"
                        6 -> "8"
                        7 -> "8"
                        8 -> "9"
                        9 -> "9"
                        10 -> "10"
                        11 -> "11"
                        12 -> "11"
                        13 -> "11"
                        14 -> "11"
                        15 -> "12"
                        16 -> "12"
                        17 -> "12"
                        18 -> "12"
                        19 -> "12"
                        20 -> "13"
                        21 -> "13"
                        22 -> "13"
                        23 -> "14"
                        24 -> "14"
                        25 -> "15"
                        26 -> "15"
                        27 -> "15"
                        28 -> "15"
                        29 -> "15"
                        30 -> "16"
                        31 -> "16"
                        32 -> "16"
                        33 -> "16"
                        34 -> "16"
                        35 -> "17"
                        36 -> "17"
                        37 -> "17"
                        38 -> "17"
                        39 -> "17"
                        40 -> "18"
                        41 -> "18"
                        42 -> "18"
                        43 -> "18"
                        44 -> "18"
                        45 -> "19"
                        46 -> "20"
                        47 -> "20"
                        48 -> "20"
                        49 -> "20"
                        50 -> "21"
                        51 -> "22"
                        52 -> "22"
                        53 -> "22"
                        54 -> "22"
                        55 -> "23"
                        56 -> "23"
                        57 -> "23"
                        58 -> "23"
                        59 -> "23"
                        60 -> "24"
                        61 -> "25"
                        62 -> "25"
                        63 -> "25"
                        64 -> "25"
                        65 -> "26"
                        66 -> "27"
                        67 -> "27"
                        68 -> "27"
                        69 -> "27"
                        70 -> "29"
                        71 -> "30"
                        72 -> "30"
                        73 -> "30"
                        74 -> "30"
                        75 -> "31"
                        76 -> "31"
                        77 -> "32"
                        78 -> "33"
                        79 -> "33"
                        80 -> "34"
                        81 -> "35"
                        82 -> "35"
                        83 -> "35"
                        84 -> "36"
                        85 -> "37"
                        86 -> "38"
                        87 -> "38"
                        88 -> "38"
                        89 -> "40"
                        90 -> "41"
                        91 -> "44"
                        92 -> "44"
                        93 -> "44"
                        94 -> "44"
                        95 -> "47"
                        96 -> "48"
                        97 -> "49"
                        98 -> "52"
                        99 -> "54"
                        else -> "80"
                    }
                }
            }
            return sol
        }
    }
}