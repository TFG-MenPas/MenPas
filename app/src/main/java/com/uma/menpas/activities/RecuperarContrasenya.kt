package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.Toast
import com.uma.menpas.R

class RecuperarContrasenya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contrasenya)

        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)

        buttonEnviar.setOnClickListener {
            Toast.makeText(applicationContext, "Recuperación realizada con éxito", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }
}