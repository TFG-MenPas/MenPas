package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.uma.menpas.R

class CuestionarioGenerico : AppCompatActivity() {
    lateinit var rlDinamico: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_generico)

        val tipoCuestionario = "seleccionMultiple"
        var layoutDinamica = 0

        when (tipoCuestionario) {
            "textoLibre" -> layoutDinamica = R.layout.cuestionario_texto_libre
            "fecha" -> layoutDinamica = R.layout.cuestionario_fecha
            "siNo" -> layoutDinamica = R.layout.cuestionario_si_no
            "seleccionMultiple" -> layoutDinamica = R.layout.cuestionario_seleccion_multiple
            else -> {
                layoutDinamica = -1
            }
        }

        rlDinamico = findViewById(R.id.RLDynamicContent)
        val cuestionarioDinamico: View = layoutInflater.inflate(layoutDinamica, rlDinamico, false)
        rlDinamico.addView(cuestionarioDinamico)

        when (tipoCuestionario) {
            "textoLibre" -> ""
            "fecha" -> ""
            "siNo" -> cuestionarioSiNo()
            "seleccionMultiple" -> cuestionarioSeleccionMultiple()
        }
    }
    private fun cuestionarioSeleccionMultiple(){
        val textOpcion : TextView = findViewById(R.id.textOpcion)
        val seekbar : SeekBar = findViewById(R.id.seekbar)
        seekbar.progress = 0
        seekbar.max = 4

        val respuestas = arrayOf("Nunca", "Raramente", "A veces", "Casi siempre", "Siempre")

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress) {
                    0 -> textOpcion.text = respuestas[0]
                    1 -> textOpcion.text = respuestas[1]
                    2 -> textOpcion.text = respuestas[2]
                    3 -> textOpcion.text = respuestas[3]
                    4 -> textOpcion.text = respuestas[4]
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun cuestionarioSiNo(){
        val btnSi : Button = findViewById(R.id.buttonSi)
        val btnNo : Button = findViewById(R.id.buttonNo)

        val checked = getDrawable(R.drawable.icon_checked)
        val backChecked = getDrawable(R.drawable.rounded_checkbox_checked)
        val unchecked = getDrawable(R.drawable.icon_unchecked)
        val backunChecked = getDrawable(R.drawable.rounded_checkbox)

        var btnSiChecked = false
        var btnNoChecked = true

        btnSi.setOnClickListener {
            if (!btnSiChecked){
                btnSi.background = backChecked
                btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnSiChecked = true
                if (btnNoChecked){
                    btnNo.background = backunChecked
                    btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                    btnNoChecked = false
                }
            }else{
                btnSi.background = backunChecked
                btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnSiChecked = false
            }
        }
        btnNo.setOnClickListener {
            if (!btnNoChecked){
                btnNo.background = backChecked
                btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnNoChecked = true
                if (btnSiChecked){
                    btnSi.background = backunChecked
                    btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                    btnSiChecked = false
                }
            }else{
                btnNo.background = backunChecked
                btnNo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnNoChecked = false
            }
        }
    }
}