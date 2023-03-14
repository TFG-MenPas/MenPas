package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class IniciarSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val textOlvidar = findViewById<TextView>(R.id.textOlvidar)
        val textRegistrar = findViewById<TextView>(R.id.textRegistrese)
        val textCuestionario = findViewById<TextView>(R.id.textCuestionarios)
        val buttonIniciarSesion = findViewById<Button>(R.id.buttonIniciar)

        textOlvidar.setOnClickListener {
            Toast.makeText(this, "Olvidar contraseña", Toast.LENGTH_SHORT).show()
        }

        textRegistrar.setOnClickListener {
            Toast.makeText(this, "Registrese aquí", Toast.LENGTH_SHORT).show()
        }

        textCuestionario.setOnClickListener {
            Toast.makeText(this, "Cuestionarios anónimos", Toast.LENGTH_SHORT).show()
        }

        buttonIniciarSesion.setOnClickListener {
            Toast.makeText(this, "Iniciar sesion", Toast.LENGTH_SHORT).show()
        }
    }
}