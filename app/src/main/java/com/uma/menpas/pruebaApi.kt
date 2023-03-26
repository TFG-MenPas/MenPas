package com.uma.menpas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.ByteArrayOutputStream
import java.io.StringReader
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult

class pruebaApi : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<String>> {
    private val NAMESPACE = "http://tempuri.org/"
    //private val METHOD_NAME = "lista_estado_civil"
    private val METHOD_NAME = "getUser"
    private val SOAP_ACTION = "$NAMESPACE$METHOD_NAME"
    private val URL = "http://150.214.108.138/menpas/ServiceApp.asmx?WSDL"
    private val LOADER_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_api)

        val loaderManager = LoaderManager.getInstance(this)
        loaderManager.initLoader(LOADER_ID, null, this)
    }
    @SuppressLint("StaticFieldLeak")
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<String>> {
        return object : AsyncTaskLoader<List<String>>(this){

            override fun onStartLoading() {
                super.onStartLoading()
                forceLoad()
            }
            override fun loadInBackground(): List<String>? {
                val request = SoapObject(NAMESPACE, METHOD_NAME)
                request.addProperty("username", "menpasprueba")
                request.addProperty("pwd", "menpasprueba")
                val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
                envelope.dotNet = true
                envelope.setOutputSoapObject(request)
                val httpTransport = HttpTransportSE(URL)
                try {
                    httpTransport.call(SOAP_ACTION, envelope)
                    return extractDataFromResponsegetUser(envelope)
                } catch (e: Exception){
                    e.printStackTrace()
                }
                Log.d("LOG_TAG", envelope.bodyIn.toString())
                Log.d("LOG_TAG", envelope.response.toString())
                return null
            }
        }
    }

    private fun extractDataFromResponseEstadoCivil(envelope: SoapSerializationEnvelope): List<String>? {
        val listaEstadoCivil = mutableListOf<String>()

        val result: SoapObject = envelope.response as SoapObject
        for (i in 0 until result.propertyCount){
            Log.d("Result", result.getPropertyAsString(i))
            listaEstadoCivil.add(result.getPropertyAsString(i))
        }

        Log.d("Estado Civil", listaEstadoCivil.toString())
        return listaEstadoCivil
    }
    private fun extractDataFromResponsegetUser(envelope: SoapSerializationEnvelope): List<String>? {
        val listaUser = mutableListOf<String>()

        val result: SoapObject = envelope.response as SoapObject
        for (i in 0 until result.propertyCount){
            Log.d("Result", result.getPropertyAsString(i))
            listaUser.add(result.getPropertyAsString(i))
        }

        Log.d("User", listaUser.toString())
        return listaUser
    }

    override fun onLoaderReset(loader: Loader<List<String>>) {
        TODO("Not yet implemented")
    }

    override fun onLoadFinished(loader: Loader<List<String>>, data: List<String>?) {
        //Una vez cargados los datos aqui se mostrarian por pantalla
    }
}