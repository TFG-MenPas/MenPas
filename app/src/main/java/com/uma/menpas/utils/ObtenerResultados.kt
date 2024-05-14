package com.uma.menpas.utils

import com.uma.menpas.cuestionarios.*

class ObtenerResultados {

    fun obtenerResultados(jsonResourceName: String, calculosCuestionario: Map<String, String>): Map<String, String> {
        return when (jsonResourceName) {
            //Ansiedad
            "preguntas_csai2" -> obtenerResultadosCSAI2(calculosCuestionario)
            "preguntas_csai2_cognitiva" -> calculateCSAI2Cognitiva(calculosCuestionario)
            "preguntas_csai2_autoconfianza" -> calculateCSAI2Autoconfianza(calculosCuestionario)
            "preguntas_csai2_somatica" -> calculateCSAI2Somatica(calculosCuestionario)
            "preguntas_scat" -> obtenerResultadosSCAT(calculosCuestionario)
            "preguntas_stai_ar" -> obtenerResultadosSTAIAR(calculosCuestionario)
            "preguntas_stai_ae" -> obtenerResultadosSTAIAE(calculosCuestionario)
            
            //Búsqueda de talentos
            "preguntas_acsi_28" -> obtenerResultadosACSI28(calculosCuestionario)
            "preguntas_embu" -> obtenerResultadosEMBU(calculosCuestionario)
            "preguntas_eacs" -> obtenerResultadosEACS(calculosCuestionario)
            "preguntas_ipseta" -> obtenerResultadosIPSETA(calculosCuestionario)
            "preguntas_mps" -> obtenerResultadosMPS(calculosCuestionario)
            "preguntas_rs" -> obtenerResultadosRS (calculosCuestionario)
            
            //Burnout
            "preguntas_maslach" -> obtenerResultadosMaslach(calculosCuestionario)
            "preguntas_abq" -> obtenerResultadosABQ(calculosCuestionario)
            
            //Autoconcepto
            "preguntas_af5" -> obtenerResultadosAF5(calculosCuestionario)
            "preguntas_bsq" -> obtenerResultadosBSQ(calculosCuestionario)
            "preguntas_caf" -> obtenerResultadosCAF(calculosCuestionario)
            
            //Calidad de vida
            "preguntas_sf_36" -> obtenerResultadosSF36(calculosCuestionario)
            "preguntas_sf_12" -> obtenerResultadosSF12(calculosCuestionario)
            "preguntas_vitalidad_subjetiva" -> obtenerResultadosVS(calculosCuestionario)
            
            //Dinámica grupal
            "preguntas_dinamica_grupal_ccd" -> obtenerResultadosDinamicaGrupalCCD(calculosCuestionario)
            
            //Autorregistros
            "preguntas_autorregistro_comida" -> obtenerResultadosAutorregistroComida(calculosCuestionario)
            "preguntas_autorregistro_diario" -> obtenerResultadosAutorregistroDiario(calculosCuestionario)
            "preguntas_autorregistro_entrenamiento" -> obtenerResultadosAutorregistroEntrenamiento(calculosCuestionario)
            "preguntas_autorregistro_libre" -> obtenerResultadosAutorregistroLibre(calculosCuestionario)
            "preguntas_autorregistro_pensamientos_negativos" -> obtenerResultadosAutorregistroPensamientosNegativos(calculosCuestionario)

            //Atencion
            "cuestionario_stroop" -> obtenerResultadosStroop(calculosCuestionario)
            "cuestionario_modrian_fotos" -> obtenerResultadosModrianFotos(calculosCuestionario)
            "cuestionario_modrian_stroop" -> obtenerResultadosModrianStroop(calculosCuestionario)
            "cuestionario_modrian_colores" -> obtenerResultadosModrianColores(calculosCuestionario)
            "cuestionario_modrian_parejas" -> obtenerResultadosModrianParejas(calculosCuestionario)
            "cuestionario_modrian_simon" -> obtenerResultadosModrianSimon(calculosCuestionario)
            "cuestionario_d2" -> obtenerResultadosD2(calculosCuestionario)
            
            //E.Mental/Evaluacion
            "preguntas_evaluacion_mental_iped" -> obtenerResultadosIPED(calculosCuestionario)
            "preguntas_entrenamiento_mental_epi" -> obtenerResultadosEPI(calculosCuestionario)
            "preguntas_poms_65" -> obtenerResultadosPOMS65(calculosCuestionario)
            "preguntas_poms_58" -> obtenerResultadosPOMS58(calculosCuestionario)
            "preguntas_poms_15" -> obtenerResultadosPOMS15(calculosCuestionario)
            "preguntas_poms_6" -> obtenerResultadosPOMS6(calculosCuestionario)
            "preguntas_poms_angustia_colera" -> obtenerResultadosPOMSAngustiaColera(calculosCuestionario)
            "preguntas_poms_confusion_orientacion" -> obtenerResultadosPOMSConfusionOrientacion(calculosCuestionario)
            "preguntas_poms_depresion" -> obtenerResultadosPOMSDepresion(calculosCuestionario)
            "preguntas_poms_fatiga_inercia" -> obtenerResultadosPOMSFatigaInercia(calculosCuestionario)
            "preguntas_poms_tension_ansiedad" -> obtenerResultadosPOMSTensionAnsiedad(calculosCuestionario)
            "preguntas_poms_vigor_activacion" -> obtenerResultadosPOMSVigorActivacion(calculosCuestionario)
            
            //Inteligencia emocional
            "preguntas_davidson_completo" -> obtenerResultadosDavidsonCompleto(calculosCuestionario)
            "preguntas_davidson_resistencia" -> obtenerResultadosDavidsonResistencia(calculosCuestionario)
            "preguntas_davidson_actitud" -> obtenerResultadosDavidsonActitud(calculosCuestionario)
            "preguntas_davidson_intuicion_social" -> obtenerResultadosDavidsonIntuicionSocial(calculosCuestionario)
            "preguntas_davidson_autoconciencia" -> obtenerResultadosDavidsonAutoconciencia(calculosCuestionario)
            "preguntas_davidson_contexto" -> obtenerResultadosDavidsonContexto(calculosCuestionario)
            "preguntas_davidson_atencion" -> obtenerResultadosDavidsonAtencion(calculosCuestionario)
            
            else -> obtenerResultadosMPS(calculosCuestionario)
        }
    }

