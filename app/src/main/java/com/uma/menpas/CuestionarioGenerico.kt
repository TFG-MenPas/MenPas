package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout

class CuestionarioGenerico : AppCompatActivity() {
    lateinit var rlDinamico: RelativeLayout
    lateinit var btnSi: Button
    lateinit var btnNo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_generico)

        rlDinamico = findViewById(R.id.RLDynamicContent)
        val cuestionarioSiNo: View = layoutInflater.inflate(R.layout.cuestionario_si_no, rlDinamico, false)
        rlDinamico.addView(cuestionarioSiNo)

        btnSi = findViewById(R.id.buttonSi)
        btnNo = findViewById(R.id.buttonNo)

        val checked = getDrawable(R.drawable.icon_checked)
        val backChecked = getDrawable(R.drawable.rounded_checkbox_checked)

        btnSi.setOnClickListener {
            btnSi.background = backChecked
            btnSi.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,checked,null)
        }
    }
}