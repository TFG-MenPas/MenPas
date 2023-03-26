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

class pruebaApi : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<String>> {
    private val NAMESPACE = "http://tempuri.org/"
    private val METHOD_NAME = "lista_estado_civil"
    private val SOAP_ACTION = "$NAMESPACE$METHOD_NAME"
    private val URL = "http://150.214.108.138/menpas/ServiceApp.asmx?op=$METHOD_NAME"
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
                //request.addProperty("username", "")
                val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
                envelope.dotNet = true
                envelope.setOutputSoapObject(request)
                val httpTransport = HttpTransportSE(URL)
                try {
                    httpTransport.call(SOAP_ACTION, envelope)
                } catch (e: Exception){
                    e.printStackTrace()
                }
                Log.d("LOG_TAG", envelope.bodyIn.toString())
                return null
            }
        }
    }

    override fun onLoaderReset(loader: Loader<List<String>>) {
        TODO("Not yet implemented")
    }

    override fun onLoadFinished(loader: Loader<List<String>>, data: List<String>?) {
    }
}