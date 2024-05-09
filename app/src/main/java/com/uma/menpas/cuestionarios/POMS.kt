package com.uma.menpas.cuestionarios

import android.content.Context
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils
import com.uma.menpas.utils.ParseUtils.Companion.formattedString

class POMS {
    companion object {
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

        private val relacionTscoreTensionAnsiedad = mapOf(
            0 to 31,
            1 to 33,
            2 to 34,
            3 to 35,
            4 to 37,
            5 to 38,
            6 to 40,
            7 to 41,
            8 to 42,
            9 to 43,
            10 to 45,
            11 to 47,
            12 to 48,
            13 to 49,
            14 to 51,
            15 to 52,
            16 to 54,
            17 to 55,
            18 to 56,
            19 to 58,
            20 to 59,
            21 to 60,
            22 to 62,
            23 to 63,
            24 to 65,
            25 to 66,
            26 to 67,
            27 to 69,
            28 to 70,
            29 to 72,
            30 to 73,
            31 to 74,
            32 to 76,
            33 to 77,
            34 to 79
        )

        private val relacionTscoreDepresionMelancolia = mapOf(
            0 to 37,
            1 to 38,
            2 to 39,
            3 to 40,
            4 to 41,
            5 to 42,
            6 to 43,
            7 to 44,
            8 to 44,
            9 to 45,
            10 to 46,
            11 to 47,
            12 to 48,
            13 to 49,
            14 to 50,
            15 to 51,
            16 to 52,
            17 to 53,
            18 to 53,
            19 to 54,
            20 to 55,
            21 to 56,
            22 to 57,
            23 to 58,
            24 to 59,
            25 to 60,
            26 to 61,
            27 to 62,
            28 to 63,
            29 to 63,
            30 to 64,
            31 to 65,
            32 to 66,
            33 to 67,
            34 to 68,
            35 to 69,
            36 to 70,
            37 to 71,
            38 to 72,
            39 to 72,
            40 to 73,
            41 to 74,
            42 to 75,
            43 to 76,
            44 to 77,
            45 to 78,
            46 to 79,
            47 to 80,
        )

        private val relactionTscoreAngustiaColera = mapOf(
            0 to 37,
            1 to 39,
            2 to 40,
            3 to 41,
            4 to 42,
            5 to 44,
            6 to 45,
            7 to 47,
            8 to 48,
            9 to 49,
            10 to 51,
            11 to 52,
            12 to 53,
            13 to 54,
            14 to 56,
            15 to 57,
            16 to 58,
            17 to 60,
            18 to 61,
            19 to 62,
            20 to 64,
            21 to 65,
            22 to 66,
            23 to 68,
            24 to 69,
            25 to 70,
            26 to 72,
            27 to 73,
            28 to 74,
            29 to 76,
            30 to 77,
            31 to 78,
            32 to 80,
        )

        private val relacionTscoreVigorActivacion = mapOf(
            0 to 30,
            1 to 30,
            2 to 30,
            3 to 30,
            4 to 32,
            5 to 33,
            6 to 35,
            7 to 37,
            8 to 38,
            9 to 40,
            10 to 41,
            11 to 43,
            12 to 44,
            13 to 46,
            14 to 48,
            15 to 49,
            16 to 51,
            17 to 52,
            18 to 54,
            19 to 55,
            20 to 57,
            21 to 59,
            22 to 60,
            23 to 62,
            24 to 63,
            25 to 65,
            26 to 66,
            27 to 68,
            28 to 70,
            29 to 71,
            30 to 73,
            31 to 74,
            32 to 76,
        )

        private val relacionTscoreFatigaInercia = mapOf(
            0 to 34,
            1 to 35,
            2 to 37,
            3 to 38,
            4 to 40,
            5 to 41,
            6 to 43,
            7 to 45,
            8 to 46,
            9 to 48,
            10 to 49,
            11 to 51,
            12 to 52,
            13 to 54,
            14 to 55,
            15 to 57,
            16 to 58,
            17 to 60,
            18 to 61,
            19 to 63,
            20 to 64,
            21 to 66,
            22 to 67,
            23 to 69,
            24 to 70,
            25 to 72,
            26 to 73,
            27 to 75,
            28 to 77,
        )

        private val relacionTscoreConfusionOrientacion = mapOf(
            0 to 30,
            1 to 32,
            2 to 33,
            3 to 35,
            4 to 37,
            5 to 39,
            6 to 41,
            7 to 43,
            8 to 44,
            9 to 46,
            10 to 48,
            11 to 50,
            12 to 52,
            13 to 53,
            14 to 55,
            15 to 57,
            16 to 59,
            17 to 61,
            18 to 63,
            19 to 64,
            20 to 66,
            21 to 68,
            22 to 70,
            23 to 72,
            24 to 73,
            25 to 75,
            26 to 77,
            27 to 79,
            28 to 80,
        )

        fun calculatePOMS65(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {

            val id = CuestionarioService().obtenerIdDisponible("poms", "ID_Poms")
            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val listaRespuestasNumerica = conversionAEnteros(respuestasUsuario)

            val tensionAnsiedad = sumarLista(listOf(
                listaRespuestasNumerica[1],
                listaRespuestasNumerica[9],
                listaRespuestasNumerica[15],
                listaRespuestasNumerica[19],
                listaRespuestasNumerica[21],
                listaRespuestasNumerica[25],
                listaRespuestasNumerica[26],
                listaRespuestasNumerica[33],
                listaRespuestasNumerica[40]
            ))

            val depresionMelancolia = sumarLista(listOf(
                listaRespuestasNumerica[4],
                listaRespuestasNumerica[8],
                listaRespuestasNumerica[13],
                listaRespuestasNumerica[17],
                listaRespuestasNumerica[20],
                listaRespuestasNumerica[22],
                listaRespuestasNumerica[31],
                listaRespuestasNumerica[34],
                listaRespuestasNumerica[35],
                listaRespuestasNumerica[43],
                listaRespuestasNumerica[44],
                listaRespuestasNumerica[57],
                listaRespuestasNumerica[60],
                listaRespuestasNumerica[61],
            ))

            val angustiaColera = sumarLista(listOf(
                listaRespuestasNumerica[2],
                listaRespuestasNumerica[11],
                listaRespuestasNumerica[16],
                listaRespuestasNumerica[23],
                listaRespuestasNumerica[30],
                listaRespuestasNumerica[32],
                listaRespuestasNumerica[38],
                listaRespuestasNumerica[41],
                listaRespuestasNumerica[46],
                listaRespuestasNumerica[51],
                listaRespuestasNumerica[52],
                listaRespuestasNumerica[56]
            ))


            val vigorActivacion =  sumarLista(listOf(
                listaRespuestasNumerica[6],
                listaRespuestasNumerica[14],
                listaRespuestasNumerica[18],
                listaRespuestasNumerica[37],
                listaRespuestasNumerica[50],
                listaRespuestasNumerica[55],
                listaRespuestasNumerica[59],
                listaRespuestasNumerica[62]
            ))

            val fatigaInercia =
                sumarLista(listOf(
                    listaRespuestasNumerica[3],
                    listaRespuestasNumerica[10],
                    listaRespuestasNumerica[28],
                    listaRespuestasNumerica[39],
                    listaRespuestasNumerica[45],
                    listaRespuestasNumerica[48],
                    listaRespuestasNumerica[64]
                ))

            val confusionOrientacion =
                sumarLista(listOf(
                    listaRespuestasNumerica[7],
                    listaRespuestasNumerica[27],
                    listaRespuestasNumerica[36],
                    listaRespuestasNumerica[49],
                    listaRespuestasNumerica[53],
                    listaRespuestasNumerica[58],
                    listaRespuestasNumerica[63]
                ))

            val tsAngustiaColera = calcularTscore(angustiaColera, relactionTscoreAngustiaColera, false)
            val tsConfusionOrientacion = calcularTscore(confusionOrientacion, relacionTscoreConfusionOrientacion, false)
            val tsDepresionMelancolia = calcularTscore(depresionMelancolia, relacionTscoreDepresionMelancolia, false)
            val tsFatigaInercia = calcularTscore(fatigaInercia, relacionTscoreFatigaInercia, true)
            val tsTensionAnsiedad = calcularTscore(tensionAnsiedad, relacionTscoreTensionAnsiedad, false)
            val tsVigorActivacion = calcularTscore(vigorActivacion, relacionTscoreVigorActivacion, false)


            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                formattedString(tensionAnsiedad.toString()),
                formattedString(depresionMelancolia.toString()),
                formattedString(angustiaColera.toString()),
                formattedString(vigorActivacion.toString()),
                formattedString(fatigaInercia.toString()),
                formattedString(confusionOrientacion.toString()),
                tsTensionAnsiedad,
                tsDepresionMelancolia,
                tsAngustiaColera,
                tsVigorActivacion,
                tsFatigaInercia,
                tsConfusionOrientacion,
                fecha,
                *listaRespuestasNumerica.toTypedArray(),
                formattedString(""), //Escala6
                formattedString(""), //T_Escala6
                formattedString("Poms 65 ítems"), //T_Escala6
                idioma,
                formattedString(""), //Tiempo
            )

            return keys.zip(valoresAInsertar).toMap()
        }

        fun calculatePOMS58(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMS15(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMS6(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMSAngustiaColera(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMSConfusionOrientacion(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMSDepresion(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMSFatigaInercia(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMSTensionAnsiedad(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }

        fun calculatePOMSVigorActivacion(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {


            return mapOf()
        }


        private fun conversionAEnteros(listaRespuestas: List<String>): MutableList<String> {
            val listaResultados = mutableListOf<String>()
            for (respuesta in listaRespuestas) {
                val valor = when (respuesta) {
                    "Nada" -> "0"
                    "Muy Poco" -> "1"
                    "Poco" -> "2"
                    "Mucho" -> "3"
                    "Muchisimo" -> "4"
                    else -> "0"
                }
                listaResultados.add(valor)
            }
            return listaResultados
        }

        private fun sumarLista(listaRespuestasNumerica: List<String>): Int {
            var suma = 0
            listaRespuestasNumerica.forEach {
                suma += it.toInt()
            }
            return suma
        }

        private fun calcularTscore(numero: Int, mapaRelacion: Map<Int, Int>, esFatigaInercia: Boolean): String {
            if(numero < 0){
                return formattedString("30")
            }

            if(numero > mapaRelacion.keys.last()){
                return if(esFatigaInercia){
                    formattedString("78")
                } else {
                    formattedString("80")
                }
            }

            val score = mapaRelacion[numero]

            return formattedString(score!!.toString())

        }

    }
}