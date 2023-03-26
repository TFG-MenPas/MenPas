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
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        BarraNavegacion(barraNavegacionInferior, this)

        buttonRegistrarCentro.setOnClickListener {
            Toast.makeText(applicationContext, "Centro registrado con éxito", Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        //Spinner paises
        val paisesSpinner = findViewById<Spinner>(R.id.selectPais)
        val paisesArrayAdapter = ArrayAdapter.createFromResource(this, R.array.paises_array, R.layout.spinner_paises)
        paisesArrayAdapter.setDropDownViewResource(R.layout.spinner_paises)
        paisesSpinner.adapter = paisesArrayAdapter
    }
}