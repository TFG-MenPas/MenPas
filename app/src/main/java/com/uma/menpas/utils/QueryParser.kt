package com.uma.menpas.utils

import java.time.format.DateTimeFormatter

class QueryParser {

    fun parse(jsonResourceName: String, respuestasUsuario: Map<String, String>): String {
        return when (jsonResourceName) {
            "preguntas_csai2" -> parseCSAI2(respuestasUsuario)
            "preguntas_scat" -> parseSCAT(respuestasUsuario)
            "preguntas_acsi_28" -> parseACSI28(respuestasUsuario)
            "preguntas_stai_ar" -> parseSTAI(respuestasUsuario)
            "preguntas_stai_ae" -> parseSTAI(respuestasUsuario)
            "preguntas_embu" -> parseEMBU(respuestasUsuario)
            else -> parseCSAI2(respuestasUsuario)
        }
    }

    private fun parseEMBU(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO EMBU VALUES(" + respuestasUsuario["Id_EMBU"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["SoporteP"] + "," +
                respuestasUsuario["SoporteM"] + "," +
                respuestasUsuario["RechazoP"] + "," +
                respuestasUsuario["RechazoM"] + "," +
                respuestasUsuario["SobreproteccionP"] + "," +
                respuestasUsuario["SobreproteccionM"] + "," +
                respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," +
                respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," +
                respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," +
                respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," +
                respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," +
                respuestasUsuario["n11"] + "," +
                respuestasUsuario["n12"] + "," +
                respuestasUsuario["n13"] + "," +
                respuestasUsuario["n14"] + "," +
                respuestasUsuario["n15"] + "," +
                respuestasUsuario["n16"] + "," +
                respuestasUsuario["n17"] + "," +
                respuestasUsuario["n18"] + "," +
                respuestasUsuario["n19"] + "," +
                respuestasUsuario["n20"] + "," +
                respuestasUsuario["n21"] + "," +
                respuestasUsuario["n22"] + "," +
                respuestasUsuario["n23"] + "," +
                respuestasUsuario["n24"] + "," +
                respuestasUsuario["n25"] + "," +
                respuestasUsuario["n26"] + "," +
                respuestasUsuario["n27"] + "," +
                respuestasUsuario["n28"] + "," +
                respuestasUsuario["n29"] + "," +
                respuestasUsuario["n30"] + "," +
                respuestasUsuario["n31"] + "," +
                respuestasUsuario["n32"] + "," +
                respuestasUsuario["n33"] + "," +
                respuestasUsuario["n34"] + "," +
                respuestasUsuario["n35"] + "," +
                respuestasUsuario["n36"] + "," +
                respuestasUsuario["n37"] + "," +
                respuestasUsuario["n38"] + "," +
                respuestasUsuario["n39"] + "," +
                respuestasUsuario["n40"] + "," +
                respuestasUsuario["n41"] + "," +
                respuestasUsuario["n42"] + ")"
    }

    private fun parseSTAI(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO STAI VALUES(" + respuestasUsuario["ID_Stai"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Stai_A_R"] + "," +
                respuestasUsuario["Stai_A_E"] + "," +
                respuestasUsuario["Centiles"] + "," +
                respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["Item1"] + "," +
                respuestasUsuario["Item2"] + "," +
                respuestasUsuario["Item3"] + "," +
                respuestasUsuario["Item4"] + "," +
                respuestasUsuario["Item5"] + "," +
                respuestasUsuario["Item6"] + "," +
                respuestasUsuario["Item7"] + "," +
                respuestasUsuario["Item8"] + "," +
                respuestasUsuario["Item9"] + "," +
                respuestasUsuario["Item10"] + "," +
                respuestasUsuario["Item11"] + "," +
                respuestasUsuario["Item12"] + "," +
                respuestasUsuario["Item13"] + "," +
                respuestasUsuario["Item14"] + "," +
                respuestasUsuario["Item15"] + "," +
                respuestasUsuario["Item16"] + "," +
                respuestasUsuario["Item17"] + "," +
                respuestasUsuario["Item18"] + "," +
                respuestasUsuario["Item19"] + "," +
                respuestasUsuario["Item20"] + "," +
                respuestasUsuario["CentilesA_E"] + "," +
                respuestasUsuario["CentilesA_R"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Tipo"] + "," +
                respuestasUsuario["Tiempo"] + ")"
    }

    private fun parseACSI28(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO ACSI_28 VALUES(" + respuestasUsuario["Id_ACSI_28"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Rendimiento"] + "," +
                respuestasUsuario["Ausencia"] + "," +
                respuestasUsuario["Confrontacion"] + "," +
                respuestasUsuario["Concentracion"] + "," +
                respuestasUsuario["Formulacion"] + "," +
                respuestasUsuario["Confianza"] + "," +
                respuestasUsuario["Capacidad"] + "," +
                respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," +
                respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," +
                respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," +
                respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," +
                respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," +
                respuestasUsuario["n11"] + "," +
                respuestasUsuario["n12"] + "," +
                respuestasUsuario["n13"] + "," +
                respuestasUsuario["n14"] + "," +
                respuestasUsuario["n15"] + "," +
                respuestasUsuario["n16"] + "," +
                respuestasUsuario["n17"] + "," +
                respuestasUsuario["n18"] + "," +
                respuestasUsuario["n19"] + "," +
                respuestasUsuario["n20"] + "," +
                respuestasUsuario["n21"] + "," +
                respuestasUsuario["n22"] + "," +
                respuestasUsuario["n23"] + "," +
                respuestasUsuario["n24"] + "," +
                respuestasUsuario["n25"] + "," +
                respuestasUsuario["n26"] + "," +
                respuestasUsuario["n27"] + "," +
                respuestasUsuario["n28"] + ")"
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