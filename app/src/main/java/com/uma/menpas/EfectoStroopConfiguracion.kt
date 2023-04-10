package com.uma.menpas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EfectoStroopConfiguracion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_efecto_stroop_configuracion)
        botones()
        barraModalidad()
    }

    private fun barraModalidad(){
        val textOpcion : TextView = findViewById(R.id.modalidad_stroop)
        val seekbar : SeekBar = findViewById(R.id.seekbar_stroop)
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
        val checked = getDrawable(R.drawable.icon_checked)
        val backChecked = getDrawable(R.drawable.rounded_checkbox_checked)
        val unchecked = getDrawable(R.drawable.icon_unchecked)
        val backunChecked = getDrawable(R.drawable.rounded_checkbox)

        val btn_fondo : Button = findViewById(R.id.button_fondo)
        var btnChecked = false

        val colores = mutableMapOf<Button, Boolean>()
        colores.put(findViewById(R.id.button_rojo) as Button, true)
        colores.put(findViewById(R.id.button_marron) as Button, true)
        colores.put(findViewById(R.id.button_verde) as Button, true)
        colores.put(findViewById(R.id.button_gris) as Button, true)
        colores.put(findViewById(R.id.button_azul) as Button, true)
        colores.put(findViewById(R.id.button_rosa) as Button, true)
        colores.put(findViewById(R.id.button_amarillo) as Button, true)
        colores.put(findViewById(R.id.button_violeta) as Button, true)
        colores.put(findViewById(R.id.button_negro) as Button, true)
        colores.put(findViewById(R.id.button_fucsia) as Button, true)
        colores.put(findViewById(R.id.button_naranja) as Button, true)
        colores.put(findViewById(R.id.button_blanco) as Button, true)
        colores.put(findViewById(R.id.button_cyan) as Button, true)

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

        btn_fondo.setOnClickListener {
            if (!btnChecked){
                btn_fondo.background = backChecked
                btn_fondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnChecked = true
            }else{
                btn_fondo.background = backunChecked
                btn_fondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnChecked = false
            }
        }
    }
}