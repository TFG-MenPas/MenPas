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
        lateinit var usuario: String
        try {
            usuario = intent.getStringExtra("usuario") as String
        } catch (e: java.lang.NullPointerException) {
            usuario = "user"
        }


        textNombreUsuario = findViewById(R.id.textNombreUsuario)
        textNombreUsuario.text = menuPrincipalController.getNombre(this).toString()

        BarraNavegacion(barraNavegacionInferior, this)

        autorregistro.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        ansiedad.setOnClickListener {
            abrirArea(it.id,usuario)
        }

        autoconcepto.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        atencion.setOnClickListener {
            abrirArea(it.id, usuario)
        }
        busqueda_talentos.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        burnout.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        dinamica_grupal.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        calidad_vida.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        inteligencia_emocional.setOnClickListener {
            abrirArea(it.id, usuario)
        }

        entrenamiento_mental.setOnClickListener {
            abrirArea(it.id, usuario)
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

    fun abrirArea(id: Int, usuario: String) {
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
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    fun actualizarNombreUsuario() {
        textNombreUsuario.text = menuPrincipalController.getNombre(this).toString()
    }
}