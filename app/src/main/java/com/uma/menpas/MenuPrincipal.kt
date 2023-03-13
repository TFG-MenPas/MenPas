package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val autorregistro = findViewById<CardView>(R.id.autorregistro)
        val ansiedad = findViewById<CardView>(R.id.ansiedad)
        val autoconcepto = findViewById<CardView>(R.id.autoconcepto)
        val atencion = findViewById<CardView>(R.id.atencion)
        val busqueda_talentos = findViewById<CardView>(R.id.busqueda_talentos)
        val burnout = findViewById<CardView>(R.id.burnout)
        val dinamica_grupal = findViewById<CardView>(R.id.dinamicagrupal)
        val calidad_vida = findViewById<CardView>(R.id.calidadvida)
        val inteligencia_emocional = findViewById<CardView>(R.id.inteligencia_emocional)

        autorregistro.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Autorregistro", Toast.LENGTH_SHORT).show()
        }

        ansiedad.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Ansiedad", Toast.LENGTH_SHORT).show()
        }

        autoconcepto.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Autoconcepto", Toast.LENGTH_SHORT).show()
        }

        atencion.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Atención", Toast.LENGTH_SHORT).show()
        }
        busqueda_talentos.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Búsqueda de Talentos", Toast.LENGTH_SHORT).show()
        }

        burnout.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Burnout", Toast.LENGTH_SHORT).show()
        }

        dinamica_grupal.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Dinámica Grupal", Toast.LENGTH_SHORT).show()
        }

        calidad_vida.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Calidad de Vida", Toast.LENGTH_SHORT).show()
        }

        inteligencia_emocional.setOnClickListener {
            Toast.makeText(applicationContext, "Sección Inteligencia Emocional", Toast.LENGTH_SHORT).show()
        }


    }
}