package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.EmailController

class DudasSugerencias : BaseActivity() {

    private val emailController = EmailController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dudas_sugerencias)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)

        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editCorreo = findViewById<EditText>(R.id.editCorreo)
        val editAsunto = findViewById<EditText>(R.id.editAsunto)
        val editMensaje = findViewById<EditText>(R.id.editMensaje)

        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_left)
        BarraNavegacion(barraNavegacionInferior, this)

        buttonEnviar.setOnClickListener {
            val nombre = editNombre.text.toString()
            val correo = editCorreo.text.toString()
            val mensaje = editMensaje.text.toString()
            val asunto = editAsunto.text.toString()
            if(sonValidosYNoVacios(nombre, correo, mensaje, asunto)){
                if (emailController.enviarDudaSugerencia(nombre,
                        correo,
                        mensaje,
                        asunto)) {
                    Toast.makeText(this, "Enviado con exito", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al enviar el mensaje", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Uno o varios campos están incompletos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sonValidosYNoVacios(nombre: String, correo: String, mensaje: String, asunto: String): Boolean {
        return nombre.isNotBlank() && correo.isNotBlank() && mensaje.isNotBlank() && asunto.isNotBlank()
    }
}