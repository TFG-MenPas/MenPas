package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.MenuPrincipalController

class MenuPrincipal : AppCompatActivity() {
    val menuPrincipalController = MenuPrincipalController()

    lateinit var textNombreUsuario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal_v2)

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

        textNombreUsuario = findViewById(R.id.textNombreUsuario)
        textNombreUsuario.text = menuPrincipalController.getNombre(this).toString()

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

    override fun onRestart() {
        super.onRestart()
        actualizarNombreUsuario()
        //When BACK BUTTON is pressed, the activity on the stack is restarted
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        actualizarNombreUsuario()
    }

    fun abrirArea(id: Int) {
        val nombre = resources.getResourceName(id).split(":id/")[1]
        val intent = Intent(this, Area::class.java)
        when(nombre){
            "autorregistro" -> intent.putExtra("area", "Autorregistro");
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

    fun actualizarNombreUsuario() {
        textNombreUsuario.text = menuPrincipalController.getNombre(this).toString()
    }
}