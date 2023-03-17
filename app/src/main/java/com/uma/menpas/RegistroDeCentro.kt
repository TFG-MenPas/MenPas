package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class RegistroDeCentro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_de_centro)

        val buttonRegistrarCentro = findViewById<Button>(R.id.buttonRegistrarCentro)

        buttonRegistrarCentro.setOnClickListener {
            Toast.makeText(applicationContext, "Centro registrado con éxito", Toast.LENGTH_SHORT)
                .show()
        }

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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

        //Spinner paises
        val paisesSpinner = findViewById<Spinner>(R.id.selectPais)
        val paisesArrayAdapter = ArrayAdapter.createFromResource(this, R.array.paises_array, R.layout.spinner_motivos_baja_usuario)
        paisesArrayAdapter.setDropDownViewResource(R.layout.spinner_motivos_baja_usuario)
        paisesSpinner.adapter = paisesArrayAdapter
    }
}