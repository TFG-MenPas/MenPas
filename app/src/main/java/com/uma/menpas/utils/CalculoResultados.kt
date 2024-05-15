package com.uma.menpas.utils

import android.content.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.cuestionarios.D2
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.round
import com.uma.menpas.cuestionarios.EPI
import com.uma.menpas.cuestionarios.IPED
import com.uma.menpas.cuestionarios.Modrian
import com.uma.menpas.cuestionarios.PDavidson
import com.uma.menpas.cuestionarios.POMS

class CalculoResultados {

    fun calculate(
        jsonResourceName: String,
        respuestasUsuario: ArrayList<String>,
        usuario: String,
        context: Context
    ): Map<String, String> {
        return when (jsonResourceName) {
            //Ansiedad
            "preguntas_csai2" -> calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_csai2_cognitiva" -> calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_csai2_autoconfianza" -> calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_csai2_somatica" -> calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_scat" -> calculateSCAT(respuestasUsuario, usuario)
            "preguntas_stai_ar" -> calculateSTAI(respuestasUsuario, usuario, false, context)
            "preguntas_stai_ae" -> calculateSTAI(respuestasUsuario, usuario, true, context)

            //Búsqueda de talentos
            "preguntas_acsi_28" -> calculateACSI28(respuestasUsuario, usuario)
            "preguntas_embu" -> calculateEMBU(respuestasUsuario, usuario)
            "preguntas_eacs" -> calculateEACS(respuestasUsuario, usuario)
            "preguntas_ipseta" -> calculateIPSETA(respuestasUsuario, usuario)
            "preguntas_mps" -> calculateMPS(respuestasUsuario, usuario)
            "preguntas_rs" -> calculateRS(respuestasUsuario, usuario)

            //Burnout
            "preguntas_maslach" -> calculateMaslach(respuestasUsuario, usuario)
            "preguntas_abq" -> calculateABQ(respuestasUsuario, usuario)
            "preguntas_preliminar_abq" -> calculatePreliminarABQ(respuestasUsuario, usuario)

            //Autoconcepto
            "preguntas_af5" -> calculateAF5(respuestasUsuario, usuario)
            "preguntas_bsq" -> calculateBSQ(respuestasUsuario, usuario)
            "preguntas_caf" -> calculateCAF(respuestasUsuario, usuario)

            //Calidad de vida
            "preguntas_sf_36" -> calculateSF36(respuestasUsuario, usuario)
            "preguntas_sf_12" -> calculateSF12(respuestasUsuario, usuario)
            "preguntas_vitalidad_subjetiva" -> calculateVS(respuestasUsuario, usuario)

            //Dinámica grupal
            "preguntas_dinamica_grupal_ccd" -> calculateCCD(respuestasUsuario, usuario)

            //Autorregistros
            "preguntas_autorregistro_comida" -> calculateAutoComida(respuestasUsuario, usuario)
            "preguntas_autorregistro_diario" -> calculateAutorregistroDiario(respuestasUsuario, usuario)
            "preguntas_autorregistro_entrenamiento" -> calculateAutorregistroEntrenamiento(respuestasUsuario, usuario)
            "preguntas_autorregistro_libre" -> calculateAutorregistroLibre(respuestasUsuario, usuario)
            "preguntas_autorregistro_pensamientos_negativos" -> calculateAutorregistroPN(respuestasUsuario, usuario)

            //Atención
            "cuestionario_stroop" -> calculateStroop(respuestasUsuario, usuario)
            "cuestionario_modrian_fotos" -> Modrian.calculateFotos(respuestasUsuario, usuario)
            "cuestionario_modrian_stroop" -> Modrian.calculateStroop(respuestasUsuario, usuario)
            "cuestionario_modrian_colores" -> Modrian.calculateColores(respuestasUsuario, usuario)
            "cuestionario_modrian_parejas" -> Modrian.calculateParejas(respuestasUsuario, usuario)
            "cuestionario_modrian_simon" -> Modrian.calculateSimon(respuestasUsuario, usuario)
            "cuestionario_d2" -> D2.calculate(respuestasUsuario, usuario)

            //Evaluación mental
            "preguntas_evaluacion_mental_iped_anonimo" -> IPED.calculateIPEDAnonimo(respuestasUsuario, "anónimo")
            "preguntas_evaluacion_mental_iped" -> IPED.calculateIPED(respuestasUsuario, usuario)
            "preguntas_entrenamiento_mental_epi" -> EPI.calculateEPI(respuestasUsuario, context)
            "preguntas_poms_65" -> POMS.calculatePOMS65(respuestasUsuario, context)
            "preguntas_poms_58" -> POMS.calculatePOMS58(respuestasUsuario, context)
            "preguntas_poms_15" -> POMS.calculatePOMS15(respuestasUsuario, context)
            "preguntas_poms_6" -> POMS.calculatePOMS6(respuestasUsuario, context)
            "preguntas_poms_angustia_colera" -> POMS.calculatePOMSAngustiaColera(respuestasUsuario, context)
            "preguntas_poms_confusion_orientacion" -> POMS.calculatePOMSConfusionOrientacion(respuestasUsuario, context)
            "preguntas_poms_depresion" -> POMS.calculatePOMSDepresion(respuestasUsuario, context)
            "preguntas_poms_fatiga_inercia" -> POMS.calculatePOMSFatigaInercia(respuestasUsuario, context)
            "preguntas_poms_tension_ansiedad" -> POMS.calculatePOMSTensionAnsiedad(respuestasUsuario, context)
            "preguntas_poms_vigor_activacion" -> POMS.calculatePOMSVigorActivacion(respuestasUsuario, context)

            //Inteligencia emocional
            "preguntas_davidson_completo" -> PDavidson.calculatePDavidsonCompleto(respuestasUsuario, context)
            "preguntas_davidson_resistencia" -> PDavidson.calculatePDavidsonResistencia(respuestasUsuario, context)
            "preguntas_davidson_actitud" -> PDavidson.calculatePDavidsonActitud(respuestasUsuario, context)
            "preguntas_davidson_intuicion_social" -> PDavidson.calculatePDavidsonIntuicion(respuestasUsuario, context)
            "preguntas_davidson_autoconciencia" -> PDavidson.calculatePDavidsonAutoconciencia(respuestasUsuario, context)
            "preguntas_davidson_contexto" -> PDavidson.calculatePDavidsonContexto(respuestasUsuario, context)
            "preguntas_davidson_atencion" -> PDavidson.calculatePDavidsonAtencion(respuestasUsuario, context)

            else -> calculateMPS(respuestasUsuario, usuario)
        }
    }

