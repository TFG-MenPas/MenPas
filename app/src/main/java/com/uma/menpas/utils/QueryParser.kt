package com.uma.menpas.utils

import java.time.format.DateTimeFormatter

class QueryParser {

    fun parse(jsonResourceName: String, respuestasUsuario: Map<String, String>): String {
        return when (jsonResourceName) {
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