package com.uma.menpas

import android.content.Intent
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
        lateinit var intent: Intent

        textOlvidar.setOnClickListener {
            intent = Intent(this, RecuperarContrasenya::class.java)
            startActivity(intent)
        }

        textRegistrar.setOnClickListener {
            intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        textCuestionario.setOnClickListener {
            intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        buttonIniciarSesion.setOnClickListener {
            intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}