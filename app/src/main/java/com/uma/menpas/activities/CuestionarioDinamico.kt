package com.uma.menpas.activities

import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R
import com.uma.menpas.utils.Boton.Companion.deshabilitarBoton
import com.uma.menpas.utils.Boton.Companion.habilitarBoton
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.collections.ArrayList
import com.uma.menpas.utils.QueryParser
import com.uma.menpas.utils.CalculoResultados
import com.uma.menpas.services.CuestionarioService

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
    lateinit var listaSeries : ArrayList<View>
    private lateinit var JSON_RESOURCE_NAME : String
    private val JSON_OBJECT_NAME = "Preguntas"
    private val JSON_RESOURCE_TYPE = "raw"
    private var indicePregunta = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_dinamico)

        JSON_RESOURCE_NAME = intent.getStringExtra("json_resource_name") as String

        botonAnterior = findViewById(R.id.btn_anterior)
        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarDesplegable)
        textNumeroPregunta = findViewById(R.id.numeroPreguntaActual)
        textTotalNumeroPreguntas = findViewById(R.id.numeroPreguntas)
        progressBar = findViewById(R.id.progressBarCuestionario)
        textPregunta = findViewById(R.id.textPregunta)
        botonSiguiente = findViewById(R.id.buttonSiguiente)
        rlDinamico = findViewById(R.id.RLDynamicContent)
        respuestasUsuario = ArrayList()
        val usuario = intent.getStringExtra("usuario") as String

        val obj = getJSONObjectFromRaw()
        val preguntas = obj.getJSONArray(JSON_OBJECT_NAME)
        rellenarPregunta(preguntas)

        botonSiguiente.setOnClickListener {
            val tipoPregunta = preguntas.getJSONObject(indicePregunta).getString("tipo")
            guardarRespuesta(tipoPregunta)
            if(indicePregunta == preguntas.length() - 1){
                this.finalizarCuestionario(usuario)
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
        botonCerrarCuestionario.setOnClickListener {
            finish()
        }
    }

    private fun finalizarCuestionario(usuario: String) {
        val calculosCuestionario: Map<String, String> = CalculoResultados().calculate(JSON_RESOURCE_NAME, respuestasUsuario, usuario, this)
        val query = QueryParser().parse(JSON_RESOURCE_NAME, calculosCuestionario)
        try {
            CuestionarioService().insertarCuestionario(query)
            showToast("Éxito en la petición")
        } catch (e: Error) {
            showToast("Algo salió mal realizando la petición")
        }

        val bundle = Bundle().apply {
            for ((key, value) in calculosCuestionario) {
                putString(key, value)
            }
        }

        val intent = Intent(this, DetallesCuestionario::class.java)
        intent.putExtras(bundle)
        intent.putExtra("jsonResourceName",JSON_RESOURCE_NAME)
        intent.putExtra("isResultado",true)
        startActivity(intent)
    }

    private fun guardarRespuesta(tipoPregunta: String) {
        when (tipoPregunta) {
            "textoLibre" -> guardarRespuestaTextoLibre()
            "fecha" -> guardarRespuestaFecha()
            "siNo" -> guardarRespuestaSiNo()
            "seleccionMultiple" ->  guardarRespuestaSeleccionMultiple()
            "seleccionMultipleSeries" -> guardarRespuestaSeleccionMultipleSeriesEntrenamiento()
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

    private fun guardarRespuestaSeleccionMultipleSeriesEntrenamiento() {
        var respuestaSeries = "${listaSeries.size}|"
        for (serie in listaSeries){
            val textOpcionAtleta = serie.findViewById<TextView>(R.id.textOpcionAtleta).text as String
            val textOpcionEntrenador = serie.findViewById<TextView>(R.id.textOpcionEntrenador).text as String
            respuestaSeries += textOpcionAtleta + "|" + textOpcionEntrenador + "|"

        }
        // 'numero de series | Opcion Atleta | Opcion Entrenador |
        actualizarRespuestasUsuario(respuestaSeries)
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
        val numeroTotalPreguntas = preguntas.getJSONObject(preguntas.length() - 1).getInt("numero")
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
            "seleccionMultipleSeries" -> rellenarPreguntaSeleccionMultipleSeriesEntrenamiento(pregunta.getJSONArray("respuestas"), pregunta.getJSONArray("respuestasAtleta"), pregunta.getJSONArray("respuestasEntrenador"))
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

        botonSiguiente.deshabilitarBoton()

        btnSi.setOnClickListener {
            if (!btnSiChecked){
                btnSi.background = backChecked
                btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnSiChecked = true
                btnSi.contentDescription = "checked"
                botonSiguiente.habilitarBoton()
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
                botonSiguiente.deshabilitarBoton()
            }
        }
        btnNo.setOnClickListener {
            if (!btnNoChecked){
                btnNo.background = backChecked
                btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnNoChecked = true
                btnNo.contentDescription = "checked"
                botonSiguiente.habilitarBoton()
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
                botonSiguiente.deshabilitarBoton()
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

        botonSiguiente.deshabilitarBoton()
        when(input){
            "tiempo" -> editText.inputType = InputType.TYPE_CLASS_DATETIME
            "texto" -> editText.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
            "number" -> editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
        if(respuestasUsuario.elementAtOrNull(indicePregunta) != null){
            editText.setText(respuestasUsuario[indicePregunta])
        }else{
            editText.setText("")
        }

        if(editText.text.isNotEmpty()) botonSiguiente.habilitarBoton()

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(editText.text.toString().trim().isEmpty()){
                    botonSiguiente.deshabilitarBoton()
                }else{
                    botonSiguiente.habilitarBoton()
                }
            }
        })
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
    private fun rellenarPreguntaSeleccionMultipleSeriesEntrenamiento(respuestas: JSONArray, respuestasAtleta: JSONArray, respuestasEntrenador: JSONArray) {
        actualizarLayout(R.layout.cuestionario_seleccion_multiple)

        val llSeleccionMultiple: LinearLayout = cuestionarioDinamico.findViewById(R.id.llSeleccionMultiple)
        listaSeries = ArrayList()
        val listaRespuestas : ArrayList<String> = ArrayList()
        for (i in 0 until respuestas.length()){
            listaRespuestas.add(i, respuestas.getString(i))
        }
        val listaRespuestasAtleta : ArrayList<String> = ArrayList()
        for (i in 0 until respuestasAtleta.length()){
            listaRespuestasAtleta.add(i, respuestasAtleta.getString(i))
        }
        val listaRespuestasEntrenador : ArrayList<String> = ArrayList()
        for (i in 0 until respuestasEntrenador.length()){
            listaRespuestasEntrenador.add(i, respuestasEntrenador.getString(i))
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
            val serie = layoutInflater.inflate(R.layout.entrenamiento_series, llSeleccionMultiple, false)
            rellenarSerie(
                serie,
                0,
                listaRespuestasAtleta,
                listaRespuestasEntrenador,
                0,
                0
            )
            listaSeries.add(serie)
            llSeleccionMultiple.addView(serie)
        }else{
            val respuestaSeries = respuestasUsuario[indicePregunta] // 'numero de series | Opcion Atleta | Opcion Entrenador |
            val numeroSeries = respuestaSeries.split('|')[0]
            textOpcion.text = numeroSeries
            seekbar.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
            seekbar.progress = listaRespuestas.indexOf(numeroSeries)
            //TODO: respuesas series
            for (i in 0 until numeroSeries.toInt()){
                val respuestaAtleta = respuestaSeries.split('|')[2*i + 1].toInt()
                val respuestaEntrenador = respuestaSeries.split('|')[2*i + 2].toInt()
                val serie = layoutInflater.inflate(R.layout.entrenamiento_series, llSeleccionMultiple, false)
                rellenarSerie(serie, i, listaRespuestasAtleta, listaRespuestasEntrenador, respuestaAtleta - 1,  respuestaEntrenador - 1)
                listaSeries.add(serie)
                llSeleccionMultiple.addView(serie)
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOpcion.text = listaRespuestas[progress]
                for(serie in listaSeries){
                    llSeleccionMultiple.removeView(serie)
                }
                listaSeries.clear()
                var serie: View
                for (i in 0 .. progress){
                    serie = layoutInflater.inflate(R.layout.entrenamiento_series, llSeleccionMultiple, false)
                    rellenarSerie(
                        serie,
                        i,
                        listaRespuestasAtleta,
                        listaRespuestasEntrenador,
                        0,
                        0
                    )
                    listaSeries.add(serie)
                    llSeleccionMultiple.addView(serie)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun rellenarSerie(
        serie: View,
        i: Int,
        listaRespuestasAtleta: ArrayList<String>,
        listaRespuestasEntrenador: ArrayList<String>,
        respuestaAtleta: Int,
        respuestaEntrenador: Int
    ) {
        val textSerie = serie.findViewById<TextView>(R.id.textSerie)
        textSerie.text = "Serie " + (i + 1)
        val seekbarAtleta = serie.findViewById<SeekBar>(R.id.seekbarAtleta)
        seekbarAtleta.max = listaRespuestasAtleta.size - 1
        seekbarAtleta.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
        seekbarAtleta.progress = respuestaAtleta
        val textOpcionAtleta = serie.findViewById<TextView>(R.id.textOpcionAtleta)
        textOpcionAtleta.text = listaRespuestasAtleta[respuestaAtleta]
        seekbarAtleta.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textOpcionAtleta.text = listaRespuestasAtleta[p1]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        val seekbarEntrenador = serie.findViewById<SeekBar>(R.id.seekbarEntrenador)
        seekbarEntrenador.max = listaRespuestasEntrenador.size - 1
        seekbarEntrenador.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
        seekbarEntrenador.progress = respuestaEntrenador
        val textOpcionEntrenador = serie.findViewById<TextView>(R.id.textOpcionEntrenador)
        textOpcionEntrenador.text = listaRespuestasEntrenador[respuestaEntrenador]
        seekbarEntrenador.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textOpcionEntrenador.text = listaRespuestasEntrenador[p1]
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