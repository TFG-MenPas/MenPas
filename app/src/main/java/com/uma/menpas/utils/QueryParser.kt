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
            "preguntas_eacs" -> parseEACS(respuestasUsuario)
            "preguntas_mps" -> parseMPS(respuestasUsuario)
            "preguntas_rs" -> parseRS(respuestasUsuario)
            "preguntas_ipseta" -> parseIPSETA(respuestasUsuario)
            "preguntas_maslach" -> parseMaslach(respuestasUsuario)
            "preguntas_abq" -> parseABQ(respuestasUsuario)
            "preguntas_preliminar_abq" -> parsePreliminarABQ(respuestasUsuario)
            "preguntas_af5" -> parseAF5(respuestasUsuario)
            "preguntas_bsq" -> parseBSQ(respuestasUsuario)
            "preguntas_caf" -> parseCAF(respuestasUsuario)
            "preguntas_sf_36" -> parseSF36(respuestasUsuario)
            "preguntas_sf_12" -> parseSF12(respuestasUsuario)
            "preguntas_vitalidad_subjetiva" -> parseVS(respuestasUsuario)
            "preguntas_autorregistro_comida" -> parseAutoComida(respuestasUsuario)
            "preguntas_evaluacion_mental_iped" -> parseGeneric(respuestasUsuario, "iped")
            "preguntas_entrenamiento_mental_epi" -> parseGeneric(respuestasUsuario, "epi")
            "cuestionario_d2" -> parseGeneric(respuestasUsuario, "d2")
            else -> parseCSAI2(respuestasUsuario)
        }
    }

    private fun parseIPSETA(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO IPSETA (Id_IPSETA, Nombre_Usuario, M_Intrinseca, M_Logro, Auto, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, Tiempo, Idioma, Fecha) VALUES(" +
                respuestasUsuario["Id_IPSETA"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["M_Intrinseca"] + "," +
                respuestasUsuario["M_Logro"] + "," +
                respuestasUsuario["Auto"] + "," +
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
                respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," +
                respuestasUsuario["Fecha"] + ")"

    }

    private fun parseRS(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO RS VALUES(" + respuestasUsuario["Id_RS"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Factor1"] + "," +
                respuestasUsuario["Factor2"] + "," +
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
                respuestasUsuario["n25"] + ")"
    }

    private fun parseMPS(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO MPS VALUES(" + respuestasUsuario["Id_MPS"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Preocupaciones"] + "," +
                respuestasUsuario["Normas"] + "," +
                respuestasUsuario["Expectativas"] + "," +
                respuestasUsuario["Criticas"] + "," +
                respuestasUsuario["Dudas"] + "," +
                respuestasUsuario["Organizacion"] + "," +
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
                respuestasUsuario["n35"] + ")"
    }

    private fun parseEACS(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO EACS VALUES(" + respuestasUsuario["Id_EACS"] + "," +
                respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Factor1_Marginado"] + "," +
                respuestasUsuario["Factor2_Aislamiento"] + "," +
                respuestasUsuario["Factor3_Disciplina"] + "," +
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
                respuestasUsuario["n12"] + ")"
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

    private fun parseAutoComida(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO Autorregistros  (Id_Autorregistro, Nombre_Usuario, Dia, Hora, Tiempo, Cantidad, Actividad, LugarComida, Comida_tipo, Calorias, Categoria, Idioma, Fecha, Tipo) VALUES(" + respuestasUsuario["Id_Autorregistro"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
        respuestasUsuario["Dia"] + "," +
                respuestasUsuario["Hora"] + "," +
                respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Cantidad"] + "," + respuestasUsuario["Actividad"] + "," +
                respuestasUsuario["LugarComida"] + "," + respuestasUsuario["Comida_tipo"] + "," +
                respuestasUsuario["Calorias"] + "," + respuestasUsuario["Categoria"] + "," + respuestasUsuario["Idioma"] + "," + respuestasUsuario["Fecha"] + "," + respuestasUsuario["Tipo"] + ")"
    }

    private fun parseVS(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO Vitalidad  (Id_Vitalidad, Nombre_Usuario, sol, n1, n2, n3, n4, n5, n6, Tiempo, Idioma, Fecha) VALUES(" + respuestasUsuario["Id_Vitalidad"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["sol"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," + respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," + respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," + respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," + respuestasUsuario["Fecha"] + ")"
    }

    private fun parseSF12(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO SF12 VALUES(" + respuestasUsuario["Id_SF12"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["Medida_S_Fisica"] + "," + respuestasUsuario["Medida_S_Mental"] + "," +  respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," + respuestasUsuario["Fecha"] + "," +
                respuestasUsuario["n1"] + "," +
                respuestasUsuario["n2"] + "," + respuestasUsuario["n3"] + "," +
                respuestasUsuario["n4"] + "," + respuestasUsuario["n5"] + "," +
                respuestasUsuario["n6"] + "," + respuestasUsuario["n7"] + "," +
                respuestasUsuario["n8"] + "," + respuestasUsuario["n9"] + "," +
                respuestasUsuario["n10"] + "," + respuestasUsuario["n11"] + "," + respuestasUsuario["n12"] + ")"
    }

    private fun parseSF36(respuestasUsuario: Map<String, String>): String {
        return "INSERT INTO SF36 VALUES(" + respuestasUsuario["Id_SF36"] + "," + respuestasUsuario["Nombre_Usuario"] + "," +
                respuestasUsuario["F_Fisica"] + "," + respuestasUsuario["Rol_Fisico"] + "," + respuestasUsuario["Dolor"] + "," +
                respuestasUsuario["Salud"] + "," + respuestasUsuario["Vitalidad"] + "," + respuestasUsuario["FuncionS"] + "," +
                respuestasUsuario["Rol_Emocional"] + "," + respuestasUsuario["Salud_Mental"] + "," +
                respuestasUsuario["Cambio_Salud"] + "," +  respuestasUsuario["Tiempo"] + "," +
                respuestasUsuario["Idioma"] + "," + respuestasUsuario["Fecha"] + "," +
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

    private fun parseGeneric(respuestasUsuario: Map<String, String>, nombreTabla: String): String {
        val valores = respuestasUsuario.values.joinToString(separator = ",")
        val query = "INSERT INTO $nombreTabla VALUES($valores)"
        return query
    }


}