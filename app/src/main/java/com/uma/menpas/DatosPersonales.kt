package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast

class DatosPersonales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)

        val botonEditarNombreDeUsuario = findViewById<ImageButton>(R.id.botonEditarNombreDeUsuario)
        val botonEditarNombre = findViewById<ImageButton>(R.id.botonEditarNombre)
        val botonEditarApellidos = findViewById<ImageButton>(R.id.botonEditarApellidos)
        val checkBoxSuscripcion = findViewById<CheckBox>(R.id.checkBoxSuscripcion)

        botonEditarNombreDeUsuario.setOnClickListener{
            Toast.makeText(applicationContext, "Edita el nombre de usuario", Toast.LENGTH_SHORT)
                .show()
        }

        botonEditarNombre.setOnClickListener{
            Toast.makeText(applicationContext, "Edita el nombre", Toast.LENGTH_SHORT)
                .show()
        }

        botonEditarApellidos.setOnClickListener{
            Toast.makeText(applicationContext, "Edita los apellidos", Toast.LENGTH_SHORT)
                .show()
        }

        checkBoxSuscripcion.setOnClickListener{
            if(checkBoxSuscripcion.isChecked){
                Toast.makeText(applicationContext, "Suscripción activada", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(applicationContext, "Suscripción desactivada", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}