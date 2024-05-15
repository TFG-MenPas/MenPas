package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.uma.menpas.R

class ModrianSimonInicio : BaseActivity() {

    lateinit var botonComenzar : Button
    private lateinit var usuario: String
    private lateinit var botonCerrar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_simon_inicio)
        usuario = intent.getStringExtra("usuario") as String

        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, ModrianSimon::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
        botonCerrar = findViewById(R.id.imageButtonCerrarDesplegable)
        botonCerrar.setOnClickListener {
            finish()
        }
    }
}