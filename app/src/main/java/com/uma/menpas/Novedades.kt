package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Novedades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novedades)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BarraNavegacion(barraNavegacionInferior, this)
    }
}