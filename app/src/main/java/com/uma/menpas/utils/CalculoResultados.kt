package com.uma.menpas.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Dictionary

class CalculoResultados {

    fun calculate(jsonResourceName: String, respuestasUsuario: ArrayList<String>): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_csai2" -> calculateCSAI2(respuestasUsuario)
            "preguntas_scat" -> calculateSCAT(respuestasUsuario)
            "preguntas_maslach" -> calculateMaslach(respuestasUsuario)
            "preguntas_abq" -> calculateABQ(respuestasUsuario)
            "preguntas_preliminar_abq" -> calculatePreliminarABQ(respuestasUsuario)
            "preguntas_af5" -> calculateAF5(respuestasUsuario)
            "preguntas_bsq" -> calculateBSQ(respuestasUsuario)
            "preguntas_caf" -> calculateCAF(respuestasUsuario)
            else -> calculateCSAI2(respuestasUsuario)
        }
    }

    private fun calculateCAF(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_CAF", "Nombre_Usuario", "Habilidad", "Condicion", "Atractivo", "Fuerza", "AutoconceptoF", "AutoconceptoG", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14",
            "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22",
            "n23", "n24", "n25", "n26", "n27",
            "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35",
            "n36"
        )
        val id = "2389"
        val usuario = formattedString("nestorgc")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        var Habilidad = 0
        var Condicion = 0
        var Atractivo = 0
        var Fuerza = 0
        var AutoconceptoF = 0
        var AutoconceptoG = 0
        val itemList = mutableListOf<String>()
        for ((index,respuesta) in respuestasUsuario.withIndex()) {
            var valor = when (respuesta) {
                "Falso" -> "1"
                "Casi siempre falso" -> "2"
                "A veces verdadero/falso" -> "3"
                "Casi siempre verdadero" -> "4"
                "Verdadero" -> "5"
                else -> "0"
            }
            if (index in listOf(4, 5, 6, 7, 8, 14, 15, 21, 22, 23, 24, 25, 31, 32, 34, 35)){
                valor = when (valor) {
                    "5" -> "1"
                    "4" -> "2"
                    "3" -> "3"
                    "2" -> "4"
                    "1" -> "5"
                    else -> "3"
                }
            }
            itemList.add(valor)
            if (index in listOf(0, 5, 16, 22, 27, 32)){
                Habilidad += valor.toInt()
                continue
            }
            if (index in listOf(1, 6, 10, 17, 23, 28)){
                Condicion += valor.toInt()
                continue
            }
            if (index in listOf(7, 11, 18, 24, 29, 33)){
                Atractivo += valor.toInt()
                continue
            }
            if (index in listOf(2, 8, 12, 19, 30, 34)){
                Fuerza += valor.toInt()
                continue
            }
            if (index in listOf(3, 13, 15, 20, 25, 35)){
                AutoconceptoF += valor.toInt()
                continue
            }
            if (index in listOf(4, 9, 14, 21, 26, 31)){
                AutoconceptoG += valor.toInt()
            }
        }
        val values = listOf(
            id, usuario, Habilidad.toString(), Condicion.toString(), Atractivo.toString(), Fuerza.toString(), AutoconceptoF.toString(), AutoconceptoG.toString(),tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateBSQ(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_BSQ", "Nombre_Usuario", "Factor1", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14"
        )
        val id = "1913"
        val usuario = formattedString("nestorgc")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        var Factor1 = 0
        val itemList = mutableListOf<String>()
        for (respuesta in respuestasUsuario) {
            val valor = when (respuesta) {
                "Nunca" -> "1"
                "Raramente" -> "2"
                "Algunas veces" -> "3"
                "A menudo" -> "4"
                "Muy a menudo" -> "5"
                "Siempre" -> "6"
                else -> "0"
            }
            itemList.add(valor)
            Factor1 += valor.toInt()
        }
        val values = listOf(
            id, usuario, Factor1.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateAF5(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_AF5", "Nombre_Usuario", "Académico", "Social", "Emocional", "Familiar", "Físico", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14", "n15", "n16",
            "n17", "n18", "n19", "n20", "n21", "n22", "n23", "n24",
            "n25", "n26", "n27", "n28", "n29", "n30"
        )
        val id = "2970"
        val usuario = formattedString("nestorgc")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        var Academico = 0
        var Social = 0
        var Emocional = 0
        var Familiar = 0
        var Fisico = 0
        val itemList = mutableListOf<String>()
        for ((index, respuesta) in respuestasUsuario.withIndex()) {
            itemList.add(respuesta)
            if (index in listOf(0, 5, 10, 15, 20, 25)){
                Academico += respuesta.toInt()
                continue
            }
            if (index in listOf(1, 6, 11, 16, 21, 26)) {
                Social += respuesta.toInt()
                continue
            }
            if (index in listOf(2, 7, 12, 17, 22, 27)) {
                Emocional += respuesta.toInt()
                continue
            }
            if (index in listOf(3, 8, 13, 18, 23, 28)) {
                Familiar += respuesta.toInt()
                continue
            }
            if (index in listOf(4, 9, 14, 19, 24, 29)) {
                Fisico += respuesta.toInt()
            }
        }
        val values = listOf(
            id, usuario, Academico.toString(), Social.toString(), Emocional.toString(), Familiar.toString(), Fisico.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculatePreliminarABQ(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_DatosABQ", "Nombre_Usuario", "Años", "Antes_de", "Club", "categoria", "Nivel", "Numero", "Duracion",
            "Meses", "Fecha", "Idioma"
        )
        val id = "875"
        val usuario = formattedString("nestorgc")
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
            id, usuario, *itemList.toTypedArray(), fecha, idioma
        )
        return keys.zip(values).toMap()
    }

    private fun calculateABQ(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "id_ABQ", "Nombre_Usuario", "AFE", "RSL", "DPD", "AFE_T", "RSL_T", "DPD_T", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14", "n15",
            "Fecha", "Idioma", "Tiempo"
        )
        val id = "1331"
        val usuario = formattedString("nestorgc")
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
        val values = listOf(
            id, usuario, AFE.toString(), RSL.toString(), DPD.toString(), AFE_T.toString(), RSL_T.toString(), DPD_T.toString(), *itemList.toTypedArray(), fecha, idioma, tiempo
        )
        return keys.zip(values).toMap()
    }

    private fun calculateMaslach(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "ID_Burnout", "Nombre_Usuario", "Escala_CE", "Escala_DP", "Escala_RP", "Fecha", "Item1",
            "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9",
            "Item10", "Item11", "Item12", "Item13", "Item14", "Item15", "Item16",
            "Item17", "Item18", "Item19", "Item20", "Item21", "Item22",
            "Idioma", "Tiempo"
        )
        val id = "4062"
        val usuario = formattedString("nestorgc")
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
            id, usuario, escalaCE.toString(), escalaDP.toString(), escalaRP.toString(), fecha, *itemList.toTypedArray(), idioma, tiempo
        )
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
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss")
        return currentDateTime.format(formatter)
    }

    private fun formattedString(s: String): String {
        return "'$s'"
    }
}