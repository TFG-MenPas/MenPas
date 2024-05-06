package com.uma.menpas.utils

import android.content.Context
import com.uma.menpas.cuestionarios.EPI
import com.uma.menpas.cuestionarios.IPED
import com.uma.menpas.cuestionarios.PDavidson
import com.uma.menpas.cuestionarios.POMS

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
            "preguntas_davidson_completo" -> PDavidson.calculatePDavidsonCompleto(respuestasUsuario, context)
            "preguntas_davidson_resistencia" -> PDavidson.calculatePDavidsonResistencia(respuestasUsuario, context)
            "preguntas_davidson_actitud" -> PDavidson.calculatePDavidsonActitud(respuestasUsuario, context)
            "preguntas_davidson_intuicion_social" -> PDavidson.calculatePDavidsonIntuicion(respuestasUsuario, context)
            "preguntas_davidson_autoconciencia" -> PDavidson.calculatePDavidsonAutoconciencia(respuestasUsuario, context)
            "preguntas_davidson_contexto" -> PDavidson.calculatePDavidsonContexto(respuestasUsuario, context)
            "preguntas_davidson_atencion" -> PDavidson.calculatePDavidsonAtencion(respuestasUsuario, context)
            "preguntas_poms_65" -> POMS.calculatePOMS65(respuestasUsuario, context)
            "preguntas_poms_58" -> POMS.calculatePOMS58(respuestasUsuario, context)
            "preguntas_poms_15" -> POMS.calculatePOMS15(respuestasUsuario, context)
            "preguntas_poms_6" -> POMS.calculatePOMS6(respuestasUsuario, context)
            "preguntas_poms_angustia_colera" -> POMS.calculatePOMSAngustiaColera(respuestasUsuario, context)
            "preguntas_poms_confusion_orientacion" -> POMS.calculatePOMSConfusionOrientacion(respuestasUsuario, context)
            "preguntas_poms_depresion" -> POMS.calculatePOMSDepresion(respuestasUsuario, context)
            "preguntas_poms_fatiga_inercia" -> POMS.calculatePOMSFatigaInercia(respuestasUsuario, context)
            "preguntas_poms_tension_ansiedad" -> POMS.calculatePOMSTensionAnsiedad(respuestasUsuario, context)
            "preguntas_poms_vigor_activacion" -> POMS.calculatePOMSVigorActivacion(respuestasUsuario, context)
            else -> IPED.calculateIPED(respuestasUsuario, usuario)
        }
    }
}