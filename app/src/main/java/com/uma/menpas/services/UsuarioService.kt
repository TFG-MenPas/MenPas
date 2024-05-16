package com.uma.menpas.services

import android.content.Context
import com.uma.menpas.models.Usuario
import com.uma.menpas.models.adapters.AdaptadorUsuario
import com.uma.menpas.network.PeticionSOAP
import com.uma.menpas.network.SoapBuilder
import com.uma.menpas.room.UsuarioDB
import org.ksoap2.serialization.SoapObject

class UsuarioService(){

    fun getUser(nombreUsuario: String, contrasenya: String): Usuario? {
        val request = SoapObject("http://tempuri.org/", "getUser")
        request.addProperty("username", nombreUsuario)
        request.addProperty("pwd", contrasenya)

        val result = PeticionSOAP.enviarPeticion(request)
        return AdaptadorUsuario.soapObjectAModelo(result)
    }

    fun registrarUsuario(usuario: Usuario): Usuario? {
        val request = SoapObject("http://tempuri.org/", "CrearUsuario")

        request.addProperty("userName", usuario.nombreUsuario)
        request.addProperty("clave", usuario.contrasenya)
        request.addProperty("nombre", usuario.nombre)
        request.addProperty("apellidos", usuario.apellidos)
        request.addProperty("edad", usuario.edad)
        request.addProperty("tlfno", usuario.telefono)
        request.addProperty("dni", usuario.dni)
        request.addProperty("sexo", usuario.sexo)
        request.addProperty("e_mail", usuario.correo)
        request.addProperty("deporte", usuario.deportePracticado)
        request.addProperty("grupo", usuario.grupo)
        request.addProperty("nacionalidad", usuario.nacionalidad)
        request.addProperty("estado_civil", usuario.estadoCivil)
        request.addProperty("horas_semanales", usuario.horasSemanales)
        request.addProperty("estudios", usuario.estudios)
        request.addProperty("profesion", usuario.profesion)
        request.addProperty("aComienzoDeporte", usuario.aComienzoDeporte)

        val result = PeticionSOAP.enviarPeticion(request)
        return AdaptadorUsuario.soapObjectAModelo(result)
    }
    fun getListaDeportes(): Array<String> {
        val request = SoapObject("http://tempuri.org/", "lista_deportes")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun getListaEstadoCivil(): Array<String>{
        val request = SoapObject("http://tempuri.org/", "lista_estado_civil")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun getListaNacionalidades(): Array<String>{
        val request = SoapObject("http://tempuri.org/", "lista_nacionalidades")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun getNivelEstudios(): Array<String>{
        val request = SoapObject("http://tempuri.org/", "lista_nivel_estudios")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun getListaPerfiles(): Array<String>{
        val request = SoapObject("http://tempuri.org/", "lista_perfiles")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun getListaProfesion(): Array<String>{
        val request = SoapObject("http://tempuri.org/", "lista_profesion")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun getListaSexo(): Array<String>{
        val request = SoapObject("http://tempuri.org/", "lista_sexo")
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapDatosRegistroAArray(result)
    }

    fun existeCorreo(correo: String): Boolean {
        val request = SoapObject("http://tempuri.org/", "existeCorreo")
        request.addProperty("correo", correo)
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)
    }

    fun existeNombreUsuario(usuario: String): Boolean {
        val request = SoapObject("http://tempuri.org/", "existeUsuario")
        request.addProperty("username", usuario)
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)
    }

    fun darDeBaja(nombreUsuario: String?, contrasenya: String?): Boolean {
        val request = SoapObject("http://tempuri.org/", "borrarUser")
        request.addProperty("username", nombreUsuario)
        request.addProperty("pwd", contrasenya)
        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)
    }

    fun editarNombreDeUsuario(usuario: String, contrasenya: String, nuevoValor: String): Boolean {
        val request = SoapBuilder.createSoapObject("updateUserName")
        request.addProperty("username", usuario)
        request.addProperty("pwd", contrasenya)
        request.addProperty("newUserName", nuevoValor)

        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)

    }

    fun editarNombre(usuario: String?, contrasenya: String?, nuevoValor: String?): Boolean {
        val request = SoapBuilder.createSoapObject("updateNombreUsuario")
        request.addProperty("username", usuario)
        request.addProperty("pwd", contrasenya)
        request.addProperty("newName", nuevoValor)

        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)
    }

    fun editarApellidos(usuario: String, contrasenya: String, nuevoValor: String): Boolean {
        val request = SoapBuilder.createSoapObject("updateApellidoUsuario")
        request.addProperty("username", usuario)
        request.addProperty("pwd", contrasenya)
        request.addProperty("newApellido", nuevoValor)

        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)

    }

