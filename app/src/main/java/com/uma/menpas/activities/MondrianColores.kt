package com.uma.menpas.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.uma.menpas.R
import java.util.concurrent.TimeUnit

class MondrianColores : AppCompatActivity() {
    lateinit var botonComenzar : Button
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var tiempoEspera : EditText
    lateinit var tiempoRealizacion : EditText
    lateinit var colores : MutableMap<Button,Boolean>
    private lateinit var checked : Drawable
    private lateinit var backChecked : Drawable
    private lateinit var unchecked : Drawable
    private lateinit var backunChecked : Drawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores)

        checked = AppCompatResources.getDrawable(this, R.drawable.icon_checked)!!
        backChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox_checked)!!
        unchecked = AppCompatResources.getDrawable(this, R.drawable.icon_unchecked)!!
        backunChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox)!!
        botones()
    }

    private fun botones(){
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
            if (arrayColores.size >= 2 && esValido(tiempoEspera.text.toString()) && esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = stringToMilis(tiempoRealizacion.text.toString())
                val longTiempoEspera = stringToMilis(tiempoEspera.text.toString())
                intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                intent.putExtra("longTiempoEspera", longTiempoEspera)
                intent.putExtra("arrayColores", arrayColores)
                intent.putExtra("arrayEliminar", arrayEliminar)
                startActivity(intent)
            }else if(arrayColores.size < 2){
                showToast("Selecciona mínimo 2 colores")
            }else if(!esValido(tiempoRealizacion.text.toString())){
                showToast("Introduzca un tiempo de realizacion valido")
            }else if(!esValido(tiempoEspera.text.toString())){
                showToast("Introduzca un tiempo de espera valido")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshButtonDrawables()
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
    }

    private fun esValido(tiempo: String): Boolean {
        if(tiempo.contains(":")){
            val minSecArray = tiempo.split(":")
            val min = minSecArray[0]
            val sec = minSecArray[1]
            return min.length == 2 && sec.length == 2
        }else{
            return false
        }
    }

    private fun stringToMilis(tiempo: String): Long {
        val minSecArray = tiempo.split(":")
        val min = minSecArray[0]
        val sec = minSecArray[1]
        val minMillis = TimeUnit.MINUTES.toMillis(min.toLong())
        val secMillis = TimeUnit.SECONDS.toMillis(sec.toLong())
        return minMillis + secMillis
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}