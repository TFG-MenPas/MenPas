package com.uma.menpas.cuestionarios

import android.content.Context
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.services.UsuarioService
import com.uma.menpas.utils.ParseUtils
import com.uma.menpas.utils.ParseUtils.Companion.formattedString

class PDavidson {

    companion object {
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

        val indicesInversosResistencia = listOf(0,2,4,5,7,9)
        val indicesInversosActitud = listOf(0,2,5,7,9)
        val indicesInversosIntuicion = listOf(0,1,3,6,7,9)
        val indicesInversosAutoconciencia = listOf(3,4,6,7,9)
        val indicesInversosContexto = listOf(0,4,5,7,8,9)
        val indicesInversosAtencion = listOf(0,1,2,5,6,9)

        fun calculatePDavidsonCompleto(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {

            val listaElementosPrueba = listOf(
                "No", "Si", "Si", "No", "No", "No", "Si", "No", "Si", "Si", "Si", "No", "Si", "Si", "No", "Si", "No",
                "Si", "No", "Si", "Si", "No", "No", "No", "Si", "No", "Si", "Si", "No", "No", "No", "Si", "No", "No",
                "Si", "No", "No", "No", "No", "Si", "No", "Si", "No", "No", "Si", "Si", "No", "No", "No", "No", "Si",
                "Si", "Si", "No", "No", "Si", "Si", "Si", "No", "Si"
            )

            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultadoResistencia = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosResistencia))
            val resultadoActitud = formattedString(procesarRespuestas(respuestasTransformadas.subList(10, 20), indicesInversosActitud))
            val resultadoIntuicion = formattedString(procesarRespuestas(respuestasTransformadas.subList(20, 30), indicesInversosIntuicion))
            val resultadoAutoconciencia = formattedString(procesarRespuestas(respuestasTransformadas.subList(30, 40), indicesInversosAutoconciencia))
            val resultadoContexto = formattedString(procesarRespuestas(respuestasTransformadas.subList(40, 50), indicesInversosContexto))
            val resultadoAtencion = formattedString(procesarRespuestas(respuestasTransformadas.subList(50, 60), indicesInversosAtencion))

            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                resultadoResistencia,
                resultadoActitud,
                resultadoIntuicion,
                resultadoAutoconciencia,
                resultadoContexto,
                resultadoAtencion,
                *respuestasTransformadas.toTypedArray(),
                formattedString("PED_completo"),
                *List<String>(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }

        fun calculatePDavidsonResistencia(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {
            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultadoResistencia = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosResistencia))

            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                resultadoResistencia,
                formattedString(""),
                formattedString(""),
                formattedString(""),
                formattedString(""),
                formattedString(""),
                *(respuestasTransformadas + List(50) {formattedString("")}).toTypedArray(),
                formattedString("PED_resistencia"),
                *List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }

        fun calculatePDavidsonActitud(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {
            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultado = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosActitud))

            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                formattedString(""),
                resultado,
                formattedString(""),
                formattedString(""),
                formattedString(""),
                formattedString(""),
                *(List(10) {formattedString("")} + respuestasTransformadas + List(40) {formattedString("")}).toTypedArray(),
                formattedString("PED_actitud"),
                *List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }
        fun calculatePDavidsonIntuicion(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {

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

            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultado = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosIntuicion))

            val blz = null
            val bdr = null
            val bd = null
            val valorCentilS = null
            val valorCentilN = null
            val valorCentilE = null
            val respuestasUsuarioString = null
            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                formattedString(""),
                formattedString(""),
                resultado,
                formattedString(""),
                formattedString(""),
                formattedString(""),
                *(List(20) {formattedString("")} + respuestasTransformadas + List(30) {formattedString("")}).toTypedArray(),
                formattedString("PED_intuicion"),
                *List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }
        fun calculatePDavidsonAutoconciencia(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {
            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultado = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosAutoconciencia))

            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                formattedString(""),
                formattedString(""),
                formattedString(""),
                resultado,
                formattedString(""),
                formattedString(""),
                *(List(30) {formattedString("")} + respuestasTransformadas + List(20) {formattedString("")}).toTypedArray(),
                formattedString("PED_autoconciencia"),
                *List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }
        fun calculatePDavidsonContexto(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {
            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultado = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosContexto))

            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                formattedString(""),
                formattedString(""),
                formattedString(""),
                formattedString(""),
                resultado,
                formattedString(""),
                *(List(40) {formattedString("")} + respuestasTransformadas + List(10) {formattedString("")}).toTypedArray(),
                formattedString("PED_contexto"),
                *List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }
        fun calculatePDavidsonAtencion(respuestasUsuario: ArrayList<String>, context: Context): Map<String, String> {
            val id = CuestionarioService().obtenerIdDisponible("PED", "Id_PED")

            val usuario = UsuarioController().getUsuario(context)
            val nombreUsuarioFormateado = formattedString(usuario.nombreUsuario)
            val fecha = formattedString(ParseUtils.obtenerFechaActual())
            val idioma = formattedString("es-es")

            val respuestasTransformadas = transformarRespuestas(respuestasUsuario)

            val resultado = formattedString(procesarRespuestas(respuestasTransformadas.subList(0, 10), indicesInversosAtencion))

            val valoresAInsertar = listOf(
                id,
                nombreUsuarioFormateado,
                formattedString(""),
                formattedString(""),
                formattedString(""),
                formattedString(""),
                formattedString(""),
                resultado,
                *(List(50) {formattedString("")} + respuestasTransformadas).toTypedArray(),
                formattedString("PED_atencion"),
                *List(60) {formattedString("")}.toTypedArray(),
                formattedString("0"),
                idioma,
                fecha,
                formattedString("0"), //Boton izquierdo
                formattedString("0"), //Boton derecho
                formattedString("0"), //Doble click
                formattedString("0"), //Resol
                formattedString(""), //Teclas
                formattedString(""), //Orden
                formattedString(""), //Edad
                formattedString(""), //Genero
                formattedString(""), //Deporte
                formattedString(""), //Grupo
                formattedString(""), //Nacionalidad
                formattedString(""), //EstadoCivil
                formattedString(""), //HorasSemanales
                formattedString(""), //Estudios
                formattedString(""), //Profesion
                formattedString(""), //TDeporte
                formattedString(""), //TipoUser
            )

            return keys.zip(valoresAInsertar).toMap()
        }



        private fun procesarRespuestas(respuestasTransformadas: List<String>, indicesPreguntasInversas: List<Int>): String {
            var contadorSies = 0

            for((indice, respuesta) in respuestasTransformadas.withIndex()){
                if (indice in indicesPreguntasInversas) {
                    if (respuesta == "1") {
                        contadorSies++
                    }
                }else{
                    if (respuesta != "1") {
                        contadorSies++
                    }
                }
            }


            return contadorSies.toString()
        }

        private fun transformarRespuestas(respuestasUsuario: List<String>): List<String> {
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