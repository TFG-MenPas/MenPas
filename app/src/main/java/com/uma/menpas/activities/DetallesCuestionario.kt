package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R

class DetallesCuestionario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_cuestionario)

        var buttonCerrar = findViewById<ImageButton>(R.id.buttonCerrar)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        BarraNavegacion(barraNavegacionInferior, this)

        buttonCerrar.setOnClickListener {
            finish()
        }

    }
}