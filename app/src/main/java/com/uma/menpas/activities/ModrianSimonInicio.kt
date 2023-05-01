package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.uma.menpas.R

class ModrianSimonInicio : AppCompatActivity() {

    lateinit var botonComenzar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_simon_inicio)

        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, ModrianSimon::class.java)
            startActivity(intent)
        }
    }
}