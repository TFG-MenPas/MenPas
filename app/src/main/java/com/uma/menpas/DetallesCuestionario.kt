package com.uma.menpas

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

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