    fun editarSuscripcion(usuario: String, contrasenya: String, nuevoValor: Boolean): Boolean {
        val request = SoapBuilder.createSoapObject("SuscripcionCorreoElectrónico")
        request.addProperty("username", usuario)
        request.addProperty("pwd", contrasenya)
        request.addProperty("activa", nuevoValor)

        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)
    }

    fun comprobarUsuario(usuario: String, contrasenya: String): Usuario? {
        var usuario = getUser(usuario, contrasenya)
        usuario?.contrasenya = contrasenya
        return usuario
    }

    fun guardarUsuarioEnBD(context: Context, usuario: Usuario){
        val usuarioDB = UsuarioDB.getDatabase(context)
        usuarioDB?.UsuarioDAO()?.insertUsuario(usuario)
    }

    fun actualizarUsuarioEnBD(context: Context, usuario: Usuario){
        val usuarioDB = UsuarioDB.getDatabase(context)
        usuarioDB?.UsuarioDAO()?.updateUsuario(usuario)
    }

    fun borrarUsuarioEnBD(context: Context){
        val usuarioDB = UsuarioDB.getDatabase(context)
        usuarioDB?.UsuarioDAO()?.limpiarUsuario()
    }

    /*
    fun editarUsuario(usuarioAntiguo: Usuario, usuarioActualizado: Usuario): Boolean {
        val request = SoapBuilder.createSoapObject("updateUser")

        val oldUser = buildUsuarioSoap("oldUser", usuarioAntiguo)
        val newUser = buildUsuarioSoap("newUser", usuarioActualizado)

        request.addProperty("oldUser", oldUser)
        request.addProperty("newUser", newUser)

        val result = PeticionSOAP.enviarPeticion(request)

        return AdaptadorUsuario.soapObjectABoolean(result)
    }

    private fun buildUsuarioSoap(namespace: String, usuario: Usuario): SoapObject {
        val request = SoapBuilder.createSoapObject(namespace)

        request.addProperty("Nombre_Usuario", usuario.nombreUsuario)
        request.addProperty("Contraseña", usuario.contrasenya)
        request.addProperty("Nombre", usuario.nombre)
        request.addProperty("Apellidos", usuario.apellidos)
        request.addProperty("Edad", usuario.edad)
        request.addProperty("Telefono", "usuario.telefono")
        request.addProperty("Dni", "usuario.dni")
        request.addProperty("Sexo", usuario.sexo)
        request.addProperty("Fecha", usuario.fechaRegistro)
        request.addProperty("Perfil", usuario.perfil)
        request.addProperty("Correo", usuario.correo)
        request.addProperty("Deporte_Practicado", usuario.deportePracticado)
        request.addProperty("Grupo", usuario.grupo)
        request.addProperty("Nacionalidad", usuario.nacionalidad)
        request.addProperty("Estado_Civil", usuario.estadoCivil)
        request.addProperty("HorasSemanales", usuario.horasSemanales)
        request.addProperty("Estudios", usuario.estudios)
        request.addProperty("Profesion", usuario.profesion)
        request.addProperty("aComienzoDeporte", usuario.aComienzoDeporte)
        request.addProperty("infoEmail", usuario.infoEmail)

        return request
    }
    */


}