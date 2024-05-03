package com.uma.menpas.utils

import android.content.Context
import com.uma.menpas.cuestionarios.EPI
import com.uma.menpas.cuestionarios.IPED
import com.uma.menpas.cuestionarios.PDavidson

class CalculoResultados {

    fun calculate(
        jsonResourceName: String,
        respuestasUsuario: ArrayList<String>,
        usuario: String,
        context: Context
    ): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_evaluacion_mental_iped" -> IPED.calculateIPED(respuestasUsuario, usuario)
            "preguntas_entrenamiento_mental_epi" -> EPI.calculateEPI(respuestasUsuario, context)
            "preguntas_davidson_completo" -> PDavidson.calculatePDavidsonCompleto(respuestasUsuario, usuario)
            else -> IPED.calculateIPED(respuestasUsuario, usuario)
        }
    }
}