    private fun obtenerResultadosModrianSimon(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Nivel:"] = calculosCuestionario["Numero_Aciertos"] ?: ""
        resultadosMostrados["Tiempo:"] = calculosCuestionario["Tiempo_Final"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Modrian simon"
        resultadosMostrados["categoria"] = "Atención"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosModrianParejas(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Aciertos:"] = calculosCuestionario["Numero_Aciertos"] ?: ""
        resultadosMostrados["Fallos:"] = calculosCuestionario["Numero_Fallos"] ?: ""
        resultadosMostrados["Blancos:"] = calculosCuestionario["Numero_Blancos"] ?: ""
        resultadosMostrados["Tiempo:"] = calculosCuestionario["Tiempo_Final"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Modrian parejas"
        resultadosMostrados["categoria"] = "Atención"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosModrianColores(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Aciertos:"] = calculosCuestionario["Numero_Aciertos"] ?: ""
        resultadosMostrados["Fallos:"] = calculosCuestionario["Numero_Fallos"] ?: ""
        resultadosMostrados["Blancos:"] = calculosCuestionario["Numero_Blancos"] ?: ""
        resultadosMostrados["Tiempo:"] = calculosCuestionario["Tiempo_Final"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Modrian colores"
        resultadosMostrados["categoria"] = "Atención"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosModrianStroop(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Aciertos:"] = calculosCuestionario["Numero_Aciertos"] ?: ""
        resultadosMostrados["Fallos:"] = calculosCuestionario["Numero_Fallos"] ?: ""
        resultadosMostrados["Blancos:"] = calculosCuestionario["Numero_Blancos"] ?: ""
        resultadosMostrados["Tiempo:"] = calculosCuestionario["Tiempo_Final"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Modrian stroop"
        resultadosMostrados["categoria"] = "Atención"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosModrianFotos(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Aciertos:"] = calculosCuestionario["Numero_Aciertos"] ?: ""
        resultadosMostrados["Fallos:"] = calculosCuestionario["Numero_Fallos"] ?: ""
        resultadosMostrados["Blancos:"] = calculosCuestionario["Numero_Blancos"] ?: ""
        resultadosMostrados["Tiempo:"] = calculosCuestionario["Tiempo_Final"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Modrian fotos"
        resultadosMostrados["categoria"] = "Atención"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosStroop(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        val (horas, minutos, segundos) = segundosAHorasMinutosSegundos(calculosCuestionario["T_total"]?.toInt() ?: 0)

        resultadosMostrados["Aciertos:"] = calculosCuestionario["Aciertos"] ?: ""
        resultadosMostrados["Fallos:"] = calculosCuestionario["Fallos"] ?: ""
        resultadosMostrados["Tiempo:"] = horas.toString() + " h, " + minutos.toString() + " m, " + segundos.toString() + " s"
        resultadosMostrados["Tiempo medio:"] = calculosCuestionario["T_medio"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Efecto Stroop"
        resultadosMostrados["categoria"] = "Atención"
        return resultadosMostrados.toMap()
    }

    fun segundosAHorasMinutosSegundos(segundos: Int): Triple<Int, Int, Int> {
        val horas = segundos / 3600
        val minutos = (segundos % 3600) / 60
        val segundosRestantes = segundos % 60
        return Triple(horas, minutos, segundosRestantes)
    }

    private fun obtenerResultadosAutorregistroPensamientosNegativos(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Día:"] = calculosCuestionario["Dia"] ?: ""
        resultadosMostrados["Hora:"] = calculosCuestionario["Hora"] ?: "" + " horas"
        resultadosMostrados["Ciudad, país, recinto deportivo..."] = calculosCuestionario["Lugar"] ?: ""
        resultadosMostrados["Situación y conducta previa:"] = calculosCuestionario["Conducta_Previa"] ?: ""
        resultadosMostrados["Pensamientos/verbalizaciones negativas:"] = calculosCuestionario["Pensamiento_Negativo"] ?: ""
        resultadosMostrados["Intensidad pensamientos/verbalizaciones:"] = calculosCuestionario["Intensidad"] ?: ""
        resultadosMostrados["Situación y conducta posterior:"] = calculosCuestionario["Conducta_Posterior"] ?: ""
        resultadosMostrados["El pensamiento positivo debe ser..."] = calculosCuestionario["Pensamiento_Positivo"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Autorregistro pensamientos negativos y verbalizaciones"
        resultadosMostrados["categoria"] = "Autoregistros"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosAutorregistroLibre(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Cuestión 1:"] = calculosCuestionario["campo1"] ?: ""
        resultadosMostrados["Cuestión 2:"] = calculosCuestionario["campo2"] ?: ""
        resultadosMostrados["Cuestión 3:"] = calculosCuestionario["campo3"] ?: ""
        resultadosMostrados["Cuestión 4:"] = calculosCuestionario["campo4"] ?: ""
        resultadosMostrados["Cuestión 5:"] = calculosCuestionario["campo5"] ?: ""
        resultadosMostrados["Cuestión 6:"] = calculosCuestionario["campo6"] ?: ""
        resultadosMostrados["Cuestión 7:"] = calculosCuestionario["campo7"] ?: ""
        resultadosMostrados["Cuestión 8:"] = calculosCuestionario["campo8"] ?: ""
        resultadosMostrados["Cuestión 9:"] = calculosCuestionario["campo9"] ?: ""
        resultadosMostrados["Cuestión 10:"] = calculosCuestionario["campo10"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Autorregistro libre"
        resultadosMostrados["categoria"] = "Autoregistros"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosAutorregistroEntrenamiento(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        resultadosMostrados["Día entrenamiento:"] = calculosCuestionario["Dia"] ?: ""
        resultadosMostrados["Objetivo general:"] = calculosCuestionario["Ob_general"] ?: ""
        resultadosMostrados["Tareas:"] = calculosCuestionario["Tarea"] ?: ""
        resultadosMostrados["Deporte practicado:"] = calculosCuestionario["Deporte"] ?: ""
        resultadosMostrados["Metas:"] = calculosCuestionario["Objetivo"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Adecuación autovalorativa del entrenamiento"
        resultadosMostrados["categoria"] = "Autoregistros"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosAutorregistroDiario(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        val imc = calcularIMC(calculosCuestionario["Peso"]?.replace("'", "")?.toDouble() ?: 0.0,
            calculosCuestionario["Estatura"]?.replace("'", "")?.toDouble() ?: 0.0)

        resultadosMostrados["Día:"] = calculosCuestionario["Dia"] ?: ""
        resultadosMostrados["Peso (kg):"] = calculosCuestionario["Peso"] ?: ""
        resultadosMostrados["Horas de sueño:"] = calculosCuestionario["HorasSueño"] ?: ""
        resultadosMostrados["Pulsaciones al despertar:"] = calculosCuestionario["Pulsaciones"] ?: ""
        resultadosMostrados["Estado de ánimo:"] = calculosCuestionario["Animo"] ?: ""
        resultadosMostrados["Tensión mínima:"] = calculosCuestionario["T_Minima"] ?: ""
        resultadosMostrados["Tensión máxima:"] = calculosCuestionario["T_Maxima"] ?: ""
        resultadosMostrados["Tiempo práctica deportiva:"] = calculosCuestionario["T_P_Deportiva"] ?: ""
        resultadosMostrados["Contenido de la práctica:"] = calculosCuestionario["Contenido_Prac"] ?: ""
        resultadosMostrados["Percepción del esfuerzo:"] = calculosCuestionario["P_Esfuerzo"] ?: ""
        resultadosMostrados["Cantidad de ejercicio:"] = calculosCuestionario["C_Ejercicio"] ?: ""
        resultadosMostrados["Intensidad del ejercicio:"] = calculosCuestionario["I_Ejercicio"] ?: ""
        resultadosMostrados["Eventos destacados del día:"] = calculosCuestionario["EventoDestacado"] ?: ""
        resultadosMostrados["Estatura (m):"] = calculosCuestionario["Estatura"] ?: ""
        resultadosMostrados["IMC:"] = imc.toString()
        resultadosMostrados["nombreCuestionario"] = "Autorregistro diario"
        resultadosMostrados["categoria"] = "Autoregistros"
        return resultadosMostrados.toMap()
    }

    private fun calcularIMC(peso: Double, estatura: Double): Double {
        return peso / (estatura * estatura)
    }

    private fun obtenerResultadosPOMSVigorActivacion(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Vigor-Activación:"] = calculosCuestionario["Vigor_Activacion"] ?: ""
        resultadosMostrados["Vigor-Activación (T score):"] = calculosCuestionario["TS_Vigor_Activacion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms (Escala Vigor-Activación)"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMSTensionAnsiedad(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Tensión-Ansiedad:"] = calculosCuestionario["Tension_Ansiedad"] ?: ""
        resultadosMostrados["Tensión-Ansiedad (T score):"] = calculosCuestionario["TS_Tension_Ansiedad"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms (Escala Tensión-Ansiedad)"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMSFatigaInercia(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Fatiga-Inercia:"] = calculosCuestionario["Fatiga_Inercia"] ?: ""
        resultadosMostrados["Fatiga-Inercia (T score):"] = calculosCuestionario["TS_Fatiga_Inercia"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms (Escala Fatiga-Inercia)"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMSDepresion(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Depresion-Melancolía:"] = calculosCuestionario["Depresion_Melancolia"] ?: ""
        resultadosMostrados["Depresion-Melancolía (T score):"] = calculosCuestionario["TS_Depresion_Melancolia"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms (Escala Depresion-Melancolía)"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMSConfusionOrientacion(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Confusión-Orientación:"] = calculosCuestionario["Confusion_orientacion"] ?: ""
        resultadosMostrados["Confusión-Orientación (T score):"] = calculosCuestionario["TS_Confusion_orientacion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms (Escala Confusión-Orientación)"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMSAngustiaColera(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Angustia-Hostilidad-Cólera:"] = calculosCuestionario["Angustia_Colera"] ?: ""
        resultadosMostrados["Angustia-Hostilidad-Cólera (T score):"] = calculosCuestionario["TS_Angustia_Colera"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms (Escala Angustia-Cólera)"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMS6(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Escala 6 items:"] = calculosCuestionario["Escala6"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms 6 items"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMS15(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Vigor-Activación:"] = calculosCuestionario["Vigor_Activacion"] ?: ""
        resultadosMostrados["Vigor-Activación (T score):"] = calculosCuestionario["TS_Vigor_Activacion"] ?: ""
        resultadosMostrados["Fatiga-Inercia:"] = calculosCuestionario["Fatiga_Inercia"] ?: ""
        resultadosMostrados["Fatiga-Inercia (T score):"] = calculosCuestionario["TS_Fatiga_Inercia"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms 15 items"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMS58(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Tensión-Ansiedad:"] = calculosCuestionario["Tension_Ansiedad"] ?: ""
        resultadosMostrados["Tensión-Ansiedad (T score):"] = calculosCuestionario["TS_Tension_Ansiedad"] ?: ""
        resultadosMostrados["Depresion-Melancolía:"] = calculosCuestionario["Depresion_Melancolia"] ?: ""
        resultadosMostrados["Depresion-Melancolía (T score):"] = calculosCuestionario["TS_Depresion_Melancolia"] ?: ""
        resultadosMostrados["Angustia-Hostilidad-Cólera:"] = calculosCuestionario["Angustia_Colera"] ?: ""
        resultadosMostrados["Angustia-Hostilidad-Cólera (T score):"] = calculosCuestionario["TS_Angustia_Colera"] ?: ""
        resultadosMostrados["Vigor-Activación:"] = calculosCuestionario["Vigor_Activacion"] ?: ""
        resultadosMostrados["Vigor-Activación (T score):"] = calculosCuestionario["TS_Vigor_Activacion"] ?: ""
        resultadosMostrados["Fatiga-Inercia:"] = calculosCuestionario["Fatiga_Inercia"] ?: ""
        resultadosMostrados["Fatiga-Inercia (T score):"] = calculosCuestionario["TS_Fatiga_Inercia"] ?: ""
        resultadosMostrados["Confusión-Orientación:"] = calculosCuestionario["Confusion_orientacion"] ?: ""
        resultadosMostrados["Confusión-Orientación (T score):"] = calculosCuestionario["TS_Confusion_orientacion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms 58 items"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosPOMS65(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Tensión-Ansiedad:"] = calculosCuestionario["Tension_Ansiedad"] ?: ""
        resultadosMostrados["Tensión-Ansiedad (T score):"] = calculosCuestionario["TS_Tension_Ansiedad"] ?: ""
        resultadosMostrados["Depresion-Melancolía:"] = calculosCuestionario["Depresion_Melancolia"] ?: ""
        resultadosMostrados["Depresion-Melancolía (T score):"] = calculosCuestionario["TS_Depresion_Melancolia"] ?: ""
        resultadosMostrados["Angustia-Hostilidad-Cólera:"] = calculosCuestionario["Angustia_Colera"] ?: ""
        resultadosMostrados["Angustia-Hostilidad-Cólera (T score):"] = calculosCuestionario["TS_Angustia_Colera"] ?: ""
        resultadosMostrados["Vigor-Activación:"] = calculosCuestionario["Vigor_Activacion"] ?: ""
        resultadosMostrados["Vigor-Activación (T score):"] = calculosCuestionario["TS_Vigor_Activacion"] ?: ""
        resultadosMostrados["Fatiga-Inercia:"] = calculosCuestionario["Fatiga_Inercia"] ?: ""
        resultadosMostrados["Fatiga-Inercia (T score):"] = calculosCuestionario["TS_Fatiga_Inercia"] ?: ""
        resultadosMostrados["Confusión-Orientación:"] = calculosCuestionario["Confusion_orientacion"] ?: ""
        resultadosMostrados["Confusión-Orientación (T score):"] = calculosCuestionario["TS_Confusion_orientacion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Poms 65 items"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonAtencion(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Atención:"] = calculosCuestionario["atencion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Davidson (Atención)"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonContexto(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Contexto:"] = calculosCuestionario["contexto"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Davidson (Contexto)"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonAutoconciencia(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Autoconciencia:"] = calculosCuestionario["autoconciencia"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Davidson (Autoconciencia)"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonIntuicionSocial(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Intuición social:"] = calculosCuestionario["intuicion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Davidson (Intuición social)"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonActitud(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Actitud:"] = calculosCuestionario["actitud"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Davidson (Actitud)"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonResistencia(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Resistencia:"] = calculosCuestionario["resistencia"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Davidson (Resistencia)"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDavidsonCompleto(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Resistencia:"] = calculosCuestionario["resistencia"] ?: ""
        resultadosMostrados["Actitud:"] = calculosCuestionario["actitud"] ?: ""
        resultadosMostrados["Intuición social:"] = calculosCuestionario["intuicion"] ?: ""
        resultadosMostrados["Autoconciencia:"] = calculosCuestionario["autoconciencia"] ?: ""
        resultadosMostrados["Contexto:"] = calculosCuestionario["contexto"] ?: ""
        resultadosMostrados["Atención:"] = calculosCuestionario["atencion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Evaluación del perfil emocional de Davidson"
        resultadosMostrados["categoria"] = "Inteligencia emocional"
        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosD2(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["TR:"] = calculosCuestionario["TR"] ?: ""
        resultadosMostrados["TA:"] = calculosCuestionario["TA"] ?: ""
        resultadosMostrados["O:"] = calculosCuestionario["O"] ?: ""
        resultadosMostrados["C:"] = calculosCuestionario["C"] ?: ""
        resultadosMostrados["TOT:"] = calculosCuestionario["TOT"] ?: ""
        resultadosMostrados["CON:"] = calculosCuestionario["CON"] ?: ""
        resultadosMostrados["TR+:"] = calculosCuestionario["TRmax"] ?: ""
        resultadosMostrados["TR-:"] = calculosCuestionario["TRmin"] ?: ""
        resultadosMostrados["VAR:"] = calculosCuestionario["VAR"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Test de atención d2"
        resultadosMostrados["categoria"] = "Atención"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosEPI(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución(S):"] = calculosCuestionario["V_S"] ?: ""
        resultadosMostrados["Solución(N):"] = calculosCuestionario["V_N"] ?: ""
        resultadosMostrados["Solución(E):"] = calculosCuestionario["V_E"] ?: ""
        resultadosMostrados["Centil(S):"] = calculosCuestionario["Centil_S"] ?: ""
        resultadosMostrados["Centil(N):"] = calculosCuestionario["Centil_N"] ?: ""
        resultadosMostrados["Centil(E):"] = calculosCuestionario["Centil_E"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Personalidad EPI"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosIPED(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()

        val resultadosNumericos = arrayOf(
            calculosCuestionario["AC"]?.replace("'", "")?.toInt() ?: 0,calculosCuestionario["CAN"]?.replace("'", "")?.toInt() ?: 0,
            calculosCuestionario["CAT"]?.replace("'", "")?.toInt() ?: 0, calculosCuestionario["CVI"]?.replace("'", "")?.toInt() ?: 0,
            calculosCuestionario["NM"]?.replace("'", "")?.toInt() ?: 0, calculosCuestionario["CAP"]?.replace("'", "")?.toInt() ?: 0,
            calculosCuestionario["CACT"]?.replace("'", "")?.toInt() ?: 0)
        val durezaMental = resultadosNumericos.average().toInt()

        resultadosMostrados["AC(autoconfianza):"] = calculosCuestionario["AC"] ?: ""
        resultadosMostrados["CAN(control de afrontamiento negativo):"] = calculosCuestionario["CAN"] ?: ""
        resultadosMostrados["CAT(control atencional):"] = calculosCuestionario["CAT"] ?: ""
        resultadosMostrados["CVI(control visuoimaginativo):"] = calculosCuestionario["CVI"] ?: ""
        resultadosMostrados["NM(nivel motivación):"] = calculosCuestionario["NM"] ?: ""
        resultadosMostrados["CAP(control de afrontamiento positivo):"] = calculosCuestionario["CAP"] ?: ""
        resultadosMostrados["CACT(control actitudinal):"] = calculosCuestionario["CACT"] ?: ""
        resultadosMostrados["Dureza mental:"] = durezaMental.toString()
        resultadosMostrados["nombreCuestionario"] = "IPED"
        resultadosMostrados["categoria"] = "E.Mental/Evaluación"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosDinamicaGrupalCCD(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["DCC:"] = calculosCuestionario["DCC"] ?: ""
        resultadosMostrados["DCI:"] = calculosCuestionario["DCI"] ?: ""
        resultadosMostrados["SC:"] = calculosCuestionario["SC"] ?: ""
        resultadosMostrados["SE:"] = calculosCuestionario["SE"] ?: ""
        resultadosMostrados["SF:"] = calculosCuestionario["SF"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Cooperación deportiva (CCD)"
        resultadosMostrados["categoria"] = "Dinámica grupal"

        return resultadosMostrados.toMap()
    }

    private fun calculateCSAI2Somatica(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Somática:"] = calculosCuestionario["Somatica"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CSAI2 (Somática)"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

    private fun calculateCSAI2Autoconfianza(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Autoconfianza:"] = calculosCuestionario["Autoconfianza"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CSAI2 (Autoconfianza)"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

    private fun calculateCSAI2Cognitiva(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Cognitiva:"] = calculosCuestionario["Cognitiva"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CSAI2 (Cognitiva)"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSTAIAE(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución:"] = calculosCuestionario["Stai_A_E"] ?: ""
        resultadosMostrados["Centiles:"] = calculosCuestionario["Centiles"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "STAI A-E"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSTAIAR(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución:"] = calculosCuestionario["Stai_A_R"] ?: ""
        resultadosMostrados["Centiles:"] = calculosCuestionario["Centiles"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "STAI A-R"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosAutorregistroComida(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Hora del día:"] = calculosCuestionario["Hora"]+" horas" ?: ""
        resultadosMostrados["Tiempo empleado en comer:"] = calculosCuestionario["Tiempo"]+" minutos" ?: ""
        resultadosMostrados["Cantidad de hambre (1 poca ... 10 mucha):"] = calculosCuestionario["Cantidad"] ?: ""
        resultadosMostrados["Actividad realizada mientras come:"] = calculosCuestionario["Actividad"] ?: ""
        resultadosMostrados["Lugar donde se come:"] = calculosCuestionario["LugarComida"] ?: ""
        resultadosMostrados["Comida y bebida (tipo y cantidad):"] = calculosCuestionario["Comida_tipo"] ?: ""
        resultadosMostrados["Comida y bebida (calorías apróx.):"] = calculosCuestionario["Calorias"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Autoregistro comida y bebida"
        resultadosMostrados["categoria"] = "Autoregistros"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosVS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución:"] = calculosCuestionario["sol"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Vitalidad subjetiva (VS)"
        resultadosMostrados["categoria"] = "Calidad de vida"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSF12(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Medida sumatorio física:"] = calculosCuestionario["Medida_S_Físico"] ?: ""
        resultadosMostrados["Medida sumatorio mental:"] = calculosCuestionario["Medida_S_Mental"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SF12"
        resultadosMostrados["categoria"] = "Calidad de vida"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSF36(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Función física:"] = calculosCuestionario["F_Física"] ?: ""
        resultadosMostrados["Rol físico:"] = calculosCuestionario["Rol_Físico"] ?: ""
        resultadosMostrados["Dolor corporal:"] = calculosCuestionario["Dolor"] ?: ""
        resultadosMostrados["Salud general:"] = calculosCuestionario["Salud"] ?: ""
        resultadosMostrados["Vitalidad:"] = calculosCuestionario["Vitalidad"] ?: ""
        resultadosMostrados["Función social:"] = calculosCuestionario["FunciónS"] ?: ""
        resultadosMostrados["Rol emocional:"] = calculosCuestionario["Rol_Emocional"] ?: ""
        resultadosMostrados["Salud mental:"] = calculosCuestionario["Salud_Mental"] ?: ""
        resultadosMostrados["Cambio de salud:"] = calculosCuestionario["Cambio_Salud"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SF36"
        resultadosMostrados["categoria"] = "Calidad de vida"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosCAF(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Habilidad física:"] = calculosCuestionario["Habilidad"] ?: ""
        resultadosMostrados["Condición física:"] = calculosCuestionario["Condición"] ?: ""
        resultadosMostrados["Atractivo físico:"] = calculosCuestionario["Atractivo"] ?: ""
        resultadosMostrados["Fuerza:"] = calculosCuestionario["Fuerza"] ?: ""
        resultadosMostrados["Autoconcepto físico general:"] = calculosCuestionario["AutoconceptoF"] ?: ""
        resultadosMostrados["Autoconcepto general:"] = calculosCuestionario["AutoconceptoG"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CAF"
        resultadosMostrados["categoria"] = "Autoconcepto"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosBSQ(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Factor1:"] = calculosCuestionario["Factor1"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "BSQ"
        resultadosMostrados["categoria"] = "Autoconcepto"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosAF5(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Académico/laboral:"] = calculosCuestionario["Académico"] ?: ""
        resultadosMostrados["Social:"] = calculosCuestionario["Social"] ?: ""
        resultadosMostrados["Emocional:"] = calculosCuestionario["Emocional"] ?: ""
        resultadosMostrados["Familiar:"] = calculosCuestionario["Familiar"] ?: ""
        resultadosMostrados["Físico:"] = calculosCuestionario["Físico"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "AF5"
        resultadosMostrados["categoria"] = "Autoconcepto"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosABQ(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["AFE:"] = calculosCuestionario["AFE"] ?: ""
        resultadosMostrados["RSL:"] = calculosCuestionario["RSL"] ?: ""
        resultadosMostrados["DPD:"] = calculosCuestionario["DPD"] ?: ""
        resultadosMostrados["AFE_T:"] = calculosCuestionario["AFE_T"] ?: ""
        resultadosMostrados["RSL_T:"] = calculosCuestionario["RSL_T"] ?: ""
        resultadosMostrados["DPD_T:"] = calculosCuestionario["DPD_T"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "ABQ"
        resultadosMostrados["categoria"] = "Burnout"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosMaslach(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Escala CE:"] = calculosCuestionario["Escala_CE"] ?: ""
        resultadosMostrados["Escala DP:"] = calculosCuestionario["Escala_DP"] ?: ""
        resultadosMostrados["Escala RP:"] = calculosCuestionario["Escala_RP"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Maslach"
        resultadosMostrados["categoria"] = "Burnout"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosRS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Competencias personales:"] = calculosCuestionario["Factor1"] ?: ""
        resultadosMostrados["Aceptación propia y de la vida:"] = calculosCuestionario["Factor2"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "RS"
        resultadosMostrados["categoria"] = "Búsqueda de talentos"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosMPS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Preocupación por errores:"] = calculosCuestionario["Preocupaciones"] ?: ""
        resultadosMostrados["Normas de carácter personal:"] = calculosCuestionario["Normas"] ?: ""
        resultadosMostrados["Expectativas de padres:"] = calculosCuestionario["Expectativas"] ?: ""
        resultadosMostrados["Críticas de padres:"] = calculosCuestionario["Criticas"] ?: ""
        resultadosMostrados["Dudas sobre acciones:"] = calculosCuestionario["Dudas"] ?: ""
        resultadosMostrados["Organización:"] = calculosCuestionario["Organizacion"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "MPS"
        resultadosMostrados["categoria"] = "Búsqueda de talentos"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosIPSETA(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Motivación intrínseca:"] = calculosCuestionario["M_Intrinseca"] ?: ""
        resultadosMostrados["Motivo de logro:"] = calculosCuestionario["M_Logro"] ?: ""
        resultadosMostrados["Autoeficacia:"] = calculosCuestionario["Auto"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "IPSETA"
        resultadosMostrados["categoria"] = "Búsqueda de talentos"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosEACS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Marginado (estudio/profesión):"] = calculosCuestionario["Factor1_Marginado"] ?: ""
        resultadosMostrados["Aislamiento social:"] = calculosCuestionario["Factor2_Aislamiento"] ?: ""
        resultadosMostrados["Disciplina de vida:"] = calculosCuestionario["Factor3_Disciplina"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "EACS"
        resultadosMostrados["categoria"] = "Búsqueda de talentos"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosEMBU(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Soporte (Padre):"] = calculosCuestionario["SoporteP"] ?: ""
        resultadosMostrados["Soporte (Madre):"] = calculosCuestionario["SoporteM"] ?: ""
        resultadosMostrados["Rechazo (Padre):"] = calculosCuestionario["RechazoP"] ?: ""
        resultadosMostrados["Rechazo (Madre):"] = calculosCuestionario["RechazoM"] ?: ""
        resultadosMostrados["Sobreprotección (Padre):"] = calculosCuestionario["SobreproteccionP"] ?: ""
        resultadosMostrados["Sobreprotección (Madre):"] = calculosCuestionario["SobreproteccionM"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "EMBU"
        resultadosMostrados["categoria"] = "Búsqueda de talentos"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosACSI28(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Rendimiento máximo bajo presión:"] = calculosCuestionario["Rendimiento"] ?: ""
        resultadosMostrados["Ausencia de preocupaciones:"] = calculosCuestionario["Ausencia"] ?: ""
        resultadosMostrados["Confrontación con la adversidad:"] = calculosCuestionario["Confrontacion"] ?: ""
        resultadosMostrados["Concentración:"] = calculosCuestionario["Concentracion"] ?: ""
        resultadosMostrados["Formulación de objetivos:"] = calculosCuestionario["Formulacion"] ?: ""
        resultadosMostrados["Confianza y motivación para llevar a cabo:"] = calculosCuestionario["Confianza"] ?: ""
        resultadosMostrados["Capacidad de formación:"] = calculosCuestionario["Capacidad"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "ACSI28"
        resultadosMostrados["categoria"] = "Búsqueda de talentos"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSCAT(calculosCuestionario: Map<String, String>): Map<String, String>{
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Resultado:"] = calculosCuestionario["Resultado_Scat"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SCAT"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosCSAI2(calculosCuestionario: Map<String, String>): Map<String, String>{
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Cognitiva:"] = calculosCuestionario["Cognitiva"] ?: ""
        resultadosMostrados["Somática:"] = calculosCuestionario["Somatica"] ?: ""
        resultadosMostrados["Autoconfianza:"] = calculosCuestionario["Autoconfianza"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CSAI2"
        resultadosMostrados["categoria"] = "Ansiedad"

        return resultadosMostrados.toMap()
    }

}