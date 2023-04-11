package com.uma.menpas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        val entrenamiento_mental = findViewById<CardView>(R.id.entrenamiento_mental)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        BarraNavegacion(barraNavegacionInferior, this)

        autorregistro.setOnClickListener {
            abrirArea(it.id)
        }

        ansiedad.setOnClickListener {
            abrirArea(it.id)
        }

        autoconcepto.setOnClickListener {
            abrirArea(it.id)
        }

        atencion.setOnClickListener {
            abrirArea(it.id)
        }
        busqueda_talentos.setOnClickListener {
            abrirArea(it.id)
        }

        burnout.setOnClickListener {
            abrirArea(it.id)
        }

        dinamica_grupal.setOnClickListener {
            abrirArea(it.id)
        }

        calidad_vida.setOnClickListener {
            abrirArea(it.id)
        }

        inteligencia_emocional.setOnClickListener {
            abrirArea(it.id)
        }

        entrenamiento_mental.setOnClickListener {
            abrirArea(it.id)
        }
    }

    override fun onBackPressed(){

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "Intent reciclado", Toast.LENGTH_SHORT).show()
    }

    fun abrirArea(id: Int) {
        var nombre = resources.getResourceName(id).split(":id/")[1]
        var intent = Intent(this, Area::class.java)
        when(nombre){
            "autorregistro" -> intent.putExtra("area", "Autorregistro")
            "ansiedad" -> intent.putExtra("area", "Ansiedad")
            "autoconcepto" -> intent.putExtra("area", "Autoconcepto")
            "atencion" -> intent.putExtra("area", "Atención")
            "busqueda_talentos" -> intent.putExtra("area", "Busqueda de talentos")
            "burnout" -> intent.putExtra("area", "Burnout")
            "dinamicagrupal" -> intent.putExtra("area", "Dinámica grupal")
            "calidadvida" -> intent.putExtra("area", "Calidad de vida")
            "inteligencia_emocional" -> intent.putExtra("area", "Inteligencia emocional")
            "entrenamiento_mental" -> intent.putExtra("area", "Entrenamiento mental")
        }
        startActivity(intent)
    }
}