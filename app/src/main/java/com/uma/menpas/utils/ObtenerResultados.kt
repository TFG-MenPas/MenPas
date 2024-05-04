package com.uma.menpas.utils

class ObtenerResultados {

    fun obtenerResultados(jsonResourceName: String, calculosCuestionario: Map<String, String>): Map<String, String> {
        return when (jsonResourceName) {
            "preguntas_csai2" -> obtenerResultadosCSAI2(calculosCuestionario)
            "preguntas_scat" -> obtenerResultadosSCAT(calculosCuestionario)
            "preguntas_acsi_28" -> obtenerResultadosACSI28(calculosCuestionario)
            "preguntas_stai_ar" -> obtenerResultadosSTAIAR(calculosCuestionario)
            "preguntas_stai_ae" -> obtenerResultadosSTAIAE(calculosCuestionario)
            "preguntas_embu" -> obtenerResultadosEMBU(calculosCuestionario)
            "preguntas_eacs" -> obtenerResultadosEACS(calculosCuestionario)
            "preguntas_ipseta" -> obtenerResultadosIPSETA(calculosCuestionario)
            "preguntas_mps" -> obtenerResultadosMPS(calculosCuestionario)
            "preguntas_rs" -> obtenerResultadosRS (calculosCuestionario)
            "preguntas_maslach" -> obtenerResultadosMaslach(calculosCuestionario)
            "preguntas_abq" -> obtenerResultadosABQ(calculosCuestionario)
            "preguntas_af5" -> obtenerResultadosAF5(calculosCuestionario)
            "preguntas_bsq" -> obtenerResultadosBSQ(calculosCuestionario)
            "preguntas_caf" -> obtenerResultadosCAF(calculosCuestionario)
            "preguntas_sf_36" -> obtenerResultadosSF36(calculosCuestionario)
            "preguntas_sf_12" -> obtenerResultadosSF12(calculosCuestionario)
            "preguntas_vitalidad_subjetiva" -> obtenerResultadosVS(calculosCuestionario)
            "preguntas_autorregistro_comida" -> obtenerResultadosAutoComida(calculosCuestionario)
            else -> obtenerResultadosSCAT(calculosCuestionario)
        }
    }

    private fun obtenerResultadosSTAIAE(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución:"] = calculosCuestionario["Stai_A_E"] ?: ""
        resultadosMostrados["Centiles:"] = calculosCuestionario["Centiles"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "STAI A-E"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSTAIAR(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución:"] = calculosCuestionario["Stai_A_R"] ?: ""
        resultadosMostrados["Centiles:"] = calculosCuestionario["Centiles"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "STAI A-R"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosAutoComida(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Hora del día:"] = calculosCuestionario["Hora"]+" horas" ?: ""
        resultadosMostrados["Tiempo empleado en comer:"] = calculosCuestionario["Tiempo"]+" minutos" ?: ""
        resultadosMostrados["Cantidad de hambre (1 poca ... 10 mucha):"] = calculosCuestionario["Cantidad"] ?: ""
        resultadosMostrados["Actividad realizada mientras come:"] = calculosCuestionario["Actividad"] ?: ""
        resultadosMostrados["Lugar donde se come:"] = calculosCuestionario["LugarComida"] ?: ""
        resultadosMostrados["Comida y bebida (tipo y cantidad):"] = calculosCuestionario["Comida_tipo"] ?: ""
        resultadosMostrados["Comida y bebida (calorías apróx.):"] = calculosCuestionario["Calorias"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Autoregistro comida y bebida"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosVS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Solución:"] = calculosCuestionario["sol"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Vitalidad subjetiva (VS)"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSF12(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Medida sumatorio física:"] = calculosCuestionario["Medida_S_Fisica"] ?: ""
        resultadosMostrados["Medida sumatorio mental:"] = calculosCuestionario["Medida_S_Mental"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SF12"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSF36(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Función física:"] = calculosCuestionario["F_Fisica"] ?: ""
        resultadosMostrados["Rol físico:"] = calculosCuestionario["Rol_Fisico"] ?: ""
        resultadosMostrados["Dolor corporal:"] = calculosCuestionario["Dolor"] ?: ""
        resultadosMostrados["Salud general:"] = calculosCuestionario["Salud"] ?: ""
        resultadosMostrados["Vitalidad:"] = calculosCuestionario["Vitalidad"] ?: ""
        resultadosMostrados["Función social:"] = calculosCuestionario["FuncionS"] ?: ""
        resultadosMostrados["Rol emocional:"] = calculosCuestionario["Rol_Emocional"] ?: ""
        resultadosMostrados["Salud mental:"] = calculosCuestionario["Salud_Mental"] ?: ""
        resultadosMostrados["Cambio de salud:"] = calculosCuestionario["Cambio_Salud"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SF36"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosCAF(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Habilidad física:"] = calculosCuestionario["Habilidad"] ?: ""
        resultadosMostrados["Condición física:"] = calculosCuestionario["Condicion"] ?: ""
        resultadosMostrados["Atractivo físico:"] = calculosCuestionario["Atractivo"] ?: ""
        resultadosMostrados["Fuerza:"] = calculosCuestionario["Fuerza"] ?: ""
        resultadosMostrados["Autoconcepto físico general:"] = calculosCuestionario["AutoconceptoF"] ?: ""
        resultadosMostrados["Autoconcepto general:"] = calculosCuestionario["AutoconceptoG"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "CAF"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosBSQ(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Factor1:"] = calculosCuestionario["Factor1"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "BSQ"

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

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosMaslach(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Escala CE:"] = calculosCuestionario["Escala_CE"] ?: ""
        resultadosMostrados["Escala DP:"] = calculosCuestionario["Escala_DP"] ?: ""
        resultadosMostrados["Escala RP:"] = calculosCuestionario["Escala_RP"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "Maslach"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosRS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Competencias personales:"] = calculosCuestionario["Factor1"] ?: ""
        resultadosMostrados["Aceptación propia y de la vida:"] = calculosCuestionario["Factor2"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "RS"

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

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosIPSETA(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Motivación intrínseca:"] = calculosCuestionario["M_Intrinseca"] ?: ""
        resultadosMostrados["Motivo de logro:"] = calculosCuestionario["M_Logro"] ?: ""
        resultadosMostrados["Autoeficacia:"] = calculosCuestionario["Auto"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "IPSETA"

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosEACS(calculosCuestionario: Map<String, String>): Map<String, String> {
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Marginado (estudio/profesión):"] = calculosCuestionario["Factor1_Marginado"] ?: ""
        resultadosMostrados["Aislamiento social:"] = calculosCuestionario["Factor2_Aislamiento"] ?: ""
        resultadosMostrados["Disciplina de vida:"] = calculosCuestionario["Factor3_Disciplina"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "EACS"

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

        return resultadosMostrados.toMap()
    }

    private fun obtenerResultadosSCAT(calculosCuestionario: Map<String, String>): Map<String, String>{
        val resultadosMostrados: MutableMap<String,String> = mutableMapOf()
        resultadosMostrados["Resultado:"] = calculosCuestionario["Resultado_Scat"] ?: ""
        resultadosMostrados["nombreCuestionario"] = "SCAT"

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