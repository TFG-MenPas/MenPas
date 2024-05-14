package com.uma.menpas.utils

class JsonResourceName {

    fun getJsonResourceName(nombreCuestionario: String, calculosCuestionario: Map<String, String>): String {
        val relacionCsai = mutableMapOf(
            "CSAI2" to "preguntas_csai2",
            "AUTOCONFIANZA" to "preguntas_csai2_autoconfianza",
            "COGNITIVA" to "preguntas_csai2_cognitiva",
            "SOMATICA" to "preguntas_csai2_somatica")

        val relacionPoms = mutableMapOf(
            "POMS 65 ÍTEMS" to "preguntas_poms_65",
            "POMS 58 ÍTEMS" to "preguntas_poms_58",
            "POMS15ITEMS" to "preguntas_poms_15",
            "POMS 6 ITEMS" to "preguntas_poms_6",
            "DEPRESIÓN_MELANCOLÍA" to "preguntas_poms_depresion",
            "TENSIÓN_ANSIEDAD" to "preguntas_poms_tension_ansiedad",
            "FATIGA_INERCIA" to "preguntas_poms_fatiga_inercia",
            "ANGUSTIA_CÓLERA" to "preguntas_poms_angustia_colera",
            "VIGOR_ACTIVACIÓN" to "preguntas_poms_vigor_activacion",
            "CONFUSIÓN ORIENTACIÓN" to "preguntas_poms_confusion_orientacion"
        )

        val relacionSTAI = mutableMapOf(
            "STAI_A_E" to "preguntas_stai_ae",
            "STAI_A_R" to "preguntas_stai_ar"
        )

        val relacionModrian = mutableMapOf(
            "MODRIAN COLORES" to "cuestionario_modrian_colores",
            "MODRIAN STROOP" to "cuestionario_modrian_stroop",
            "MODRIAN PAREJAS" to "cuestionario_modrian_parejas",
            "MODRIAN IMAGENES" to "cuestionario_modrian_fotos",
            "MODRIAN SIMON" to "cuestionario_modrian_simon"
        )

        val relacionAutorregistros = mutableMapOf(
            "A PENSAMIENTOS N" to "preguntas_autorregistro_pensamientos_negativos",
            "A COMIDA" to "preguntas_autorregistro_comida",
            "A ENTRENAMIENTO" to "preguntas_autorregistro_entrenamiento",
            "A DIARIO" to "preguntas_autorregistro_diario",
            "A LIBRE" to "preguntas_autorregistro_libre"
        )

        val relacionPed = mutableMapOf(
            "PED_RESISTENCIA" to "preguntas_davidson_resistencia",
            "PED_CONTEXTO" to "preguntas_davidson_contexto",
            "PED_INTUICION" to "preguntas_davidson_intuicion_social",
            "PED_AUTOCONCIENCIA" to "preguntas_davidson_autoconciencia",
            "PED_ATENCION" to "preguntas_davidson_atencion",
            "PED_ACTITUD" to "preguntas_davidson_actitud",
            "PED_COMPLETO" to "preguntas_davidson_completo"
        )

        return when (nombreCuestionario) {
            "CSAI2" -> relacionCsai[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            "SCAT" -> "preguntas_scat"
            "STAI" -> relacionSTAI[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            "D2" -> "cuestionario_d2"
            "STROOP" -> "cuestionario_stroop"
            "MODRIAN" -> relacionModrian[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            "AF5" -> "preguntas_af5"
            "BSQ" -> "preguntas_bsq"
            "CAF" -> "preguntas_caf"
            "AUTORREGISTROS" -> relacionAutorregistros[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            "MASLACH" -> "preguntas_maslach"
            "ABQ" -> "preguntas_abq"
            "ACSI_28" -> "preguntas_acsi_28"
            "EMBU" -> "preguntas_embu"
            "EACS" -> "preguntas_eacs"
            "IPSETA" -> "preguntas_ipseta"
            "MPS" -> "preguntas_mps"
            "RS" -> "preguntas_rs"
            "SF12" -> "preguntas_sf_12"
            "SF36" -> "preguntas_sf_36"
            "VITALIDAD" -> "preguntas_vitalidad_subjetiva"
            "CCD" -> "preguntas_dinamica_grupal_ccd"
            "IPED" -> "preguntas_evaluacion_mental_iped"
            "IPED_ANONIMO"  -> "preguntas_evaluacion_mental_iped"
            "IPED_ARG" -> "preguntas_evaluacion_mental_iped"
            "EPI" -> "preguntas_entrenamiento_mental_epi"
            "POMS" -> relacionPoms[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            "PED" -> relacionPed[(calculosCuestionario["Tipo"]!!).uppercase()]!!
            else -> "preguntas_mps"
        }
    }
}