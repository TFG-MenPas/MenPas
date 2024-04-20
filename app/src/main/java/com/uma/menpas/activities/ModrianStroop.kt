package com.uma.menpas.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R
import com.uma.menpas.utils.Tiempo

class ModrianStroop : AppCompatActivity() {
    lateinit var textOpcion : TextView
    lateinit var seekbar : SeekBar
    lateinit var btnFondo : Button
    lateinit var botonComenzar : Button
    lateinit var botonCerrarCuestionario: ImageButton
    lateinit var tiempoEspera : EditText
    lateinit var tiempoRealizacion : EditText
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var colores : MutableMap<Button,Boolean>
    private lateinit var checked : Drawable
    private lateinit var backChecked : Drawable
    private lateinit var unchecked : Drawable
    private lateinit var backunChecked : Drawable
    private var btnFondoChecked = false
    lateinit var numeroFallosPermitidos: TextView
    private lateinit var textOpcionTamanyoTablero: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_stroop)
        checked = AppCompatResources.getDrawable(this, R.drawable.icon_checked)!!
        backChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox_checked)!!
        unchecked = AppCompatResources.getDrawable(this, R.drawable.icon_unchecked)!!
        backunChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox)!!
        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarCuestionario)
        botonCerrarCuestionario.setOnClickListener {
            finish()
        }
        InicializarSeekbarFallosPermitidos()
        seekbarTamanyoTablero()
        barraModalidad()
        botones()
    }

    private fun InicializarSeekbarFallosPermitidos() {
        numeroFallosPermitidos = findViewById(R.id.numero_fallos_permitidos)
        val seekbarFallosPermitidos: SeekBar = findViewById(R.id.seekbar_fallos_permitidos)
        seekbarFallosPermitidos.progress = 0
        seekbarFallosPermitidos.max = 3

        val respuestas = arrayOf("25% Matriz", "50% Matriz", "75% Matriz", "Sin control de fallos")

        seekbarFallosPermitidos.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    numeroFallosPermitidos.text = respuestas[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun seekbarTamanyoTablero() {
        textOpcionTamanyoTablero = findViewById(R.id.textOpcionTamanyoTablero)
        val seekbarTamanyoTablero: SeekBar = findViewById(R.id.seekbarTamanyoTablero)
        seekbarTamanyoTablero.progress = 0
        seekbarTamanyoTablero.max = 2

        val respuestas = arrayOf("Pequeño", "Mediano", "Grande")

        seekbarTamanyoTablero.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOpcionTamanyoTablero.text = respuestas[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    override fun onResume() {
        super.onResume()
        refreshButtonDrawables()
        when(textOpcion.text.toString()){
            "Congruente" -> seekbar.progress = 0
            "Incongruente" -> seekbar.progress = 1
            "Mixto" -> seekbar.progress = 2
        }
    }

    private fun barraModalidad(){
        textOpcion = findViewById(R.id.modalidad_stroop)
        seekbar = findViewById(R.id.seekbar_stroop)
        seekbar.progress = 0
        seekbar.max = 2

        val respuestas = arrayOf("Congruente", "Incongruente", "Mixto")

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress) {
                    0 -> textOpcion.text = respuestas[0]
                    1 -> textOpcion.text = respuestas[1]
                    2 -> textOpcion.text = respuestas[2]
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }
    private fun botones(){
        btnFondo = findViewById(R.id.button_fondo)
        btnFondoChecked = false

        colores = mutableMapOf()
        colores[findViewById(R.id.button_rojo)] = true
        colores[findViewById(R.id.button_marron)] = true
        colores[findViewById(R.id.button_verde)] = true
        colores[findViewById(R.id.button_gris)] = true
        colores[findViewById(R.id.button_azul)] = true
        colores[findViewById(R.id.button_rosa)] = true
        colores[findViewById(R.id.button_amarillo)] = true
        colores[findViewById(R.id.button_violeta)] = true
        colores[findViewById(R.id.button_negro)] = true
        colores[findViewById(R.id.button_fucsia)] = true
        colores[findViewById(R.id.button_naranja)] = true
        colores[findViewById(R.id.button_cyan)] = true
        colores[findViewById(R.id.button_blanco)] = true

        colores.entries.forEach{
                color ->
            color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
            color.key.background = backChecked
            color.key.setOnClickListener {
                if (!color.value){
                    color.key.background = backChecked
                    color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                    color.setValue(true)
                } else {
                    color.key.background = backunChecked
                    color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                    color.setValue(false)
                }
            }
        }

        btnFondo.setOnClickListener {
            if (!btnFondoChecked){
                btnFondo.background = backChecked
                btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnFondoChecked = true
            }else{
                btnFondo.background = backunChecked
                btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnFondoChecked = false
            }
        }

        tiempoEspera = findViewById(R.id.editTextTiempoEspera)
        tiempoRealizacion = findViewById(R.id.editTextTiempoRealizacion)
        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, ModrianStroopGrid::class.java)
            arrayColores = ArrayList()
            arrayEliminar = ArrayList()
            colores.entries.forEach {color ->
                val textColor = color.key.text as String
                if (color.value){
                    arrayColores.add(textColor.lowercase())
                }else{
                    arrayEliminar.add(textColor.lowercase())
                }
            }

            if (arrayColores.size >= 2 && Tiempo.esValido(tiempoEspera.text.toString()) && Tiempo.esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = Tiempo.stringToMilis(tiempoRealizacion.text.toString())
                val longTiempoEspera = Tiempo.stringToMilis(tiempoEspera.text.toString())
                intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                intent.putExtra("longTiempoEspera", longTiempoEspera)
                intent.putExtra("arrayColores", arrayColores)
                intent.putExtra("arrayEliminar", arrayEliminar)
                intent.putExtra("tipoStroop", textOpcion.text)
                intent.putExtra("fondo", btnFondoChecked)
                intent.putExtra("fallosPermitidos", numeroFallosPermitidos.text)
                intent.putExtra("tamanyoTablero", textOpcionTamanyoTablero.text)
                startActivity(intent)
            }else if(arrayColores.size < 2){
                showToast("Selecciona mínimo 2 colores")
            }else if(!Tiempo.esValido(tiempoRealizacion.text.toString())){
                showToast("Introduzca un tiempo de realizacion valido")
            }else if(!Tiempo.esValido(tiempoEspera.text.toString())){
                showToast("Introduzca un tiempo de espera valido")
            }
        }
    }

    private fun refreshButtonDrawables(){
        colores.entries.forEach{
                color ->
            color.key.background = null
            if(color.value){
                color.key.background = backChecked
                color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
            }else{
                color.key.background = backunChecked
                color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
            }
        }
        btnFondo.background = null
        if (btnFondoChecked){
            btnFondo.background = backChecked
            btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
        }else{
            btnFondo.background = backunChecked
            btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}