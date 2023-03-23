package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.ScrollView

class CuestionarioGenerico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_generico)

        val pb : ProgressBar = findViewById(R.id.progressBarCuestionario)

        val tipoCuestionario = "textoLibre"
        var layout = 0

        when (tipoCuestionario) {
            "textoLibre" -> layout = R.layout.cuestionario_texto_libre ;


            else -> {
                layout = -1
            }
        }

        val contenidoDinamico : RelativeLayout = findViewById(R.id.RLDynamicContent)
        val wizardView : View = getLayoutInflater().inflate(layout, contenidoDinamico, false)
        contenidoDinamico.addView(wizardView);

    }
}