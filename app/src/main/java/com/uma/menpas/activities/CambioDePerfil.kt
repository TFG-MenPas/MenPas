package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R

class CambioDePerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambio_de_perfil)

        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        BarraNavegacion(barraNavegacionInferior, this)

        buttonEnviar.setOnClickListener {
            Toast.makeText(applicationContext, "Solicitud enviada con éxito", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }
}