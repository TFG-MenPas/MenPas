package com.uma.menpas.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class QueryParser {

    fun parse(jsonResourceName: String, respuestasUsuario: Map<String,String>): String {
        return when (jsonResourceName) {
            "preguntas_csai2" -> parseCSAI2(respuestasUsuario)
            "preguntas_scat" -> parseSCAT(respuestasUsuario)
            "preguntas_maslach" -> parseMaslach(respuestasUsuario)
            "preguntas_abq" -> parseABQ(respuestasUsuario)
            "preguntas_preliminar_abq" -> parsePreliminarABQ(respuestasUsuario)
            "preguntas_af5" -> parseAF5(respuestasUsuario)
            "preguntas_bsq" -> parseBSQ(respuestasUsuario)
            "preguntas_caf" -> parseCAF(respuestasUsuario)
            else -> parseCSAI2(respuestasUsuario)
        }
    }

    private fun parseCAF(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO CAF VALUES(" + respuestasUsuario["Id_CAF"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Habilidad"] + "," + respuestasUsuario["Condicion"] + "," + respuestasUsuario["Atractivo"] + "," +
                respuestasUsuario["Fuerza"] + "," + respuestasUsuario["AutoconceptoF"] + "," + respuestasUsuario["AutoconceptoG"] + "," +
                respuestasUsuario["Tiempo"] + "," + respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," + respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," + respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," + respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," + respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," + respuestasUsuario["n11"] + "," + respuestasUsuario["n12"] + "," + respuestasUsuario["n13"] + "," +
                respuestasUsuario["n14"] + "," + respuestasUsuario["n15"] + "," +
                respuestasUsuario["n16"] + "," + respuestasUsuario["n17"] + "," +
                respuestasUsuario["n18"] + "," + respuestasUsuario["n19"] + "," +
                respuestasUsuario["n20"] + "," + respuestasUsuario["n21"] + "," +
                respuestasUsuario["n22"] + "," + respuestasUsuario["n23"] + "," +
                respuestasUsuario["n24"] + "," + respuestasUsuario["n25"] + "," + respuestasUsuario["n26"] + "," + respuestasUsuario["n27"] + "," +
                respuestasUsuario["n28"] + "," + respuestasUsuario["n29"] + "," + respuestasUsuario["n30"] + "," +
                respuestasUsuario["n31"] + "," + respuestasUsuario["n32"] + "," + respuestasUsuario["n33"] + "," + respuestasUsuario["n34"] + "," +
                respuestasUsuario["n35"] +  "," + respuestasUsuario["n36"] + ")"
    }

    private fun parseBSQ(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO BSQ VALUES(" + respuestasUsuario["Id_BSQ"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Factor1"] + "," + respuestasUsuario["Tiempo"] + "," + respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," + respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," + respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," + respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," + respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," + respuestasUsuario["n11"] + "," + respuestasUsuario["n12"] + "," + respuestasUsuario["n13"] + "," +
                respuestasUsuario["n14"] + ")"
    }

    private fun parseAF5(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO AF5 VALUES(" + respuestasUsuario["Id_AF5"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Académico"] + "," + respuestasUsuario["Social"] + "," + respuestasUsuario["Emocional"] + "," +
                respuestasUsuario["Familiar"] + "," + respuestasUsuario["Físico"] + "," + respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," + respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," + respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," + respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," + respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," + respuestasUsuario["n11"] + "," + respuestasUsuario["n12"] + "," + respuestasUsuario["n13"] + "," +
                respuestasUsuario["n14"] + "," + respuestasUsuario["n15"] + "," + respuestasUsuario["n16"] + "," +
                respuestasUsuario["n17"] + "," + respuestasUsuario["n18"] + "," +
                respuestasUsuario["n19"] + "," + respuestasUsuario["n20"] + "," +
                respuestasUsuario["n21"] + "," + respuestasUsuario["n22"] + "," +
                respuestasUsuario["n23"] + "," + respuestasUsuario["n24"] + "," +
                respuestasUsuario["n25"] + "," + respuestasUsuario["n26"] + "," + respuestasUsuario["n27"] + "," + respuestasUsuario["n28"] + "," +
                respuestasUsuario["n29"] + "," + respuestasUsuario["n30"] + ")"
    }

    private fun parsePreliminarABQ(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO DatosABQ VALUES(" + respuestasUsuario["Id_DatosABQ"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Años"] + "," + respuestasUsuario["Antes_de"] + "," + respuestasUsuario["Club"] + "," +
                respuestasUsuario["categoria"] + "," + respuestasUsuario["Nivel"] + "," +
                respuestasUsuario["Numero"] + "," + respuestasUsuario["Duracion"] + "," +
                respuestasUsuario["Meses"] + "," + respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["Idioma"] + ")"
    }

    private fun parseABQ(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO ABQ VALUES(" + respuestasUsuario["id_ABQ"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["AFE"] + "," + respuestasUsuario["RSL"] + "," + respuestasUsuario["DPD"] + "," +
                respuestasUsuario["AFE_T"] + "," + respuestasUsuario["RSL_T"] + "," +
                respuestasUsuario["DPD_T"] + "," + respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," + respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," + respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," + respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," + respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," + respuestasUsuario["n11"] + "," + respuestasUsuario["n12"] + "," + respuestasUsuario["n13"] + "," +
                respuestasUsuario["n14"] + "," + respuestasUsuario["n15"] + "," + respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Tiempo"] + ")"
    }
    private fun parseMaslach(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO burnout VALUES(" + respuestasUsuario["ID_Burnout"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Escala_CE"] + "," + respuestasUsuario["Escala_DP"] + "," + respuestasUsuario["Escala_RP"] + "," + respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["Item1"] + "," + respuestasUsuario["Item2"] + "," +
                respuestasUsuario["Item3"] + "," + respuestasUsuario["Item4"] + "," +
                respuestasUsuario["Item5"] + "," + respuestasUsuario["Item6"] + "," +
                respuestasUsuario["Item7"] + "," + respuestasUsuario["Item8"] + "," +
                respuestasUsuario["Item9"] + "," + respuestasUsuario["Item10"] + "," +
                respuestasUsuario["Item11"] + "," + respuestasUsuario["Item12"] + "," +
                respuestasUsuario["Item13"] + "," + respuestasUsuario["Item14"] + "," + respuestasUsuario["Item15"] + "," + respuestasUsuario["Item16"] + "," +
                respuestasUsuario["Item17"] + "," + respuestasUsuario["Item18"] + "," + respuestasUsuario["Item19"] + "," + respuestasUsuario["Item20"] + "," +
                respuestasUsuario["Item21"] + "," + respuestasUsuario["Item22"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Tiempo"] + ")"
    }

    private fun parseSCAT(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO scat VALUES(" + respuestasUsuario["ID_Scat"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Resultado_Scat"] + "," + respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["Item1"] + "," + respuestasUsuario["Item2"] + "," +
                respuestasUsuario["Item3"] + "," + respuestasUsuario["Item4"] + "," +
                respuestasUsuario["Item5"] + "," + respuestasUsuario["Item6"] + "," +
                respuestasUsuario["Item7"] + "," + respuestasUsuario["Item8"] + "," +
                respuestasUsuario["Item9"] + "," + respuestasUsuario["Item10"] + "," +
                respuestasUsuario["Item11"] + "," + respuestasUsuario["Item12"] + "," +
                respuestasUsuario["Item13"] + "," + respuestasUsuario["Item14"] + "," +
                respuestasUsuario["Item15"] + "," + respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Tiempo"] + ")"
    }

    private fun parseCSAI2(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO csai2 VALUES(" + respuestasUsuario["ID_CSAI2"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Cognitiva"] + "," + respuestasUsuario["Somatica"] + "," +
                respuestasUsuario["Autoconfianza"] + "," + respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["Item1"] + "," + respuestasUsuario["Item2"] + "," +
                respuestasUsuario["Item3"] + "," + respuestasUsuario["Item4"] + "," +
                respuestasUsuario["Item5"] + "," + respuestasUsuario["Item6"] + "," +
                respuestasUsuario["Item7"] + "," + respuestasUsuario["Item8"] + "," +
                respuestasUsuario["Item9"] + "," + respuestasUsuario["Item10"] + "," +
                respuestasUsuario["Item11"] + "," + respuestasUsuario["Item12"] + "," +
                respuestasUsuario["Item13"] + "," + respuestasUsuario["Item14"] + "," +
                respuestasUsuario["Item15"] + "," + respuestasUsuario["Item16"] + "," +
                respuestasUsuario["Item17"] + "," + respuestasUsuario["Item18"] + "," +
                respuestasUsuario["Item19"] + "," + respuestasUsuario["Item20"] + "," +
                respuestasUsuario["Item21"] + "," + respuestasUsuario["Item22"] + "," +
                respuestasUsuario["Item23"] + "," + respuestasUsuario["Item24"] + "," +
                respuestasUsuario["Item25"] + "," + respuestasUsuario["Item26"] + "," +
                respuestasUsuario["Item27"] + "," + respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Tipo"] + "," + respuestasUsuario["Tiempo"] + ")"
    }

}