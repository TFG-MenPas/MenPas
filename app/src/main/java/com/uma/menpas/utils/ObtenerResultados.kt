package com.uma.menpas.utils

class ObtenerResultados {

    fun obtenerResultados(jsonResourceName: String, calculosCuestionario: Map<String, String>): Map<String, String> {
        return when (jsonResourceName) {
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

}