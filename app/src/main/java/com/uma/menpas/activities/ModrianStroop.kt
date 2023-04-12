package com.uma.menpas.activities

import android.content.Intent
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
    lateinit var tiempoEspera : EditText
    lateinit var tiempoRealizacion : EditText
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_stroop)
        barraModalidad()
        botones()
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
        val checked = AppCompatResources.getDrawable(this, R.drawable.icon_checked)
        val backChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox_checked)
        val unchecked = AppCompatResources.getDrawable(this, R.drawable.icon_unchecked)
        val backunChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox)

        btnFondo = findViewById(R.id.button_fondo)
        var btnFondoChecked = false

        val colores = mutableMapOf<Button, Boolean>()
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
            val intent = Intent(this, MondrianColoresGrid::class.java)
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

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}