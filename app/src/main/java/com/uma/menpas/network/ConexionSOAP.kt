package com.uma.menpas.network

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import org.ksoap2.SoapEnvelope
import org.ksoap2.SoapFault12
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.util.logging.Logger

private class ConexionSOAP {

    companion object {
        fun enviarPeticion(request: SoapObject): SoapObject {
            val SOAP_ACTION = "${request.namespace}${request.name}"
            val URL = "http://150.214.108.138/menpas/ServiceApp.asmx?WSDL"
            lateinit var result: SoapObject
            //Politicas para poder realizar peticiones en el main thread
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
            envelope.setOutputSoapObject(request)
            envelope.dotNet = true
            try {
                val androidHttpTransport = HttpTransportSE(URL)
                androidHttpTransport.call(SOAP_ACTION, envelope)
                val response = envelope.response
                result = envelope.bodyIn as SoapObject
            } catch (e: Exception) {
                Log.e("ConexionSOAP", envelope.bodyIn.toString())
                e.printStackTrace()
            }
            return result
        }
    }
}

class PeticionSOAP() {
    companion object{
        fun enviarPeticion(request: SoapObject): SoapObject {
            return ConexionSOAP.enviarPeticion(request)
        }
    }
}