package com.uma.menpas.services

import android.util.Log
import com.uma.menpas.models.Usuario
import com.uma.menpas.models.adapters.AdaptadorUsuario
import com.uma.menpas.network.PeticionSOAP
import org.ksoap2.serialization.SoapObject
import java.time.LocalDate

class UsuarioService(){

    companion object{
        fun getUser(nombreUsuario: String, contrasenya: String): Usuario? {
            val request = SoapObject("http://tempuri.org/", "getUser")
            request.addProperty("username", nombreUsuario)
            request.addProperty("pwd", contrasenya)

            val result = PeticionSOAP.enviarPeticion(request)
            return AdaptadorUsuario.soapObjectAModelo(result)
        }
    }
}