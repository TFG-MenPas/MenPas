package com.uma.menpas.controllers

import com.uma.menpas.models.Usuario
import com.uma.menpas.services.UsuarioService
import java.time.LocalDateTime

class RegistroController {
    val usuarioService = UsuarioService()

    fun registrarUsuario(
        usuario: String,
        contrasenya: String,
        nombre: String,
        apellidos: String,
        edad: String,
        email: String,
        grupo: String,
        anyoComienzo: String,
        genero: String,
        deportePracticado: String,
        nacionalidad: String,
        estadoCivil: String,
        nivelEstudios: String,
        horasSemanales: String,
        profesion: String,
    ): Usuario? {
        val usuarioNuevo = Usuario(
            usuario,
            contrasenya,
            nombre,
            apellidos,
            Integer.parseInt(edad),
            "",
            "",
            genero,
            LocalDateTime.now().toString(),
            "",
            email,
            deportePracticado,
            grupo,
            nacionalidad,
            estadoCivil,
            Integer.parseInt(horasSemanales),
            nivelEstudios,
            profesion,
            Integer.parseInt(anyoComienzo),
        )

        return usuarioService.registrarUsuario(usuarioNuevo)
    }

    fun comprobarDatos(
        usuario: String,
        contrasenya: String,
        repContrasenya: String,
        nombre: String,
        apellidos: String,
        edad: String,
        email: String,
        repEmail: String,
        grupo: String,
        anyoComienzo: String,
        genero: String,
        deportePracticado: String,
        nacionalidad: String,
        estadoCivil: String,
        nivelEstudios: String,
        horasSemanales: String,
        profesion: String,
        terminos: Boolean
    ): Int {
        val cUsuario = comprobarUsuario(usuario)
        val cContrasenya = comprobarContrasenya(contrasenya, repContrasenya)
        val cEmail = comprobarEmail(email, repEmail)
        val cCamposRestantes =
            comprobarCamposRestantes(nombre, apellidos, edad, grupo, anyoComienzo, terminos)

        return comprobarCodigos(cUsuario, cContrasenya, cEmail, cCamposRestantes)
    }

    fun comprobarUsuario(usuario: String): Int {
        if (usuario.isNullOrBlank()) {
            return 1
        }
        if (comprobarUsuarioExiste(usuario)) { //Usuario existe
            return 2
        }
        return 0
    }

    fun comprobarUsuarioExiste(usuario: String): Boolean {
        return usuarioService.existeNombreUsuario(usuario)
    }

    fun comprobarEmailExiste(email: String): Boolean {
        return usuarioService.existeCorreo(email)
    }

    fun comprobarContrasenya(contrasenya: String, repContrasenya: String): Int {
        if (contrasenya.isNullOrBlank() || repContrasenya.isNullOrBlank()) {
            return 1
        }
        if (contrasenya != repContrasenya) { //Contraseñas no coinciden
            return 3
        }
        return 0
    }

    fun comprobarEmail(email: String, repEmail: String): Int {
        if (email.isNullOrBlank() || repEmail.isNullOrBlank()) {
            return 1
        }
        if (email != repEmail) { //Emails no coinciden
            return 4
        }
        if (comprobarEmailExiste(email)) { //Email existe
            return 5
        }
        return 0
    }

    fun comprobarCamposRestantes(
        nombre: String,
        apellidos: String,
        edad: String,
        grupo: String,
        anyoComienzo: String,
        terminos: Boolean
    ): Int {
        if (nombre.isNullOrBlank() || apellidos.isNullOrBlank() || edad.isNullOrBlank() || grupo.isNullOrBlank() || anyoComienzo.isNullOrBlank()) {
            return 1
        }
        if (!terminos) { //Terminos no aceptados
            return 6
        }
        return 0
    }

    fun comprobarCodigos(
        cUsuario: Int,
        cContrasenya: Int,
        cEmail: Int,
        cCamposRestantes: Int
    ): Int {
        if (cUsuario != 0) {
            return cUsuario
        }
        if (cContrasenya != 0) {
            return cContrasenya
        }
        if (cEmail != 0) {
            return cEmail
        }
        if (cCamposRestantes != 0) {
            return cCamposRestantes
        }
        return 0
    }

    fun getListaDeportes(): Array<String> {
        return usuarioService.getListaDeportes()
    }

    fun getListaEstadoCivil(): Array<String> {
        return usuarioService.getListaEstadoCivil()
    }

    fun getListaNacionalidades(): Array<String> {
        return usuarioService.getListaNacionalidades()
    }

    fun getNivelEstudios(): Array<String> {
        return usuarioService.getNivelEstudios()
    }

    fun getListaPerfiles(): Array<String> {
        return usuarioService.getListaPerfiles()
    }

    fun getListaProfesion(): Array<String> {
        return usuarioService.getListaProfesion()
    }

    fun getListaSexo(): Array<String> {
        return usuarioService.getListaSexo()
    }
}