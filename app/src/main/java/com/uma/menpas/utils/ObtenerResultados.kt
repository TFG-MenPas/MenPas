package com.uma.menpas.utils

class ObtenerResultados {

    fun obtenerResultados(jsonResourceName: String, calculosCuestionario: Map<String, String>): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_csai2" -> obtenerResultadosCSAI2(calculosCuestionario)
            "preguntas_scat" -> obtenerResultadosSCAT(calculosCuestionario)
            else -> obtenerResultadosSCAT(calculosCuestionario)
        }
    }

    private fun obtenerResultadosSCAT(calculosCuestionario: Map<String, String>): Map<String, String>{
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Resultado:"] = calculosCuestionario["Resultado_Scat"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SCAT (Rainer Martens)"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosCSAI2(calculosCuestionario: Map<String, String>): Map<String, String>{
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Cognitiva:"] = calculosCuestionario["Cognitiva"] ?: ""
        resultadosMostrados["Somática:"] = calculosCuestionario["Somatica"] ?: ""
        resultadosMostrados["Autoconfianza:"] = calculosCuestionario["Autoconfianza"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CSAI2"

        return resultadosMostrados.toMap()
    }



}