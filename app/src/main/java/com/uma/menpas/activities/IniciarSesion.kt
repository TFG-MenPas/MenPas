package com.uma.menpas.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.uma.menpas.R
import com.uma.menpas.controllers.InicioSesionController
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.services.UsuarioService
import org.w3c.dom.Text
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

        val snackbarComprobacion = Snackbar.make(layout, R.string.datos_incorrectos, 2000)
        snackbarComprobacion.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(
                Color.WHITE
            )

        lateinit var intent: Intent
        //InicioSesionController.comprobarUsuario("falso", "falso")

        val room: UsuarioDB = Room.databaseBuilder(this, UsuarioDB::class.java, "usuario").allowMainThreadQueries().build()
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
            //startActivity(intent)
            room.UsuarioDAO().insertUsuario(usuarioPrueba)
        }

        textRegistrar.setOnClickListener {
            intent = Intent(this, RegistroUsuario::class.java)
            room.UsuarioDAO().deleteUsuario(usuarioPrueba)
            //startActivity(intent)
        }

        textCuestionario.setOnClickListener {
            intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        buttonIniciarSesion.setOnClickListener {
            intent = Intent(this, MenuPrincipal::class.java)
            //snackbarComprobacion.show()
            val usuario = room.UsuarioDAO().getUsuario()
            if(usuario == null){
                Toast.makeText(this, "USUARIO NULO", Toast.LENGTH_SHORT).show()
            }else{
                Log.v("USUARIO", usuario.toString())
                Toast.makeText(this, "USUARIO EXISTE", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }

        }

    }
}