package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R

class DudasSugerencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dudas_sugerencias)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)

        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_left)
        BarraNavegacion(barraNavegacionInferior, this)

        buttonEnviar.setOnClickListener {
            Toast.makeText(this, "Enviado con exito", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}