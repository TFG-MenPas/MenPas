package com.uma.menpas

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView

class CuestionarioGenerico : AppCompatActivity() {
    lateinit var rlDinamico: RelativeLayout
    lateinit var btnSi: Button
    lateinit var btnNo: Button
    lateinit var seekbar: SeekBar
    lateinit var textOpcion: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_generico)

        rlDinamico = findViewById(R.id.RLDynamicContent)
        //val cuestionarioSiNo: View = layoutInflater.inflate(R.layout.cuestionario_si_no, rlDinamico, false)
        //rlDinamico.addView(cuestionarioSiNo)
        val cuestionarioSeleccionMultiple: View = layoutInflater.inflate(R.layout.cuestionario_seleccion_multiple, rlDinamico, false)
        rlDinamico.addView(cuestionarioSeleccionMultiple)

        textOpcion = findViewById(R.id.textOpcion)
        seekbar = findViewById(R.id.seekbar)
        seekbar.progress = 0
        seekbar.max = 4

        val tickbar_enabled = getDrawable(R.drawable.tickbar_enabled)
        val tickbar_disabled = getDrawable(R.drawable.tickbar_disabled)

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

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

    }
    private fun cuestionarioSiNo(){
        btnSi = findViewById(R.id.buttonSi)
        btnNo = findViewById(R.id.buttonNo)

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