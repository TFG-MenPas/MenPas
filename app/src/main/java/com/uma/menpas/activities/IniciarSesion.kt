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
import com.uma.menpas.controllers.CuestionarioController
import com.uma.menpas.controllers.InicioSesionController
import com.uma.menpas.models.Usuario
import com.uma.menpas.room.UsuarioDB
import com.uma.menpas.utils.SnackBarPersonalizada
import java.time.LocalDateTime

class IniciarSesion : BaseActivity() {
    private val inicioSesionController = InicioSesionController()
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

        isUsuarioGuardado() // Si está guardado, abre el menu principal

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
            intent = Intent(this, Area::class.java)
            intent.putExtra("area", "Cuestionarios Anónimos")
            intent.putExtra("usuario", "anónimo")
            startActivity(intent)
        }

        buttonIniciarSesion.setOnClickListener {
            val entradaUsuario = editTextUsuario.text.toString()
            val entradaContrasenya = editTextContrasenya.text.toString()
            if (inicioSesionController.validarDatos(entradaUsuario, entradaContrasenya)){
                val user = inicioSesionController.comprobarUsuario(entradaUsuario, entradaContrasenya)
                if(user == null){
                    SnackBarPersonalizada.mostrarSnack(layout, this.resources.getString(R.string.datos_incorrectos), 2000)
                }else{
                    inicioSesionController.guardarUsuario(this, user)
                    intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)
                }
            }else{
                SnackBarPersonalizada.mostrarSnack(layout, this.resources.getString(R.string.datos_incorrectos), 2000)
            }
        }
    }

    override fun onRestart() {
        isUsuarioGuardado() // Si está guardado, abre el menu principal
        super.onRestart()

        //When BACK BUTTON is pressed, the activity on the stack is restarted
    }

    private fun isUsuarioGuardado(){
        val usuarioGuardado = inicioSesionController.getUsuarioGuardado(this)
        if(usuarioGuardado != null){
            val usuarioActualizado = inicioSesionController.comprobarUsuario(usuarioGuardado.nombreUsuario, usuarioGuardado.contrasenya)
            inicioSesionController.guardarUsuario(this, usuarioActualizado!!)
            intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}