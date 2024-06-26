package com.uma.menpas.cuestionarios

import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils.Companion.formattedString
import com.uma.menpas.utils.ParseUtils.Companion.obtenerFechaActual

class IPED {
    companion object {
        fun calculateIPED(respuestasUsuario: ArrayList<String>, nombreUsuario: String): Map<String, String> {
            val keys = listOf(
                "ID_IPED",
                "Nombre_Usuario",
                "AC",
                "CAN",
                "CAT",
                "CVI",
                "NM",
                "CAP",
                "CACT",
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
                "Idioma"
            )

            val id = CuestionarioService().obtenerIdDisponible("iped", "ID_IPED")
            val nombreUsuarioFormateado = formattedString(nombreUsuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")

            val listaResultados = mutableListOf<String>()
            val indicesInvertidos = listOf(0, 1, 2, 8, 9, 13, 14, 15, 19, 22, 29, 30, 32, 37)
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) { //Se invierten los valores
                    "Casi siempre" -> "5"
                    "Muchas veces" -> "4"
                    "A veces sí, a veces no" -> "3"
                    "Pocas veces" -> "2"
                    "Casi nunca" -> "1"
                    else -> "0"
                }
                if (index in indicesInvertidos) {
                    val valorInvertido = when (valor) {
                        "5" -> "1"
                        "4" -> "2"
                        "3" -> "3"
                        "2" -> "4"
                        "1" -> "5"
                        else -> "0"
                    }
                    listaResultados.add(valorInvertido)
                }else{
                    listaResultados.add(valor)
                }
            }

            val AC = formattedString((listaResultados[0].toInt() +
                    listaResultados[7].toInt() +
                    listaResultados[14].toInt() +
                    listaResultados[21].toInt() +
                    listaResultados[28].toInt() + listaResultados[35].toInt()).toString())

            val CAN = formattedString((listaResultados[1].toInt() +
                    listaResultados[8].toInt() +
                    listaResultados[15].toInt() +
                    listaResultados[22].toInt() +
                    listaResultados[29].toInt() + listaResultados[36].toInt()).toString())

            val CAT = formattedString((listaResultados[2].toInt() +
                    listaResultados[9].toInt() +
                    listaResultados[16].toInt() +
                    listaResultados[23].toInt() +
                    listaResultados[30].toInt() + listaResultados[37].toInt()).toString())

            val CVI = formattedString((listaResultados[3].toInt() +
                    listaResultados[10].toInt() +
                    listaResultados[17].toInt() +
                    listaResultados[24].toInt() +
                    listaResultados[31].toInt() + listaResultados[38].toInt()).toString())

            val NM = formattedString((listaResultados[4].toInt() +
                    listaResultados[11].toInt() +
                    listaResultados[18].toInt() +
                    listaResultados[25].toInt() +
                    listaResultados[32].toInt() + listaResultados[39].toInt()).toString())

            val CAP = formattedString((listaResultados[5].toInt() +
                    listaResultados[12].toInt() +
                    listaResultados[19].toInt() +
                    listaResultados[26].toInt() +
                    listaResultados[33].toInt() + listaResultados[40].toInt()).toString())

            val CACT = formattedString((listaResultados[6].toInt() +
                    listaResultados[13].toInt() +
                    listaResultados[20].toInt() +
                    listaResultados[27].toInt() +
                    listaResultados[34].toInt() + listaResultados[41].toInt()).toString())

            val values = listOf(
                id, nombreUsuarioFormateado, AC, CAN, CAT, CVI, NM, CAP, CACT, fecha, *listaResultados.toTypedArray(), idioma
            )

            return keys.zip(values).toMap()
        }

        fun calculateIPEDAnonimo(respuestasUsuario: ArrayList<String>, nombreUsuario: String): Map<String, String> {
            val keys = listOf(
                "Id_Iped_A",
                "Nombre_Usuario",
                "AC",
                "CAN",
                "CAT",
                "CVI",
                "NM",
                "CAP",
                "CACT",
                "Fecha",
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
                "Idioma"
            )

            val id = CuestionarioService().obtenerIdDisponible("iped", "ID_IPED")
            val nombreUsuarioFormateado = formattedString(nombreUsuario)
            val fecha = formattedString(obtenerFechaActual())
            val idioma = formattedString("es-es")

            val listaResultados = mutableListOf<String>()
            val indicesInvertidos = listOf(0, 1, 2, 8, 9, 13, 14, 15, 19, 22, 29, 30, 32, 37)
            for ((index, respuesta) in respuestasUsuario.withIndex()) {
                val valor = when (respuesta) { //Se invierten los valores
                    "Casi siempre" -> "5"
                    "Muchas veces" -> "4"
                    "A veces sí, a veces no" -> "3"
                    "Pocas veces" -> "2"
                    "Casi nunca" -> "1"
                    else -> "0"
                }
                if (index in indicesInvertidos) {
                    val valorInvertido = when (valor) {
                        "5" -> "1"
                        "4" -> "2"
                        "3" -> "3"
                        "2" -> "4"
                        "1" -> "5"
                        else -> "0"
                    }
                    listaResultados.add(valorInvertido)
                }else{
                    listaResultados.add(valor)
                }
            }

            val AC = formattedString((listaResultados[0].toInt() +
                    listaResultados[7].toInt() +
                    listaResultados[14].toInt() +
                    listaResultados[21].toInt() +
                    listaResultados[28].toInt() + listaResultados[35].toInt()).toString())

            val CAN = formattedString((listaResultados[1].toInt() +
                    listaResultados[8].toInt() +
                    listaResultados[15].toInt() +
                    listaResultados[22].toInt() +
                    listaResultados[29].toInt() + listaResultados[36].toInt()).toString())

            val CAT = formattedString((listaResultados[2].toInt() +
                    listaResultados[9].toInt() +
                    listaResultados[16].toInt() +
                    listaResultados[23].toInt() +
                    listaResultados[30].toInt() + listaResultados[37].toInt()).toString())

            val CVI = formattedString((listaResultados[3].toInt() +
                    listaResultados[10].toInt() +
                    listaResultados[17].toInt() +
                    listaResultados[24].toInt() +
                    listaResultados[31].toInt() + listaResultados[38].toInt()).toString())

            val NM = formattedString((listaResultados[4].toInt() +
                    listaResultados[11].toInt() +
                    listaResultados[18].toInt() +
                    listaResultados[25].toInt() +
                    listaResultados[32].toInt() + listaResultados[39].toInt()).toString())

            val CAP = formattedString((listaResultados[5].toInt() +
                    listaResultados[12].toInt() +
                    listaResultados[19].toInt() +
                    listaResultados[26].toInt() +
                    listaResultados[33].toInt() + listaResultados[40].toInt()).toString())

            val CACT = formattedString((listaResultados[6].toInt() +
                    listaResultados[13].toInt() +
                    listaResultados[20].toInt() +
                    listaResultados[27].toInt() +
                    listaResultados[34].toInt() + listaResultados[41].toInt()).toString())

            val values = listOf(
                id, nombreUsuarioFormateado, AC, CAN, CAT, CVI, NM, CAP, CACT, fecha, *listaResultados.toTypedArray(), idioma
            )

            return keys.zip(values).toMap()
        }
    }
}