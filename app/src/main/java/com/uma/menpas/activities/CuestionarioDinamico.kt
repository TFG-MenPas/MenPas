package com.uma.menpas.activities

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class CuestionarioDinamico : AppCompatActivity() {
    lateinit var botonAnterior : RelativeLayout
    lateinit var botonCerrarCuestionario : ImageButton
    lateinit var textNumeroPregunta : TextView
    lateinit var textTotalNumeroPreguntas : TextView
    lateinit var progressBar : ProgressBar
    lateinit var textPregunta : TextView
    lateinit var botonSiguiente : Button
    lateinit var rlDinamico: RelativeLayout
    lateinit var cuestionarioDinamico : View
    lateinit var respuestasUsuario : ArrayList<String>
    private val JSON_RESOURCE_NAME = "preguntas_caf"
    private val JSON_OBJECT_NAME = "Preguntas"
    private val JSON_RESOURCE_TYPE = "raw"
    private var indicePregunta = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_dinamico)

        botonAnterior = findViewById(R.id.btn_anterior)
        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarDesplegable)
        textNumeroPregunta = findViewById(R.id.numeroPreguntaActual)
        textTotalNumeroPreguntas = findViewById(R.id.numeroPreguntas)
        progressBar = findViewById(R.id.progressBarCuestionario)
        textPregunta = findViewById(R.id.textPregunta)
        botonSiguiente = findViewById(R.id.buttonSiguiente)
        rlDinamico = findViewById(R.id.RLDynamicContent)
        respuestasUsuario = ArrayList()

        val obj = getJSONObjectFromRaw()
        val preguntas = obj.getJSONArray(JSON_OBJECT_NAME)
        rellenarPregunta(preguntas)

        botonSiguiente.setOnClickListener {
            val tipoPregunta = preguntas.getJSONObject(indicePregunta).getString("tipo")
            guardarRespuesta(tipoPregunta)
            if(indicePregunta == preguntas.length() - 1){
                showToast("Cuestionario Finalizado")
            }else{
                indicePregunta++
                rellenarPregunta(preguntas)
            }
        }

        botonAnterior.setOnClickListener {
            if(indicePregunta <= 0){
                val viewShake = AnimationUtils.loadAnimation(this, R.anim.view_shake)
                botonAnterior.startAnimation(viewShake)
            }else{
                val tipoPregunta = preguntas.getJSONObject(indicePregunta).getString("tipo")
                guardarRespuesta(tipoPregunta)
                indicePregunta--
                rellenarPregunta(preguntas)
            }
        }
    }

    private fun guardarRespuesta(tipoPregunta: String) {
        when (tipoPregunta) {
            "textoLibre" -> guardarRespuestaTextoLibre()
            "fecha" -> guardarRespuestaFecha()
            "siNo" -> guardarRespuestaSiNo()
            "seleccionMultiple" ->  guardarRespuestaSeleccionMultiple()
        }
    }

    private fun guardarRespuestaSiNo() {
        val btnSi : Button = cuestionarioDinamico.findViewById(R.id.buttonSi)
        val btnNo : Button = cuestionarioDinamico.findViewById(R.id.buttonNo)
        if(btnSi.contentDescription == "checked"){
            actualizarRespuestasUsuario("si")
        }else if (btnNo.contentDescription == "checked"){
            actualizarRespuestasUsuario("no")
        }else{
            actualizarRespuestasUsuario("unchecked")
        }
        btnSi.contentDescription = "unchecked"
        btnNo.contentDescription = "unchecked"
    }

    private fun guardarRespuestaTextoLibre() {
        val editText : EditText = cuestionarioDinamico.findViewById(R.id.editTextCuestionarioTextoLibre)
        actualizarRespuestasUsuario(editText.text.toString())
    }

    private fun guardarRespuestaFecha() {
        val calendar : CalendarView = cuestionarioDinamico.findViewById(R.id.calendarView)
        actualizarRespuestasUsuario(calendar.date.toString())
    }

    private fun guardarRespuestaSeleccionMultiple() {
        val textOpcion : TextView = cuestionarioDinamico.findViewById(R.id.textOpcion)
        actualizarRespuestasUsuario(textOpcion.text as String)
    }

    private fun actualizarRespuestasUsuario(res: String){
        if(respuestasUsuario.elementAtOrNull(indicePregunta) == null){
            respuestasUsuario.add(indicePregunta, res)
        }else{
            respuestasUsuario[indicePregunta] = res
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
        val tipoPregunta = pregunta.getString("tipo")

        textTotalNumeroPreguntas.text = numeroTotalPreguntas.toString()
        textNumeroPregunta.text = numeroPregunta.toString()
        progressBar.max = numeroTotalPreguntas
        progressBar.progress = numeroPregunta
        textPregunta.text = textoPregunta

        when (tipoPregunta) {
            "textoLibre" -> rellenarPreguntaTextoLibre(pregunta.getString("input"))
            "fecha" -> rellenarPreguntaFecha()
            "siNo" -> rellenarPreguntaSiNo()
            "seleccionMultiple" -> rellenarPreguntaSeleccionMultiple(pregunta.getJSONArray("respuestas"))
        }
    }

    private fun rellenarPreguntaSiNo() {
        actualizarLayout(R.layout.cuestionario_si_no)
        val btnSi : Button = cuestionarioDinamico.findViewById(R.id.buttonSi)
        val btnNo : Button = cuestionarioDinamico.findViewById(R.id.buttonNo)

        val checked = AppCompatResources.getDrawable(this, R.drawable.icon_checked)
        val backChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox_checked)
        val unchecked = AppCompatResources.getDrawable(this, R.drawable.icon_unchecked)
        val backunChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox)

        var btnSiChecked = false
        var btnNoChecked = false



        btnSi.setOnClickListener {
            if (!btnSiChecked){
                btnSi.background = backChecked
                btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnSiChecked = true
                btnSi.contentDescription = "checked"
                if (btnNoChecked){
                    btnNo.background = backunChecked
                    btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                    btnNoChecked = false
                    btnNo.contentDescription = "unchecked"
                }
            }else{
                btnSi.background = backunChecked
                btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnSiChecked = false
                btnSi.contentDescription = "unchecked"
            }
        }
        btnNo.setOnClickListener {
            if (!btnNoChecked){
                btnNo.background = backChecked
                btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnNoChecked = true
                btnNo.contentDescription = "checked"
                if (btnSiChecked){
                    btnSi.background = backunChecked
                    btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                    btnSiChecked = false
                    btnSi.contentDescription = "unchecked"
                }
            }else{
                btnNo.background = backunChecked
                btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnNoChecked = false
                btnNo.contentDescription = "unchecked"
            }
        }

        btnSi.background = backunChecked
        btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
        btnNo.background = backunChecked
        btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)

        if(respuestasUsuario.elementAtOrNull(indicePregunta) != null) {
            when (respuestasUsuario[indicePregunta]) {
                "si" -> btnSi.performClick()
                "no" -> btnNo.performClick()
                "unchecked" -> ""
            }
        }
    }

    private fun rellenarPreguntaTextoLibre(input: String) {
        actualizarLayout(R.layout.cuestionario_texto_libre)
        val editText : EditText = cuestionarioDinamico.findViewById(R.id.editTextCuestionarioTextoLibre)
        editText.clearComposingText()
        when(input){
            "tiempo" -> editText.inputType = InputType.TYPE_CLASS_DATETIME
            "texto" -> editText.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        }
        if(respuestasUsuario.elementAtOrNull(indicePregunta) != null){
            editText.setText(respuestasUsuario[indicePregunta])
        }else{
            editText.setText("")
        }
    }

    private fun rellenarPreguntaFecha() {
        actualizarLayout(R.layout.cuestionario_fecha)
        val calendar : CalendarView = cuestionarioDinamico.findViewById(R.id.calendarView)
        calendar.setOnDateChangeListener { _, year, month, day ->
            val c = Calendar.getInstance()
            c.set(year, month, day)
            calendar.date = c.timeInMillis
        }
        if(respuestasUsuario.elementAtOrNull(indicePregunta) != null){
            calendar.date = respuestasUsuario[indicePregunta].toLong()
        }
    }

    private fun rellenarPreguntaSeleccionMultiple(respuestas: JSONArray) {
        actualizarLayout(R.layout.cuestionario_seleccion_multiple)

        val listaRespuestas : ArrayList<String> = ArrayList()
        for (i in 0 until respuestas.length()){
            listaRespuestas.add(i, respuestas.getString(i))
        }
        val textOpcion : TextView = cuestionarioDinamico.findViewById(R.id.textOpcion)
        val seekbar : SeekBar = cuestionarioDinamico.findViewById(R.id.seekbar)
        seekbar.max = listaRespuestas.size - 1
        if (seekbar.max > 7) seekbar.tickMark = null
        seekbar.progressDrawable = null
        if(respuestasUsuario.elementAtOrNull(indicePregunta) == null){
            textOpcion.text = listaRespuestas[0]
            seekbar.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
            seekbar.progress = 0
        }else{
            val respuesta = respuestasUsuario[indicePregunta]
            textOpcion.text = respuesta
            seekbar.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
            seekbar.progress = listaRespuestas.indexOf(respuesta)
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOpcion.text = listaRespuestas[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }
    private fun actualizarLayout(layout: Int){
            rlDinamico.removeAllViews()
            cuestionarioDinamico = layoutInflater.inflate(layout, rlDinamico, false)
            rlDinamico.addView(cuestionarioDinamico)
    }
}