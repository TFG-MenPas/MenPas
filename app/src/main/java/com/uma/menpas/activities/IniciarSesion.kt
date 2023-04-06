package com.uma.menpas.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.uma.menpas.R
import com.uma.menpas.controllers.InicioSesionController
import com.uma.menpas.services.UsuarioService
import org.w3c.dom.Text

class IniciarSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val textOlvidar = findViewById<TextView>(R.id.textOlvidar)
        val textRegistrar = findViewById<TextView>(R.id.textRegistrese)
        val textCuestionario = findViewById<TextView>(R.id.textCuestionarios)
        val buttonIniciarSesion = findViewById<Button>(R.id.buttonIniciar)
        val layout = findViewById<RelativeLayout>(R.id.relativeInicioSesion)

        val snackbarComprobacion = Snackbar.make(layout, R.string.datos_incorrectos, 2000)
        snackbarComprobacion.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(
                Color.RED
            )

        lateinit var intent: Intent
        //InicioSesionController.comprobarUsuario("falso", "falso")

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
            snackbarComprobacion.show()
            startActivity(intent)
        }

    }
}