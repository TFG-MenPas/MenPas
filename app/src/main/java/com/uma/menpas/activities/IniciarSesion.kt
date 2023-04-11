package com.uma.menpas.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.uma.menpas.R
import com.uma.menpas.controllers.InicioSesionController
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import java.time.LocalDateTime

class IniciarSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val textOlvidar = findViewById<TextView>(R.id.textOlvidar)
        val textRegistrar = findViewById<TextView>(R.id.textRegistrese)
        val textCuestionario = findViewById<TextView>(R.id.textCuestionarios)
        val buttonIniciarSesion = findViewById<Button>(R.id.buttonIniciar)
        val layout = findViewById<RelativeLayout>(R.id.relativeInicioSesion)
        val editTextUsuario = findViewById<EditText>(R.id.editUsuario)
        val editTextContrasenya= findViewById<EditText>(R.id.editContrasenya)

        val snackbarComprobacion = Snackbar.make(layout, R.string.datos_incorrectos, 2000)
        snackbarComprobacion.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(
                Color.WHITE
            )

        lateinit var intent: Intent

        val room: UsuarioDB = Room.databaseBuilder(this, UsuarioDB::class.java, "usuario").allowMainThreadQueries().build()
        val usuario = InicioSesionController.comprobarUsuario("prueba1111111", "prueba1111111")
        val usuarioPrueba = Usuario(
            "nombreUsuario",
            "c",
            "n",
            "ap",
            10,
            "956",
            "2684",
            "M",
            LocalDateTime.now().toString(),
            "GENERAL",
            "email",
            "ninguno",
            "A",
        "Nigeriana",
            "CASADO",
            10,
            "EGB",
            "ASESINO",
            1990
        )

        textOlvidar.setOnClickListener {
            intent = Intent(this, RecuperarContrasenya::class.java)
            startActivity(intent)
        }

        textRegistrar.setOnClickListener {
            intent = Intent(this, RegistroUsuario::class.java)
            //room.UsuarioDAO().deleteUsuario(usuarioPrueba)
            startActivity(intent)
        }

        textCuestionario.setOnClickListener {
            intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

        buttonIniciarSesion.setOnClickListener {
            val entradaUsuario = editTextUsuario.text.toString()
            val entradaContrasenya = editTextContrasenya.text.toString()
            if (InicioSesionController.validarDatos(entradaUsuario, entradaContrasenya)){
                //val user = InicioSesionController.comprobarUsuario("menpasprueba", "menpasprueba")
                val user = InicioSesionController.comprobarUsuario(entradaUsuario, entradaContrasenya)
                if(user == null){
                    snackbarComprobacion.show()
                }else{
                    //room.UsuarioDAO().insertUsuario(user)
                    intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)
                }
            }else{
                snackbarComprobacion.show()
            }
        }

    }
}