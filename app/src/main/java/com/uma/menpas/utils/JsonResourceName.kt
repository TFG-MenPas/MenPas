package com.uma.menpas.utils

class JsonResourceName {

    fun getJsonResourceName(nombreCuestionario: String, calculosCuestionario: Map<String, String>): String {
        val relacionCsai = mutableMapOf(
            "CSAI2" to "preguntas_csai2",
            "AUTOCONFIANZA" to "preguntas_csai2_autoconfianza",
            "COGNITIVA" to "preguntas_csai2_cognitiva",
            "SOMATICA" to "preguntas_csai2_somatica")

        val relacionPoms = mutableMapOf(
            "POMS 65 ITEMS" to "preguntas_poms_65",
            "POMS 58 ITEMS" to "preguntas_poms_58",
            "POMS15ITEMS" to "preguntas_poms_15",
            "POMS 6 ITEMS" to "preguntas_poms_6",
            "DEPRESIÓN_MELANCOLÍA" to "preguntas_poms_depresion",
            "TENSIÓN_ANSIEDAD" to "preguntas_poms_tension_ansiedad",
            "FATIGA_INERCIA" to "preguntas_poms_fatiga_inercia",
            "ANGUSTIA_CÓLERA" to "preguntas_poms_angustia_colera",
            "VIGOR_ACTIVACIÓN" to "preguntas_poms_vigor_activacion",
            "CONFUSIÓN ORIENTACIÓN" to "preguntas_poms_confusion_orientacion"
        )

        return when (nombreCuestionario) {
            "CSAI2" -> {
                return relacionCsai[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            }
            "SCAT" -> ""
            "STAI" -> ""
            "D2" -> ""
            "STROOP" -> ""
            "MODRIAN" -> ""
            "AF5" -> ""
            "BSQ" -> ""
            "CAF" -> ""
            "AUTORREGISTROS" -> ""
            "MASLACH" -> ""
            "ABQ" -> ""
            "ACSI_28" -> ""
            "EMBU" -> ""
            "EACS" -> ""
            "IPSETA" -> ""
            "MPS" -> ""
            "RS" -> ""
            "SF12" -> ""
            "SF36" -> ""
            "VITALIDAD" -> ""
            "CCD" -> ""
            "IPED" -> ""
            "IPED_ANONIMO"  -> ""
            "IPED_ARG" -> ""
            "EPI" -> ""
            "POMS" -> {
                val tipoFormateado = (calculosCuestionario["Tipo"]!!).uppercase()
                return relacionPoms[tipoFormateado]!!
            }
            "PED" -> ""
            else -> ""
        }
    }
}