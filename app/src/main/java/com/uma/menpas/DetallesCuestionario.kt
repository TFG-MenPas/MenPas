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

        buttonCerrar.setOnClickListener {
            Toast.makeText(this, "Botón de cerrar", Toast.LENGTH_SHORT).show()
        }

        barraNavegacionInferior.menu.getItem(0).isCheckable = false;
        barraNavegacionInferior.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_faq -> {
                    it.isCheckable = true; // LINEA PROVISIONAL PARA DEVOLVER EL CHECKABLE AL PRIMER ELEMENTO
                    Toast.makeText(applicationContext, "FAQ", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    Toast.makeText(applicationContext, "HOME", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_user -> {
                    Toast.makeText(applicationContext, "USER", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}