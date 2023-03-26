package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class CuestionarioGenerico : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario_generico)

        val pb : ProgressBar = findViewById(R.id.progressBarCuestionario)

        val tipoCuestionario = "fecha"
        var layoutDinamica = 0

        when (tipoCuestionario) {
            "textoLibre" -> layoutDinamica = R.layout.cuestionario_texto_libre ;
            "fecha" -> layoutDinamica = R.layout.cuestionario_fecha;

            else -> {
                layoutDinamica = -1
            }
        }

        val contenidoDinamico : RelativeLayout = findViewById(R.id.RLDynamicContent)
        val wizardView : View = getLayoutInflater().inflate(layoutDinamica, contenidoDinamico, false)
        contenidoDinamico.addView(wizardView);

    }
}