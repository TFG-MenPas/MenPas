package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class BusquedaDeTalentos {
    companion object {
        fun calculateACSI28(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("Id_ACSI_28", "Nombre_Usuario", "Rendimiento", "Ausencia", "Confrontacion",
                "Concentracion", "Formulacion", "Confianza", "Capacidad", "Tiempo", "Idioma", "Fecha",
                "n1","n2","n3","n4","n5","n6","n7","n8","n9","n10","n11","n12","n13","n14","n15","n16","n17","n18",
                "n19","n20","n21","n22","n23","n24","n25","n26","n27","n28")
            val id = CuestionarioService().obtenerIdDisponible("ACSI_28", "Id_ACSI_28")
            val nombreUsuario = formattedString(usuario)
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
            val values = listOf(id, nombreUsuario, *valoresString.toTypedArray(), tiempo, idioma, fecha, *itemList.toTypedArray() )
            return keys.zip(values).toMap()
        }

        fun calculateEMBU(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("Id_EMBU", "Nombre_Usuario", "SoporteP", "SoporteM", "RechazoP",
                "RechazoM", "SobreproteccionP", "SobreproteccionM", "Tiempo", "Idioma", "Fecha", "n1",
                "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "n10", "n11", "n12", "n13", "n14",
                "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22", "n23", "n24", "n25", "n26",
                "n27", "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35", "n36", "n37", "n38",
                "n39", "n40", "n41", "n42")
            val id = CuestionarioService().obtenerIdDisponible("EMBU", "Id_EMBU")
            val nombreUsuario = formattedString(usuario)
            var soporteP = 0
            var soporteM = 0
            var rechazoP = 0
            var rechazoM = 0
            var sobreproteccionP = 0
            var sobreproteccionM = 0
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val fecha = formattedString(obtenerFechaActual())
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Nunca" -> "1"
                    "A veces" -> "2"
                    "A menudo" -> "3"
                    "Siempre" -> "4"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(0, 8, 14, 20, 26, 30, 36)) {
                    soporteP += valor.toInt()
                } else if (index in listOf(1, 9, 15, 21, 27, 31, 37)) {
                    soporteM += valor.toInt()
                } else if (index in listOf(4, 10, 16, 22, 28, 34, 38)) {
                    rechazoP += valor.toInt()
                } else if (index in listOf(5, 11, 17, 23, 29, 35, 39)) {
                    rechazoM += valor.toInt()
                } else if (index in listOf(2, 6, 12, 18, 24, 32, 40)) {
                    sobreproteccionP += valor.toInt()
                } else if (index in listOf(3, 7, 13, 19, 25, 33, 41)) {
                    sobreproteccionM += valor.toInt()
                }
            }
            val values = listOf(id, nombreUsuario, soporteP.toString(), soporteM.toString(),
                rechazoP.toString(), rechazoM.toString(), sobreproteccionP.toString(),
                sobreproteccionM.toString(), tiempo, idioma, fecha, *itemList.toTypedArray())
            return keys.zip(values).toMap()
        }

        fun calculateEACS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_EACS", "Nombre_Usuario", "Factor1_Marginado", "Factor2_Aislamiento", "Factor3_Disciplina",
                "Tiempo", "Idioma", "Fecha", "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "n10",
                "n11", "n12")
            val id = CuestionarioService().obtenerIdDisponible("EACS", "Id_EACS")
            val nombreUsuario = formattedString(usuario)
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val fecha = formattedString(obtenerFechaActual())
            var factor1 = 0
            var factor2 = 0
            var factor3 = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Completamente en desacuerdo" -> "1"
                    "En desacuerdo" -> "2"
                    "Neutro" -> "3"
                    "De acuerdo" -> "4"
                    "Completamente de acuerdo" -> "5"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(3,4,5,6)) {
                    factor1 += valor.toInt()
                } else if (index in listOf(0,1,2,8)) {
                    factor2 += valor.toInt()
                } else {
                    factor3 += valor.toInt()
                }
            }
            val values = listOf(id, nombreUsuario, factor1.toString(), factor2.toString(),
                factor3.toString(), tiempo, idioma, fecha, *itemList.toTypedArray(), "0", "0")
            return keys.zip(values).toMap()
        }

        fun calculateIPSETA(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf(
                "Id_IPSETA", "Nombre_Usuario", "M_Intrinseca", "M_Logro", "Auto",
                "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "n10",
                "n11", "n12", "n13", "n14", "n15", "n16", "n17", "n18", "n19", "Tiempo", "Idioma",
                "Fecha")
            val id = CuestionarioService().obtenerIdDisponible("IPSETA", "Id_IPSETA")
            val nombreUsuario = formattedString(usuario)
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val fecha = formattedString(obtenerFechaActual())
            var intrinseca = 0
            var logro = 0
            var auto = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Casi nunca" -> "1"
                    "A veces" -> "2"
                    "Casi siempre" -> "3"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(1,4,7,10,13,16)) {
                    intrinseca += valor.toInt()
                } else if (index in listOf(2,5,8,11,14,17)) {
                    logro += valor.toInt()
                } else {
                    auto += valor.toInt()
                }
            }
            val values = listOf(id, nombreUsuario, intrinseca.toString(), logro.toString(),
                auto.toString(), *itemList.toTypedArray(), tiempo, idioma, fecha)
            return keys.zip(values).toMap()
        }

        fun calculateMPS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("Id_MPS", "Nombre_Usuario", "Preocupacion", "Normas", "Expectativas",
                "Criticas", "Dudas", "Organizacion", "Tiempo", "Idioma", "Fecha", "n1", "n2", "n3", "n4",
                "n5", "n6", "n7", "n8", "n9", "n10", "n11", "n12", "n13", "n14", "n15", "n16", "n17",
                "n18", "n19", "n20", "n21", "n22", "n23", "n24", "n25", "n26", "n27", "n28",
                "n29", "n30", "n31", "n32", "n33", "n34", "n35")
            val id = CuestionarioService().obtenerIdDisponible("MPS", "Id_MPS")
            val nombreUsuario = formattedString(usuario)
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val fecha = formattedString(obtenerFechaActual())
            var preocupaciones = 0
            var normas = 0
            var expectativas = 0
            var criticas = 0
            var dudas = 0
            var organizacion = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Completamente en desacuerdo" -> "1"
                    "En desacuerdo" -> "2"
                    "Neutro" -> "3"
                    "De acuerdo" -> "4"
                    "Completamente de acuerdo" -> "5"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(8,9,13,20,22,24,33,12,17)) {
                    preocupaciones += valor.toInt()
                } else if (index in listOf(3,5,11,15,18,23,29)) {
                    normas += valor.toInt()
                } else if (index in listOf(0,10,14,19,15)) {
                    expectativas += valor.toInt()
                } else if (index in listOf(2,4,21,34)) {
                    criticas += valor.toInt()
                } else if (index in listOf(16,27,31,32)) {
                    dudas += valor.toInt()
                } else if (index in listOf(1,6,7,26,28,30)) {
                    organizacion += valor.toInt()
                }
            }
            val values = listOf(id, nombreUsuario, preocupaciones.toString(), normas.toString(),
                expectativas.toString(),criticas.toString(),dudas.toString(),organizacion.toString(),
                tiempo, idioma, fecha, *itemList.toTypedArray())
            return keys.zip(values).toMap()
        }

        fun calculateRS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
            val keys = listOf("Id_RS", "Nombre_Usuario", "Factor1", "Factor2","Tiempo",
                "Idioma", "Fecha", "n1", "n2", "n3", "n4",
                "n5", "n6", "n7", "n8", "n9", "n10", "n11", "n12", "n13", "n14", "n15", "n16", "n17",
                "n18", "n19", "n20", "n21", "n22", "n23", "n24", "n25")
            val id = CuestionarioService().obtenerIdDisponible("RS", "Id_RS")
            val nombreUsuario = formattedString(usuario)
            val tiempo = "100"
            val idioma = formattedString("es-es")
            val fecha = formattedString(obtenerFechaActual())
            var factor1 = 0
            var factor2 = 0
            val itemList = mutableListOf<String>()
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) {
                    "Completamente en desacuerdo" -> "1"
                    "Bastante en desacuerdo" -> "2"
                    "Algo en desacuerdo" -> "3"
                    "Neutro" -> "4"
                    "Algo de acuerdo" -> "5"
                    "Bastante en acuerdo" -> "6"
                    "Completamente de acuerdo" -> "7"
                    else -> "0"
                }
                itemList.add(valor)
                if (index in listOf(0,1,3,5,7,8,9,13,14,17,18)) {
                    factor1 += valor.toInt()
                } else {
                    factor2 += valor.toInt()
                }
            }
            val values = listOf(id, nombreUsuario, factor1.toString(), factor2.toString(),
                tiempo, idioma, fecha, *itemList.toTypedArray())
            return keys.zip(values).toMap()
        }
    }
}