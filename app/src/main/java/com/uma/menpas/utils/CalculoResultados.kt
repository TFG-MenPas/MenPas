package com.uma.menpas.utils

import android.content.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.cuestionarios.*
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.round

class CalculoResultados {

    fun calculate(
        jsonResourceName: String,
        respuestasUsuario: ArrayList<String>,
        usuario: String,
        context: Context
    ): Map<String, String> {
        return when (jsonResourceName) {
            //Ansiedad
            "preguntas_csai2" -> Ansiedad.calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_csai2_cognitiva" -> Ansiedad.calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_csai2_autoconfianza" -> Ansiedad.calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_csai2_somatica" -> Ansiedad.calculateCSAI2(respuestasUsuario, usuario, jsonResourceName)
            "preguntas_scat" -> Ansiedad.calculateSCAT(respuestasUsuario, usuario)
            "preguntas_stai_ar" -> Ansiedad.calculateSTAI(respuestasUsuario, usuario, false, context)
            "preguntas_stai_ae" -> Ansiedad.calculateSTAI(respuestasUsuario, usuario, true, context)

            //Búsqueda de talentos
            "preguntas_acsi_28" -> BusquedaDeTalentos.calculateACSI28(respuestasUsuario, usuario)
            "preguntas_embu" -> BusquedaDeTalentos.calculateEMBU(respuestasUsuario, usuario)
            "preguntas_eacs" -> BusquedaDeTalentos.calculateEACS(respuestasUsuario, usuario)
            "preguntas_ipseta" -> BusquedaDeTalentos.calculateIPSETA(respuestasUsuario, usuario)
            "preguntas_mps" -> BusquedaDeTalentos.calculateMPS(respuestasUsuario, usuario)
            "preguntas_rs" -> BusquedaDeTalentos.calculateRS(respuestasUsuario, usuario)

            //Burnout
            "preguntas_maslach" -> Burnout.calculateMaslach(respuestasUsuario, usuario)
            "preguntas_abq" -> Burnout.calculateABQ(respuestasUsuario, usuario)
            "preguntas_preliminar_abq" -> Burnout.calculatePreliminarABQ(respuestasUsuario, usuario)

            //Autoconcepto
            "preguntas_af5" -> Autoconcepto.calculateAF5(respuestasUsuario, usuario)
            "preguntas_bsq" -> Autoconcepto.calculateBSQ(respuestasUsuario, usuario)
            "preguntas_caf" -> Autoconcepto.calculateCAF(respuestasUsuario, usuario)

            //Calidad de vida
            "preguntas_sf_36" -> CalidadDeVida.calculateSF36(respuestasUsuario, usuario)
            "preguntas_sf_12" -> CalidadDeVida.calculateSF12(respuestasUsuario, usuario)
            "preguntas_vitalidad_subjetiva" -> CalidadDeVida.calculateVS(respuestasUsuario, usuario)

            //Dinámica grupal
            "preguntas_dinamica_grupal_ccd" -> DinamicaGrupal.calculateCCD(respuestasUsuario, usuario)

            //Autorregistros
            "preguntas_autorregistro_comida" -> Autorregistros.calculateAutoComida(respuestasUsuario, usuario)
            "preguntas_autorregistro_diario" -> Autorregistros.calculateAutorregistroDiario(respuestasUsuario, usuario)
            "preguntas_autorregistro_entrenamiento" -> Autorregistros.calculateAutorregistroEntrenamiento(respuestasUsuario, usuario)
            "preguntas_autorregistro_libre" -> Autorregistros.calculateAutorregistroLibre(respuestasUsuario, usuario)
            "preguntas_autorregistro_pensamientos_negativos" -> Autorregistros.calculateAutorregistroPN(respuestasUsuario, usuario)

            //Atención
            "cuestionario_stroop" -> Stroop.calculateStroop(respuestasUsuario, usuario)
            "cuestionario_modrian_fotos" -> Modrian.calculateFotos(respuestasUsuario, usuario)
            "cuestionario_modrian_stroop" -> Modrian.calculateStroop(respuestasUsuario, usuario)
            "cuestionario_modrian_colores" -> Modrian.calculateColores(respuestasUsuario, usuario)
            "cuestionario_modrian_parejas" -> Modrian.calculateParejas(respuestasUsuario, usuario)
            "cuestionario_modrian_simon" -> Modrian.calculateSimon(respuestasUsuario, usuario)
            "cuestionario_d2" -> D2.calculate(respuestasUsuario, usuario)

            //Evaluación mental
            "preguntas_evaluacion_mental_iped_anonimo" -> IPED.calculateIPEDAnonimo(respuestasUsuario, "anónimo")
            "preguntas_evaluacion_mental_iped" -> IPED.calculateIPED(respuestasUsuario, usuario)
            "preguntas_entrenamiento_mental_epi" -> EPI.calculateEPI(respuestasUsuario, context)
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

            //Inteligencia emocional
            "preguntas_davidson_completo" -> PDavidson.calculatePDavidsonCompleto(respuestasUsuario, context)
            "preguntas_davidson_resistencia" -> PDavidson.calculatePDavidsonResistencia(respuestasUsuario, context)
            "preguntas_davidson_actitud" -> PDavidson.calculatePDavidsonActitud(respuestasUsuario, context)
            "preguntas_davidson_intuicion_social" -> PDavidson.calculatePDavidsonIntuicion(respuestasUsuario, context)
            "preguntas_davidson_autoconciencia" -> PDavidson.calculatePDavidsonAutoconciencia(respuestasUsuario, context)
            "preguntas_davidson_contexto" -> PDavidson.calculatePDavidsonContexto(respuestasUsuario, context)
            "preguntas_davidson_atencion" -> PDavidson.calculatePDavidsonAtencion(respuestasUsuario, context)

            else -> mapOf()
        }
    }
}