package com.uma.menpas.services

import android.util.Log
import com.uma.menpas.network.PeticionSOAP
import org.ksoap2.serialization.SoapObject

class UsuarioService(){

    companion object{
        private fun extractDataFromResponsegetUser(result: SoapObject): List<String>? {
            val listaUser = mutableListOf<String>()
            for (i in 0 until result.propertyCount){
                Log.d("Result", result.getPropertyAsString(i))
                listaUser.add(result.getPropertyAsString(i))
            }

            Log.d("User", listaUser.toString())
            return listaUser
        }

        fun getUser(nombreUsuario: String, contrasenya: String){
            val request = SoapObject("http://tempuri.org/", "getUser")
            request.addProperty("username", nombreUsuario)
            request.addProperty("pwd", contrasenya)

            val response = extractDataFromResponsegetUser(PeticionSOAP.enviarPeticion(request))
            Log.v("RESPONSE", response.toString())
        }
    }
}