package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class DatosPersonales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)

        val botonEditarNombreDeUsuario = findViewById<ImageButton>(R.id.botonEditarNombreDeUsuario)
        val botonEditarNombre = findViewById<ImageButton>(R.id.botonEditarNombre)
        val botonEditarApellidos = findViewById<ImageButton>(R.id.botonEditarApellidos)
        val checkBoxSuscripcion = findViewById<CheckBox>(R.id.checkBoxSuscripcion)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

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

        barraNavegacionInferior.getMenu().getItem(0).setCheckable(false); // LINEA PROVISIONAL PARA QUITAR EL FOCUS INICIAL DEL PRIMER ELEMENTO
        barraNavegacionInferior.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_faq -> {
                    it.setCheckable(true); // LINEA PROVISIONAL PARA DEVOLVER EL CHECKABLE AL PRIMER ELEMENTO
                    Toast.makeText(applicationContext, "FAQ", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                        Toast.makeText(applicationContext, "HOME", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_user -> {
                    Toast.makeText(applicationContext, "USER", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}