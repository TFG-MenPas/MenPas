package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.uma.menpas.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CSAI2 : AppCompatActivity() {
    lateinit var botonAnterior : RelativeLayout
    lateinit var botonCerrarCuestionario : ImageButton
    lateinit var textNumeroPregunta : TextView
    lateinit var textTotalNumeroPreguntas : TextView
    lateinit var progressBar : ProgressBar
    lateinit var textPregunta : TextView
    lateinit var botonSiguiente : Button
    lateinit var rlDinamico: RelativeLayout
    lateinit var cuestionarioDinamico : View
    private val JSON_RESOURCE_NAME = "preguntas_csai2"
    private val JSON_OBJECT_NAME = "Preguntas"
    private val JSON_RESOURCE_TYPE = "raw"
    private var indicePregunta = 0
    private var layoutDinamico = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csai2)

        botonAnterior = findViewById(R.id.btn_anterior)
        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarDesplegable)
        textNumeroPregunta = findViewById(R.id.numeroPreguntaActual)
        textTotalNumeroPreguntas = findViewById(R.id.numeroPreguntas)
        progressBar = findViewById(R.id.progressBarCuestionario)
        textPregunta = findViewById(R.id.textPregunta)
        botonSiguiente = findViewById(R.id.buttonSiguiente)
        rlDinamico = findViewById(R.id.RLDynamicContent)
        /* TODO: SI HAY QUE CAMBIAR EL TIPO DE PREGUNTA
        val cuestionarioDinamico: View = layoutInflater.inflate(R.layout.cuestionario_si_no, rlDinamico, false)
        rlDinamico.addView(cuestionarioDinamico)

        botonSiguiente.setOnClickListener {
            rlDinamico.removeView(cuestionarioDinamico)
            val cuestionarioDinamico: View = layoutInflater.inflate(R.layout.cuestionario_texto_libre, rlDinamico, false)
            rlDinamico.addView(cuestionarioDinamico)
        }
        */

        val obj = getJSONObjectFromRaw()
        val preguntas = obj.getJSONArray(JSON_OBJECT_NAME)
        rellenarPregunta(preguntas)

        botonSiguiente.setOnClickListener {
            //TODO: Como no cambia el tipo de pregunta no actualizo el tipo (Habria que meterlo en el JSON)
            //TODO: Mantener informacion de la respuesta del usuario
            if(indicePregunta == preguntas.length() - 1){
                showToast("Cuestionario Finalizado")
            }else{
                indicePregunta++
                rellenarPregunta(preguntas)
            }
        }

        botonAnterior.setOnClickListener {
            //TODO: Como no cambia el tipo de pregunta no actualizo el tipo (Habria que meterlo en el JSON)
            //TODO: Mantener informacion de la respuesta del usuario
            if(indicePregunta <= 0){
                val viewShake = AnimationUtils.loadAnimation(this, R.anim.view_shake)
                botonAnterior.startAnimation(viewShake)
            }else{
                indicePregunta--
                rellenarPregunta(preguntas)
            }
        }
    }
    private fun getJSONObjectFromRaw(): JSONObject {
        val jsonString = transformJSONtoString()
        return JSONObject(jsonString)
    }
    private fun transformJSONtoString(): String {
        val res = resources
        val resId = res.getIdentifier(JSON_RESOURCE_NAME, JSON_RESOURCE_TYPE, packageName)

        val inputStream = res.openRawResource(resId)
        val reader = BufferedReader(InputStreamReader(inputStream))

        val sb = StringBuilder()
        var line: String?

        try {
            while (reader.readLine().also {line = it} != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sb.toString()
    }
    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    private fun rellenarPregunta(preguntas: JSONArray) {
        val numeroTotalPreguntas = preguntas.length()
        val pregunta = preguntas.getJSONObject(indicePregunta)
        val numeroPregunta =  pregunta.getInt("numero")
        val textoPregunta = pregunta.getString("pregunta")
        val respuestas = pregunta.getJSONArray("respuestas")

        textTotalNumeroPreguntas.text = numeroTotalPreguntas.toString()
        textNumeroPregunta.text = numeroPregunta.toString()
        progressBar.max = numeroTotalPreguntas
        progressBar.progress = numeroPregunta
        textPregunta.text = textoPregunta

        rellenarCuestionarioSeleccionMultiple(respuestas)
    }
    private fun rellenarCuestionarioSeleccionMultiple(respuestas: JSONArray) {
        if(layoutDinamico != R.layout.cuestionario_seleccion_multiple){
            layoutDinamico = R.layout.cuestionario_seleccion_multiple
            rlDinamico.removeAllViews()
            cuestionarioDinamico = layoutInflater.inflate(R.layout.cuestionario_seleccion_multiple, rlDinamico, false)
            rlDinamico.addView(cuestionarioDinamico)
        }

        val textProposicion = cuestionarioDinamico.findViewById<TextView>(R.id.textProposicion)
        textProposicion.text = "¿Como se identifica?"
        val textOpcion : TextView = cuestionarioDinamico.findViewById(R.id.textOpcion)
        textOpcion.text = respuestas[0] as CharSequence
        val seekbar : SeekBar = cuestionarioDinamico.findViewById(R.id.seekbar)
        seekbar.progress = 0
        seekbar.max = respuestas.length() - 1

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOpcion.text = respuestas[progress] as CharSequence
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }
}