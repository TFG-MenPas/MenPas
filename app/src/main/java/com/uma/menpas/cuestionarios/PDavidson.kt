package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils
import com.uma.menpas.utils.ParseUtils.Companion.formattedString

class PDavidson {

    companion object {

        fun calculatePDavidsonCompleto(respuestasUsuario: ArrayList<String>, nombreUsuario: String): Map<String, String> {
            val keys = listOf(
                "Id_PED",
                "Nombre_Usuario",
                "resistencia",
                "actitud",
                "intuicion",
                "autoconciencia",
                "contexto",
                "atencion",
                "n1",
                "n2",
                "n3",
                "n4",
                "n5",
                "n6",
                "n7",
                "n8",
                "n9",
                "n10",
                "n11",
                "n12",
                "n13",
                "n14",
                "n15",
                "n16",
                "n17",
                "n18",
                "n19",
                "n20",
                "n21",
                "n22",
                "n23",
                "n24",
                "n25",
                "n26",
                "n27",
                "n28",
                "n29",
                "n30",
                "n31",
                "n32",
                "n33",
                "n34",
                "n35",
                "n36",
                "n37",
                "n38",
                "n39",
                "n40",
                "n41",
                "n42",
                "n43",
                "n44",
                "n45",
                "n46",
                "n47",
                "n48",
                "n49",
                "n50",
                "n51",
                "n52",
                "n53",
                "n54",
                "n55",
                "n56",
                "n57",
                "n58",
                "n59",
                "n60",
                "Tipo",
                "T1",
                "T2",
                "T3",
                "T4",
                "T5",
                "T6",
                "T7",
                "T8",
                "T9",
                "T10",
                "T11",
                "T12",
                "T13",
                "T14",
                "T15",
                "T16",
                "T17",
                "T18",
                "T19",
                "T20",
                "T21",
                "T22",
                "T23",
                "T24",
                "T25",
                "T26",
                "T27",
                "T28",
                "T29",
                "T30",
                "T31",
                "T32",
                "T33",
                "T34",
                "T35",
                "T36",
                "T37",
                "T38",
                "T39",
                "T40",
                "T41",
                "T42",
                "T43",
                "T44",
                "T45",
                "T46",
                "T47",
                "T48",
                "T49",
                "T50",
                "T51",
                "T52",
                "T53",
                "T54",
                "T55",
                "T56",
                "T57",
                "T58",
                "T59",
                "T60",
                "Tiempo",
                "Idioma",
                "Fecha",
                "BIz",
                "BDr",
                "BD",
                "Resol",
                "Teclas",
                "Orden",
                "Edad",
                "Genero",
                "Deporte_Practicado",
                "Grupo",
                "Nacionalidad",
                "Estado_Civil",
                "HorasSemanales",
                "Estudios",
                "Profesion",
                "TDeporte",
                "TipoUser"
            )

            val id = CuestionarioService().obtenerIdDisponible("poms", "ID_Poms")
            val nombreUsuarioFormateado = formattedString(nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultadoResistencia = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 9)))
            val resultadoActitud = formattedString(procesarRespuestas(respuestasTransformadas.subList(10, 19)))
            val resultadoIntuicion = formattedString(procesarRespuestas(respuestasTransformadas.subList(20, 29)))
            val resultadoAutoconciencia = formattedString(procesarRespuestas(respuestasTransformadas.subList(30, 39)))
            val resultadoContexto = formattedString(procesarRespuestas(respuestasTransformadas.subList(40, 49)))
            val resultadoAtencion = formattedString(procesarRespuestas(respuestasTransformadas.subList(50, 59)))

            val blz = null
            val bdr = null
            val bd = null
            val valorCentilS = null
            val valorCentilN = null
            val valorCentilE = null
            val respuestasUsuarioString = null
            val valoresAInsertar = listOf(
                id,
                nombreUsuario,
                resultadoResistencia,
                resultadoActitud,
                resultadoIntuicion,
                resultadoAutoconciencia,
                resultadoContexto,
                resultadoAtencion,
                *respuestasTransformadas.toTypedArray(),
                formattedString("PED_completo"),
                List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                blz,
                bdr,
                bd,
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                

                valorCentilS,
                valorCentilN,
                valorCentilE,

                respuestasUsuarioString,

                ParseUtils.formattedString("0")
            )

            return mapOf()
        }

        private fun procesarRespuestas(respuestasTransformadas: List<String>): String {
            var contadorSies = 0
            respuestasTransformadas.forEach {
                if (it == "1") {
                    contadorSies++
                }
            }

            return contadorSies.toString()
        }

        private fun transformarRespuestas(respuestasUsuario: ArrayList<String>): List<String> {
            val respuestasTransformadas = respuestasUsuario.map {
                if(it.contentEquals("SI", ignoreCase = true)){
                    "1"
                } else {
                    "0"
                }
            }
            return respuestasTransformadas
        }
    }
}