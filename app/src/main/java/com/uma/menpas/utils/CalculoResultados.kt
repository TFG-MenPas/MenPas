package com.uma.menpas.utils

import android.widget.CalendarView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Calendar
import java.util.Date
import kotlin.math.round

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
            "preguntas_sf_36" -> calculateSF36(respuestasUsuario)
            "preguntas_sf_12" -> calculateSF12(respuestasUsuario)
            "preguntas_vitalidad_subjetiva" -> calculateVS(respuestasUsuario)
            "preguntas_autorregistro_comida" -> calculateAutoComida(respuestasUsuario)
            else -> calculateCSAI2(respuestasUsuario)
        }
    }

    private fun calculateAutoComida(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_Autorregistro", "Nombre_Usuario", "Dia", "Hora", "Tiempo", "Cantidad", "Actividad", "LugarComida", "Comida_tipo", "Calorias", "Categoria", "Idioma", "Fecha", "Tipo"
        )
        val id = "13125"
        val tipo = formattedString("A Comida")
        val usuario = formattedString("pruebamenpas")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val itemList = mutableListOf<String>()
        for ((index,respuesta) in respuestasUsuario.withIndex()) {
            if(index == 0){
                //Fecha
                val c = Calendar.getInstance()
                c.timeInMillis = respuesta.toLong()
                val currentDateTime = LocalDateTime.of( c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR), c.get(Calendar.MINUTE))
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
            id, usuario, *itemList.toTypedArray(), idioma, fecha, tipo
        )
        return keys.zip(values).toMap()
    }

    private fun calculateVS(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_Vitalidad", "Nombre_Usuario", "sol", "n1", "n2", "n3", "n4", "n5", "n6", "Tiempo", "Idioma", "Fecha"
        )
        val id = "407"
        val usuario = formattedString("pruebamenpas")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        val valores = mutableListOf<Int>()
        val itemList = mutableListOf<String>()
        for (respuesta in respuestasUsuario) {
            val valor = when (respuesta) {
                "Totalmente en desacuerdo" -> "1"
                "Muy en desacuerdo" -> "2"
                "Algo en desacuerdo" -> "3"
                "Neutral" -> "4"
                "Algo de acuerdo" -> "5"
                "Muy de acuerdo" -> "6"
                "Totalmente de acuerdo" -> "7"
                else -> "0"
            }
            itemList.add(valor)
            valores.add(valor.toInt())
        }
        val values = listOf(
            id, usuario, valores.average().toInt().toString(), *itemList.toTypedArray(), tiempo, idioma, fecha,
        )
        return keys.zip(values).toMap()
    }

    private fun calculateSF12(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_SF12", "Nombre_Usuario", "Medida_S_Fisica", "Medida_S_Mental", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12"
        )
        val id = "1917"
        val usuario = formattedString("pruebamenpas")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        val Medida_S_Fisica = mutableListOf<Int>()
        val Medida_S_Mental = mutableListOf<Int>()
        val itemList = mutableListOf<String>()
        for ((index,respuesta) in respuestasUsuario.withIndex()) {
            if (index == 0) {
                val valor = when (respuesta) {
                    "Excelente" -> "100"
                    "Muy Buena" -> "75"
                    "Buena" -> "50"
                    "Regular" -> "25"
                    "Mala" -> "0"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Fisica.add(valor.toInt())
                continue
            }
            if (index in listOf(1, 2)) {
                val valor = when (respuesta) {
                    "Sí, me limita mucho" -> "0"
                    "Sí, me limita un poco" -> "50"
                    "No, no me limita nada" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Fisica.add(valor.toInt())
                continue
            }
            if (index in listOf(3, 4)) {
                val valor = when (respuesta) {
                    "si" -> "0"
                    "no" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Fisica.add(valor.toInt())
                continue
            }
            if (index == 7) {
                val valor = when (respuesta) {
                    "Nada" -> "100"
                    "Un poco" -> "75"
                    "Regular" -> "50"
                    "Bastante" -> "25"
                    "Mucho" -> "0"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Fisica.add(valor.toInt())
                continue
            }
            if (index in listOf(5, 6)) {
                val valor = when (respuesta) {
                    "si" -> "0"
                    "no" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Mental.add(valor.toInt())
                continue
            }
            if (index in listOf(8, 9, 10)) {
                val valor = when (respuesta) {
                    "Siempre" -> "100"
                    "Casi siempre" -> "80"
                    "Muchas veces" -> "60"
                    "Algunas veces" -> "40"
                    "Solo una vez" -> "20"
                    "Nunca" -> "0"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Mental.add(valor.toInt())
                continue
            }
            if (index == 11) {
                val valor = when (respuesta) {
                    "Siempre" -> "0"
                    "Casi siempre" -> "25"
                    "Algunas veces" -> "50"
                    "Solo una vez" -> "75"
                    "Nunca" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                Medida_S_Mental.add(valor.toInt())
            }
        }
        val values = listOf(
            id, usuario, Medida_S_Fisica.average().toInt().toString(), Medida_S_Mental.average().toInt().toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateSF36(respuestasUsuario: ArrayList<String>): Map<String, String> {
        val keys = listOf(
            "Id_SF36", "Nombre_Usuario", "F_Fisica", "Rol_Fisico", "Dolor", "Salud", "Vitalidad", "FuncionS", "Rol_Emocional", "Salud_Mental", "Cambio_Salud", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14",
            "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22",
            "n23", "n24", "n25", "n26", "n27",
            "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35",
            "n36"
        )
        val id = "1663"
        val usuario = formattedString("pruebamenpas")
        val fecha = formattedString(obtenerFechaActual())
        val idioma = formattedString("es-es")
        val tiempo = "100"
        val F_Fisica = mutableListOf<Int>()
        val Rol_Fisico = mutableListOf<Int>()
        val Dolor = mutableListOf<Int>()
        val Salud = mutableListOf<Int>()
        val Vitalidad = mutableListOf<Int>()
        val FuncionS = mutableListOf<Int>()
        val Rol_Emocional = mutableListOf<Int>()
        val Salud_Mental = mutableListOf<Int>()
        var Cambio_Salud = 0
        val itemList = mutableListOf<String>()
        for ((index,respuesta) in respuestasUsuario.withIndex()) {
            if (index in listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)) {
                val valor = when (respuesta) {
                    "Sí, me limita mucho" -> "0"
                    "Sí, me limita un poco" -> "50"
                    "No, no me limita nada" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                F_Fisica.add(valor.toInt())
                continue
            }
            if (index in listOf(12, 13, 14, 15)) {
                val valor = when (respuesta) {
                    "si" -> "0"
                    "no" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                Rol_Fisico.add(valor.toInt())
                continue
            }
            if (index in listOf(20, 21)) {
                var valor = ""
                if (index == 20){
                    valor = when (respuesta) {
                        "No, ninguno" -> "100"
                        "Sí, muy poco" -> "80"
                        "Sí, un poco" -> "60"
                        "Sí, moderado" -> "40"
                        "Sí, mucho" -> "20"
                        "Sí, muchísimo" -> "0"
                        else -> "0"
                    }
                }else{
                    valor = when (respuesta) {
                        "Nada" -> "100"
                        "Un poco" -> "75"
                        "Regular" -> "50"
                        "Bastante" -> "25"
                        "Mucho" -> "0"
                        else -> "0"
                    }
                }
                itemList.add(valor)
                Dolor.add(valor.toInt())
                continue
            }
            if (index in listOf(0, 32, 33, 34, 35)) {
                var valor = ""
                if (index in listOf(0, 33, 35)){
                    valor = when (respuesta) {
                        "Excelente" -> "100"
                        "Muy buena" -> "75"
                        "Buena" -> "50"
                        "Regular" -> "25"
                        "Mala" -> "0"
                        else -> {
                            when (respuesta){
                                "Totalmente cierta" -> "100"
                                "Bastante cierta" -> "75"
                                "No lo sé" -> "50"
                                "Bastante falsa" -> "25"
                                "Totalmente falsa" -> "0"
                                else -> "0"
                            }
                        }
                    }
                }
                if (index in listOf(32, 34)){
                    valor = when (respuesta){
                        "Totalmente cierta" -> "0"
                        "Bastante cierta" -> "25"
                        "No lo sé" -> "50"
                        "Bastante falsa" -> "75"
                        "Totalmente falsa" -> "100"
                        else -> "0"
                    }
                }
                itemList.add(valor)
                Salud.add(valor.toInt())
                continue
            }
            if (index in listOf(22, 26, 28, 30)) {
                var valor = ""
                if (index in listOf(22, 26)){
                    valor = when (respuesta){
                        "Siempre" -> "100"
                        "Casi siempre" -> "80"
                        "Muchas veces" -> "60"
                        "Algunas veces" -> "40"
                        "Sólo alguna vez" -> "20"
                        "Nunca" -> "0"
                        else -> "0"
                    }
                }
                if (index in listOf(28, 30)){
                    valor = when (respuesta){
                        "Siempre" -> "0"
                        "Casi siempre" -> "20"
                        "Muchas veces" -> "40"
                        "Algunas veces" -> "60"
                        "Sólo alguna vez" -> "80"
                        "Nunca" -> "100"
                        else -> "0"
                    }
                }
                itemList.add(valor)
                Vitalidad.add(valor.toInt())
                continue
            }
            if (index in listOf(19, 31)) {
                var valor = ""
                if (index == 19){
                    valor = when (respuesta) {
                        "Nada" -> "100"
                        "Un poco" -> "75"
                        "Regular" -> "50"
                        "Bastante" -> "25"
                        "Mucho" -> "0"
                        else -> "0"
                    }
                } else {
                    valor = when (respuesta){
                        "Siempre" -> "0"
                        "Casi siempre" -> "20"
                        "Muchas veces" -> "40"
                        "Algunas veces" -> "60"
                        "Sólo alguna vez" -> "80"
                        "Nunca" -> "100"
                        else -> "0"
                    }
                }
                itemList.add(valor)
                FuncionS.add(valor.toInt())
                continue
            }
            if (index in listOf(16, 17, 18)) {
                val valor = when (respuesta) {
                    "si" -> "0"
                    "no" -> "100"
                    else -> "0"
                }
                itemList.add(valor)
                Rol_Emocional.add(valor.toInt())
                continue
            }
            if (index in listOf(23, 24, 25, 27, 29)) {
                var valor = ""
                if (index in listOf(23, 24, 27)){
                    valor = when (respuesta){
                        "Siempre" -> "0"
                        "Casi siempre" -> "20"
                        "Muchas veces" -> "40"
                        "Algunas veces" -> "60"
                        "Sólo alguna vez" -> "80"
                        "Nunca" -> "100"
                        else -> "0"
                    }
                }else{
                    valor = when (respuesta){
                        "Siempre" -> "100"
                        "Casi siempre" -> "80"
                        "Muchas veces" -> "60"
                        "Algunas veces" -> "40"
                        "Sólo alguna vez" -> "20"
                        "Nunca" -> "0"
                        else -> "0"
                    }
                }
                itemList.add(valor)
                Salud_Mental.add(valor.toInt())
                continue
            }
            if (index == 1){
                val valor = when (respuesta){
                    "Mucho mejor ahora que hace un año" -> "100"
                    "Algo mejor ahora que hace un año" -> "75"
                    "Más o menos igual que hace un año" -> "50"
                    "Algo peor ahora que hace un año" -> "25"
                    "Mucho peor ahora que hace un año" -> "0"
                    else -> "0"
                }
                itemList.add(valor)
                Cambio_Salud = valor.toInt()
            }
        }
        val values = listOf(
            id, usuario, F_Fisica.average().toInt().toString(), Rol_Fisico.average().toInt().toString(), Dolor.average().toInt().toString(), Salud.average().toInt().toString(),
            Vitalidad.average().toInt().toString(), FuncionS.average().toInt().toString(), Rol_Emocional.average().toInt().toString(), Salud_Mental.average().toInt().toString(),
            Cambio_Salud.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
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
        val id = "2401"
        val usuario = formattedString("pruebamenpas")
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
            id, usuario, round((Academico / 60).toDouble()).toString(),  round((Social / 60).toDouble()).toString(),  round((Emocional / 60).toDouble()).toString(),
            round((Familiar / 60).toDouble()).toString(),  round((Fisico / 60).toDouble()).toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
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