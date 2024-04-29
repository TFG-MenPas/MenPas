package com.uma.menpas.models

class TipoCuestionario {
    private val relacion: MutableMap<String, MutableList<String>> = mutableMapOf(
        "Ansiedad" to mutableListOf("CSAI2", "SCAT", "STAI"),
        "Atención" to mutableListOf("D2", "STROOP", "MODRIAN"),
        "Autoconcepto" to mutableListOf("AF5", "BSQ", "CAF"),
        "Autorregistros" to mutableListOf("AUTORREGISTROS"),
        "Burnout" to mutableListOf("MASLACH", "ABQ"),
        "Búsqueda de talentos" to mutableListOf("ACSI_28", "EMBU", "EACS", "IPSETA", "MPS", "RS"),
        "Calidad de vida" to mutableListOf("SF12", "SF36", "VITALIDAD"),
        "Dinámica grupal" to mutableListOf("CCD"),
        "E.Mental" to mutableListOf("IPED", "IPED_ANONIMO", "IPED_ARG", "EPI", "POMS"),
        "Inteligencia emocional" to mutableListOf("PED"),
    )

    private val conversiones: Map<String, String> = mapOf(
        "BURNOUT" to "MASLACH"
    )





    fun obtenerNombreYTipo(nombreCuestionario: String): Pair<String, String> {
        val nombreConvertido = conversiones[nombreCuestionario.uppercase()] ?: nombreCuestionario
        for ((tipo, nombres) in relacion) {
            if (nombreConvertido in nombres) {
                return Pair(tipo, nombreConvertido)
            }
        }
        return Pair("", nombreCuestionario)
    }

}