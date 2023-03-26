package com.uma.menpas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class BajaUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baja_usuario)

        val buttonDarseDeBaja = findViewById<Button>(R.id.buttonDarseDeBaja)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        lateinit var intent: Intent

        BarraNavegacion(barraNavegacionInferior, this)

        buttonDarseDeBaja.setOnClickListener {
            Toast.makeText(applicationContext, "Baja realizada con éxito", Toast.LENGTH_SHORT)
                .show()
            intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
            finish()
        }

        //Spinner motivos
        val motivosSpinner = findViewById<Spinner>(R.id.selectMotivo)
        val motivosArrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.motivos_baja_array,
            R.layout.spinner_motivos_baja_usuario
        )
        motivosArrayAdapter.setDropDownViewResource(R.layout.spinner_motivos_baja_usuario)
        motivosSpinner.adapter = motivosArrayAdapter
    }
}