    private fun calculateStroop(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateAutorregistroEntrenamiento(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateAutorregistroPN(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateAutorregistroLibre(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf("ID_Autorregistro", "Nombre_Usuario", "Fecha", "Tipo", "campo1","campo2","campo3","campo4",
            "campo5","campo6","campo7","campo8","campo9","campo10")
        val id = CuestionarioService().obtenerIdDisponible("Autorregistros", "ID_Autorregistro")
        val fecha = formattedString(obtenerFechaActual())
        val tipo = formattedString("A Libre")
        val itemList = respuestasUsuario.map {it -> formattedString(it)}
        val values = listOf(id, formattedString(usuario), fecha, tipo, *itemList.toTypedArray())
        return keys.zip(values).toMap()
    }

    private fun calculateAutorregistroDiario(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateCCD(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf("Id_CCDeportiva", "Nombre_Usuario", "DCC", "DCI","SC",
            "SE", "SF", "n1", "n2", "n3", "n4",
            "n5", "n6", "n7", "n8", "n9", "n10", "n11", "n12", "n13", "n14", "n15", "Fecha", "Idioma",
            "Tiempo")
        val id = CuestionarioService().obtenerIdDisponible("CCDeportiva", "Id_CCDeportiva")
        val nombreUsuario = formattedString(usuario)
        val tiempo = "100"
        val idioma = formattedString("es-es")
        val fecha = formattedString(obtenerFechaActual())
        var dcc = 0
        var dci = 0
        var sc = 0
        var se = 0
        var sf = 0
        var itemList = mutableListOf<String>()
        for ((index, respuesta) in respuestasUsuario.withIndex()) {
            val valor = when (respuesta) {
                "Nada" -> "0"
                "Muy poco" -> "1"
                "Algo" -> "2"
                "Bastante" -> "3"
                "Mucho" -> "4"
                else -> "0"
            }
            itemList.add(valor)
            if (index in listOf(0,3,7,11)) {
                dcc += valor.toInt()
            } else if (index in listOf(1,10,13,14)) {
                dci += valor.toInt()
            }else if (index in listOf(5,12)) {
                sc += valor.toInt()
            } else if (index in listOf(4,6,8)) {
                se += valor.toInt()
            }else {
                sf += valor.toInt()
            }
        }
        val values = listOf(id, nombreUsuario, dcc.toString(), dci.toString(),
            sc.toString(), se.toString(), sf.toString(), *itemList.toTypedArray(), fecha,idioma,tiempo)
        return keys.zip(values).toMap()
    }

    private fun calculateRS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateMPS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateIPSETA(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateEACS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateEMBU(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateSTAI(respuestasUsuario: ArrayList<String>, usuario: String, isAE: Boolean, context: Context): Map<String, String> {
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

    private fun calculateACSI28(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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
    private fun calculateAutoComida(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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
            id, nombreUsuario, *itemList.toTypedArray(), idioma, fecha, tipo
        )
        return keys.zip(values).toMap()
    }

    private fun calculateVS(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf(
            "Id_Vitalidad", "Nombre_Usuario", "sol", "n1", "n2", "n3", "n4", "n5", "n6", "Tiempo", "Idioma", "Fecha"
        )
        val id = CuestionarioService().obtenerIdDisponible("Vitalidad", "Id_Vitalidad")
        val nombreUsuario = formattedString(usuario)
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
            id, nombreUsuario, valores.average().toInt().toString(), *itemList.toTypedArray(), tiempo, idioma, fecha,
        )
        return keys.zip(values).toMap()
    }

    private fun calculateSF12(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf(
            "Id_SF12", "Nombre_Usuario", "Medida_S_Físico", "Medida_S_Mental", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12"
        )
        val id = CuestionarioService().obtenerIdDisponible("SF12", "Id_SF12")
        val nombreUsuario = formattedString(usuario)
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
            id, nombreUsuario, Medida_S_Fisica.average().toInt().toString(), Medida_S_Mental.average().toInt().toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateSF36(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf(
            "Id_SF36", "Nombre_Usuario", "F_Física", "Rol_Físico", "Dolor", "Salud", "Vitalidad", "FunciónS", "Rol_Emocional", "Salud_Mental", "Cambio_Salud", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14",
            "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22",
            "n23", "n24", "n25", "n26", "n27",
            "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35",
            "n36"
        )
        val id = CuestionarioService().obtenerIdDisponible("SF36", "Id_SF36")
        val nombreUsuario = formattedString(usuario)
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
            id, nombreUsuario, F_Fisica.average().toInt().toString(), Rol_Fisico.average().toInt().toString(), Dolor.average().toInt().toString(), Salud.average().toInt().toString(),
            Vitalidad.average().toInt().toString(), FuncionS.average().toInt().toString(), Rol_Emocional.average().toInt().toString(), Salud_Mental.average().toInt().toString(),
            Cambio_Salud.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateCAF(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf(
            "Id_CAF", "Nombre_Usuario", "Habilidad", "Condición", "Atractivo", "Fuerza", "AutoconceptoF", "AutoconceptoG", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14",
            "n15", "n16", "n17", "n18", "n19", "n20", "n21", "n22",
            "n23", "n24", "n25", "n26", "n27",
            "n28", "n29", "n30", "n31", "n32", "n33", "n34", "n35",
            "n36"
        )
        val id = CuestionarioService().obtenerIdDisponible("CAF", "Id_CAF")
        val nombreUsuario = formattedString(usuario)
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
            id, nombreUsuario, Habilidad.toString(), Condicion.toString(), Atractivo.toString(), Fuerza.toString(), AutoconceptoF.toString(), AutoconceptoG.toString(),tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateBSQ(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf(
            "Id_BSQ", "Nombre_Usuario", "Factor1", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14"
        )
        val id = CuestionarioService().obtenerIdDisponible("BSQ", "Id_BSQ")
        val nombreUsuario = formattedString(usuario)
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
            id, nombreUsuario, Factor1.toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculateAF5(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
        val keys = listOf(
            "Id_AF5", "Nombre_Usuario", "Académico", "Social", "Emocional", "Familiar", "Físico", "Tiempo", "Idioma", "Fecha", "n1",
            "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9",
            "n10", "n11", "n12", "n13", "n14", "n15", "n16",
            "n17", "n18", "n19", "n20", "n21", "n22", "n23", "n24",
            "n25", "n26", "n27", "n28", "n29", "n30"
        )
        val id = CuestionarioService().obtenerIdDisponible("AF5", "Id_AF5")
        val nombreUsuario = formattedString(usuario)
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
            id, nombreUsuario, round((Academico / 60).toDouble()).toString(),  round((Social / 60).toDouble()).toString(),  round((Emocional / 60).toDouble()).toString(),
            round((Familiar / 60).toDouble()).toString(),  round((Fisico / 60).toDouble()).toString(), tiempo, idioma, fecha, *itemList.toTypedArray()
        )
        return keys.zip(values).toMap()
    }

    private fun calculatePreliminarABQ(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateABQ(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateMaslach(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateSCAT(respuestasUsuario: ArrayList<String>, usuario: String): Map<String, String> {
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

    private fun calculateCSAI2(respuestasUsuario: ArrayList<String>, usuario: String, jsonResourceName: String): Map<String, String> {
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

    private fun obtenerFechaActual(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss")
        return currentDateTime.format(formatter)
    }

    private fun formattedString(s: String): String {
        return "'$s'"
    }

    private fun calculoCentilesSTAI(sexo: String, mayorEdad: Boolean, valor: Int): String {
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