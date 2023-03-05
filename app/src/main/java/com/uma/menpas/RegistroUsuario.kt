package com.uma.menpas

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mostrarOcultarPassword = findViewById<ImageButton>(R.id.show_hide_password)
        val hideShowRepPassword = findViewById<ImageButton>(R.id.show_hide_rep_password)

        val usuario = findViewById<EditText>(R.id.usuario)
        val password = findViewById<EditText>(R.id.password)
        val repPassword = findViewById<EditText>(R.id.rep_password)

        val terminos = findViewById<TextView>(R.id.terminos)
        val iniSesion = findViewById<TextView>(R.id.textInicieSesion)

        val registrar = findViewById<Button>(R.id.buttonRegistrar)

        var isPasswordVisible = false

        mostrarOcultarPassword.setOnClickListener {
            if (!isPasswordVisible) {
                password.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                password.typeface = usuario.typeface
                mostrarOcultarPassword.setImageResource(R.drawable.hide_icon_v3)
            } else {
                password.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                password.typeface = usuario.typeface
                mostrarOcultarPassword.setImageResource(R.drawable.show_icon_v3)
            }
            password.setSelection(password.text.length)
            isPasswordVisible = !isPasswordVisible

        }

        var isRepPasswordVisible = false

        hideShowRepPassword.setOnClickListener {
            if (!isRepPasswordVisible) {
                repPassword.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                repPassword.typeface = usuario.typeface
                hideShowRepPassword.setImageResource(R.drawable.hide_icon_v3)
            } else {
                repPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                repPassword.typeface = usuario.typeface
                hideShowRepPassword.setImageResource(R.drawable.show_icon_v3)
            }
            repPassword.setSelection(repPassword.text.length)
            isRepPasswordVisible = !isRepPasswordVisible
        }

        terminos.setOnClickListener {
            Toast.makeText(applicationContext, "Terminos y Condiciones de Uso", Toast.LENGTH_SHORT)
                .show()
        }

        iniSesion.setOnClickListener {
            Toast.makeText(applicationContext, "Activity Iniciar Sesion", Toast.LENGTH_SHORT).show()
        }

        registrar.setOnClickListener {
            Toast.makeText(applicationContext, "Registro realizado con éxito", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
