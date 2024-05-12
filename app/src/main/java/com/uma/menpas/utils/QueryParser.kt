package com.uma.menpas.utils

import com.uma.menpas.cuestionarios.Modrian
import com.uma.menpas.cuestionarios.PDavidson
import java.time.format.DateTimeFormatter

class QueryParser {

    fun parse(jsonResourceName: String, respuestasUsuario: Map<String, String>): String {
        return when (jsonResourceName) {
            "preguntas_evaluacion_mental_iped" -> generateQuery(respuestasUsuario, "iped")
            "preguntas_entrenamiento_mental_epi" -> generateQuery(respuestasUsuario, "epi")
            "cuestionario_d2" -> generateQuery(respuestasUsuario, "d2")
            "preguntas_davidson_completo",
            "preguntas_davidson_resistencia",
            "preguntas_davidson_actitud",
            "preguntas_davidson_intuicion_social",
            "preguntas_davidson_autoconciencia",
            "preguntas_davidson_contexto",
            "preguntas_davidson_atencion" -> generateQuery(respuestasUsuario, "PED")
            "preguntas_poms_65",
            "preguntas_poms_58",
            "preguntas_poms_15",
            "preguntas_poms_6",
            "preguntas_poms_angustia_colera",
            "preguntas_poms_confusion_orientacion",
            "preguntas_poms_depresion",
            "preguntas_poms_fatiga_inercia",
            "preguntas_poms_tension_ansiedad",
            "preguntas_poms_vigor_activacion" -> generateQuery(respuestasUsuario, "poms")
            "cuestionario_modrian_colores" -> generateQuery(respuestasUsuario, "modrian")
            "cuestionario_modrian_parejas" -> generateQuery(respuestasUsuario, "modrian")
            "cuestionario_modrian_simon" -> generateQuery(respuestasUsuario, "modrian")
            "preguntas_csai2" -> generateQuery(respuestasUsuario, "csai2")
            "preguntas_csai2_somatica" -> generateQuery(respuestasUsuario, "csai2")
            "preguntas_csai2_autoconfianza" -> generateQuery(respuestasUsuario, "csai2")
            "preguntas_csai2_cognitiva" -> generateQuery(respuestasUsuario, "csai2")
            "preguntas_scat" -> generateQuery(respuestasUsuario, "scat")
            "preguntas_acsi_28" -> generateQuery(respuestasUsuario, "ACSI_28")
            "preguntas_stai_ae" -> generateQuery(respuestasUsuario, "STAI")
            "preguntas_stai_ar" -> generateQuery(respuestasUsuario, "STAI")
            "preguntas_embu" -> generateQuery(respuestasUsuario, "EMBU")
            "preguntas_eacs" -> generateQuery(respuestasUsuario, "EACS")
            "preguntas_mps" -> generateQuery(respuestasUsuario, "MPS")
            "preguntas_rs" -> generateQuery(respuestasUsuario, "RS")
            "preguntas_ipseta" -> generateQuery(respuestasUsuario, "IPSETA")
            "preguntas_maslach" -> generateQuery(respuestasUsuario, "burnout")
            "preguntas_abq" -> generateQuery(respuestasUsuario, "ABQ")
            "preguntas_preliminar_abq" -> generateQuery(respuestasUsuario, "DatosABQ")
            "preguntas_af5" -> generateQuery(respuestasUsuario, "AF5")
            "preguntas_bsq" -> generateQuery(respuestasUsuario, "BSQ")
            "preguntas_caf" -> generateQuery(respuestasUsuario, "CAF")
            "preguntas_sf_36" -> generateQuery(respuestasUsuario, "SF36")
            "preguntas_sf_12" -> generateQuery(respuestasUsuario, "SF12")
            "preguntas_dinamica_grupal_ccd" -> generateQuery(respuestasUsuario, "CCDeportiva")
            "preguntas_vitalidad_subjetiva" -> generateQuery(respuestasUsuario, "Vitalidad")
            "preguntas_autorregistro_comida" -> generateQuery(respuestasUsuario, "Autorregistros")
            "preguntas_autorregistro_diario" -> generateQuery(respuestasUsuario, "Autorregistros")
            "preguntas_autorregistro_pensamientos_negativos" -> generateQuery(respuestasUsuario, "Autorregistros")
            "preguntas_autorregistro_libre" -> generateQuery(respuestasUsuario, "Autorregistros")
            "preguntas_autorregistro_entrenamiento" -> generateQuery(respuestasUsuario, "Autorregistros")
            "cuestionario_stroop" -> generateQuery(respuestasUsuario, "stroop")
            "cuestionario_modrian_fotos" -> generateQuery(respuestasUsuario, "modrian")
            "cuestionario_modrian_stroop" -> generateQuery(respuestasUsuario, "modrian")
            else -> generateQuery(respuestasUsuario, "")
        }
    }

    private fun generateQuery(respuestasUsuario: Map<String, String>, nombreTabla: String): String {
        val valores = respuestasUsuario.values.joinToString(separator = ",")
        val claves = respuestasUsuario.keys.joinToString(separator = "," )
        val query = "INSERT INTO $nombreTabla ($claves) VALUES ($valores)"
        return query
    }
}