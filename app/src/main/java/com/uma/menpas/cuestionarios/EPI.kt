package com.uma.menpas.cuestionarios

import android.content.Context
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.ParseUtils
import com.uma.menpas.utils.ParseUtils.Companion.formattedString

class EPI {

    companion object {
        fun calculateEPI(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {
            val keys = listOf(
                "ID_EPI",
                "Nombre_Usuario",
                "V_S",
                "V_N",
                "V_E",
                "Centil_S",
                "Centil_N",
                "Centil_E",
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
                "Idioma",
                "Tiempo"
            )

            val usuario = UsuarioController().getUsuario(context)

            val id = CuestionarioService().obtenerIdDisponible("epi", "ID_EPI")
            val nombreUsuario = formattedString(usuario?.nombreUsuario ?: "anónimo")
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val listaParaCalculoNoes = listOf(1, 3, 6, 8, 10, 13,
                15, 18, 20, 22, 25, 27,
                30, 32, 34, 37, 39, 42,
                44, 46, 49, 51, 54, 56)

            val listaParaCalculoE = listOf(0, 2, 7, 9, 12, 16,
                21, 24, 26, 28, 30, 35,
                37, 40, 44, 49, 52, 54,
                4, 14, 19, 32, 42, 47)

            val listaParaCalculoSies = listOf(5, 11, 11, 17, 23, 29, 35, 41, 47, 53)

            val contadorSies = listaParaCalculoSies.sumOf { indice ->
                when (indice) {
                    5, 17, 23, 29, 35, 41, 47, 53 -> valorarSies(respuestasUsuario[indice])
                    11 -> valorarNoes(respuestasUsuario[indice])
                    else -> -1
                }
            }
            val contadorNoes = listaParaCalculoNoes.sumOf { indice ->
                valorarSies(respuestasUsuario[indice])
            }

            val contadorE = listaParaCalculoE.sumOf { indice ->
                when (indice) {
                    4, 14, 19, 32, 42, 47 -> valorarNoes(respuestasUsuario[indice])
                    else -> valorarSies(respuestasUsuario[indice])
                }
            }
            val valorCentilS = formattedString(calcularCentilesS(contadorSies, usuario?.edad ?: 0, usuario?.sexo ?: "Otro").toString())
            val valorCentilN = formattedString(calcularCentilesN(contadorNoes, usuario?.edad ?: 0, usuario?.sexo ?: "Otro").toString())
            val valorCentilE = formattedString(calcularCentilesE(contadorE, usuario?.edad ?: 0, usuario?.sexo ?: "Otro").toString())

            val respuestasUsuarioString = respuestasUsuario.map {
                formattedString(it)
            }

            val valoresAInsertar = listOf(
                id,
                nombreUsuario,
                formattedString(contadorSies.toString()),
                formattedString(contadorNoes.toString()),
                formattedString(contadorE.toString()),
                valorCentilS,
                valorCentilN,
                valorCentilE,
                fecha,
                *respuestasUsuarioString.toTypedArray(),
                idioma,
                formattedString("0")
            )

            return keys.zip(valoresAInsertar).toMap()
        }

        private fun valorarSies(seleccionado: String): Int {
            var solSi = 0
            if (seleccionado.equals("si", ignoreCase = true)) {
                solSi = 1
            }
            return solSi
        }

        private fun valorarNoes(seleccionado: String): Int {
            var solNo = 0
            if (seleccionado.equals("no", ignoreCase = true)) {
                solNo = 1
            }
            return solNo
        }

        private fun calcularCentilesN(numero: Int, edad: Int, sexo: String): Int {

            val resultado = if (edad < 18) // es menor de edad
            {
                if (sexo.equals("Masculino", ignoreCase = true)) {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 1
                        3 -> 5
                        4 -> 10
                        5 -> 20
                        6 -> 25
                        7 -> 35
                        8 -> 45
                        9 -> 55
                        10 -> 65
                        11 -> 75
                        12 -> 80
                        13 -> 85
                        14 -> 90
                        15 -> 95
                        16 -> 95
                        17 -> 97
                        18 -> 98
                        19 -> 99
                        20 -> 99
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } // if masculino
                else  // sexo femenino y menor de edad
                {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 1
                        3 -> 3
                        4 -> 5
                        5 -> 15
                        6 -> 20
                        7 -> 30
                        8 -> 40
                        9 -> 50
                        10 -> 60
                        11 -> 65
                        12 -> 75
                        13 -> 80
                        14 -> 85
                        15 -> 90
                        16 -> 95
                        17 -> 95
                        18 -> 97
                        19 -> 99
                        20 -> 99
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } //if femenino
                // ahora los mayores de edad
            } else  // mayor igual 18
            {
                if (sexo.equals("Masculino", ignoreCase = true)) {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 4
                        3 -> 10
                        4 -> 20
                        5 -> 30
                        6 -> 40
                        7 -> 50
                        8 -> 60
                        9 -> 70
                        10 -> 75
                        11 -> 85
                        12 -> 90
                        13 -> 90
                        14 -> 95
                        15 -> 97
                        16 -> 98
                        17 -> 99
                        18 -> 99
                        19 -> 99
                        20 -> 99
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } // if masculino
                else  // sexo femenino mayor de edad
                {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 3
                        3 -> 10
                        4 -> 15
                        5 -> 25
                        6 -> 35
                        7 -> 45
                        8 -> 55
                        9 -> 65
                        10 -> 75
                        11 -> 80
                        12 -> 85
                        13 -> 90
                        14 -> 95
                        15 -> 96
                        16 -> 97
                        17 -> 98
                        18 -> 99
                        19 -> 99
                        20 -> 99
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } //if femenino
            }

            return resultado
        }

        private fun calcularCentilesE(numero: Int, edad: Int, sexo: String): Int {

            val resultado = if (edad < 18) // es menor de edad
            {
                if (sexo.equals("Masculino", ignoreCase = true)) {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 1
                        3 -> 1
                        4 -> 1
                        5 -> 1
                        6 -> 1
                        7 -> 2
                        8 -> 4
                        9 -> 5
                        10 -> 10
                        11 -> 15
                        12 -> 25
                        13 -> 30
                        14 -> 45
                        15 -> 55
                        16 -> 70
                        17 -> 80
                        18 -> 90
                        19 -> 95
                        20 -> 98
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } // if masculino
                else  // sexo femenino y menor de edad
                {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 1
                        3 -> 1
                        4 -> 1
                        5 -> 1
                        6 -> 1
                        7 -> 2
                        8 -> 3
                        9 -> 5
                        10 -> 10
                        11 -> 15
                        12 -> 20
                        13 -> 30
                        14 -> 40
                        15 -> 55
                        16 -> 65
                        17 -> 80
                        18 -> 85
                        19 -> 95
                        20 -> 97
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } //if femenino
                // ahora los mayores de edad
            } else  // mayor igual 18
            {
                if (sexo.equals("Masculino", ignoreCase = true)) {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 1
                        3 -> 1
                        4 -> 1
                        5 -> 1
                        6 -> 1
                        7 -> 2
                        8 -> 3
                        9 -> 5
                        10 -> 10
                        11 -> 15
                        12 -> 25
                        13 -> 35
                        14 -> 45
                        15 -> 60
                        16 -> 70
                        17 -> 80
                        18 -> 90
                        19 -> 95
                        20 -> 98
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } // if masculino
                else  // sexo femenino mayor de edad
                {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 1
                        3 -> 1
                        4 -> 1
                        5 -> 1
                        6 -> 1
                        7 -> 1
                        8 -> 2
                        9 -> 4
                        10 -> 10
                        11 -> 15
                        12 -> 20
                        13 -> 30
                        14 -> 40
                        15 -> 55
                        16 -> 65
                        17 -> 80
                        18 -> 90
                        19 -> 95
                        20 -> 97
                        21 -> 99
                        22 -> 99
                        23 -> 99
                        24 -> 99
                        else -> 80
                    }
                } //if femenino
            }

            return resultado
        }

        private fun calcularCentilesS(numero: Int, edad: Int, sexo: String): Int {

            val resultado = if (edad < 18) // es menor de edad
            {
                if (sexo.equals("Masculino", ignoreCase = true)) {
                    when (numero) {
                        0 -> 1
                        1 -> 1
                        2 -> 5
                        3 -> 10
                        4 -> 20
                        5 -> 35
                        6 -> 50
                        7 -> 70
                        8 -> 85
                        9 -> 96
                        else -> 80
                    }
                } // if masculino
                else  // sexo femenino y menor de edad
                {
                    when (numero) {
                        0 -> 1
                        1 -> 2
                        2 -> 4
                        3 -> 15
                        4 -> 25
                        5 -> 40
                        6 -> 60
                        7 -> 75
                        8 -> 90
                        9 -> 98
                        else -> 80
                    }
                } //if femenino
                // ahora los mayores de edad
            } else  // mayor igual 18
            {
                if (sexo.equals("Masculino", ignoreCase = true)) {
                    when (numero) {
                        0 -> 1
                        1 -> 2
                        2 -> 5
                        3 -> 10
                        4 -> 25
                        5 -> 40
                        6 -> 55
                        7 -> 75
                        8 -> 85
                        9 -> 96
                        else -> 80
                    }
                } // if masculino
                else  // sexo femenino mayor de edad
                {
                    when (numero) {
                        0 -> 1
                        1 -> 2
                        2 -> 5
                        3 -> 15
                        4 -> 30
                        5 -> 50
                        6 -> 65
                        7 -> 80
                        8 -> 90
                        9 -> 97
                        else -> 80
                    }
                } //if femenino
            }

            return resultado
        }
    }
}