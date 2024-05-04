package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils
import com.uma.menpas.utils.ParseUtils.Companion.formattedString

class POMS {
    companion object {
        fun calculatePOMS65(respuestasUsuario: ArrayList<String>, nombreUsuario: String) {
            val keys = listOf(
                "ID_Poms",
                "Nombre_Usuario",
                "Tension_Ansiedad",
                "Depresion_Melancolia",
                "Angustia_Colera",
                "Vigor_Activacion",
                "Fatiga_Inercia",
                "Confusion_orientacion",
                "TS_Tension_Ansiedad",
                "TS_Depresion_Melancolia",
                "TS_Angustia_Colera",
                "TS_Vigor_Activacion",
                "TS_Fatiga_Inercia",
                "TS_Confusion_orientacion",
                "Fecha",
                "Item1",
                "Item2",
                "Item3",
                "Item4",
                "Item5",
                "Item6",
                "Item7",
                "Item8",
                "Item9",
                "Item10",
                "Item11",
                "Item12",
                "Item13",
                "Item14",
                "Item15",
                "Item16",
                "Item17",
                "Item18",
                "Item19",
                "Item20",
                "Item21",
                "Item22",
                "Item23",
                "Item24",
                "Item25",
                "Item26",
                "Item27",
                "Item28",
                "Item29",
                "Item30",
                "Item31",
                "Item32",
                "Item33",
                "Item34",
                "Item35",
                "Item36",
                "Item37",
                "Item38",
                "Item39",
                "Item40",
                "Item41",
                "Item42",
                "Item43",
                "Item44",
                "Item45",
                "Item46",
                "Item47",
                "Item48",
                "Item49",
                "Item50",
                "Item51",
                "Item52",
                "Item53",
                "Item54",
                "Item55",
                "Item56",
                "Item57",
                "Item58",
                "Item59",
                "Item60",
                "Item61",
                "Item62",
                "Item63",
                "Item64",
                "Item65",
                "Escala6",
                "T_Escala6",
                "Tipo",
                "Idioma",
                "Tiempo"
            )


            val id = CuestionarioService().obtenerIdDisponible("poms", "ID_Poms")
            val nombreUsuarioFormateado = formattedString(nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val listaResultados = mutableListOf<String>()
            for (respuesta in respuestasUsuario) {
                val valor = when (respuesta) {
                    "Casi siempre" -> "5"
                    "Muchas veces" -> "4"
                    "A veces sí, a veces no" -> "3"
                    "Pocas veces" -> "2"
                    "Casi nunca" -> "1"
                    else -> "0"
                }

            }
        }
    }
}