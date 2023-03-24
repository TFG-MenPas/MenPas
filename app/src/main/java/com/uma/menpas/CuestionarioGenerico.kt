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