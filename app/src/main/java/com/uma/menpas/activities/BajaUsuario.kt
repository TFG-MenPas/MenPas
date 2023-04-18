package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.BajaUsuarioController
import com.uma.menpas.utils.SnackBarPersonalizada

class BajaUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baja_usuario)

        val buttonDarseDeBaja = findViewById<Button>(R.id.buttonDarseDeBaja)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val layout = findViewById<RelativeLayout>(R.id.relativeBajaUsuario)
        lateinit var intent: Intent

        BarraNavegacion(barraNavegacionInferior, this)

        buttonDarseDeBaja.setOnClickListener {
            val dadoDeBaja = BajaUsuarioController.bajaDeUsuario(this)
            if(dadoDeBaja){
                Toast.makeText(applicationContext, "Baja realizada con éxito", Toast.LENGTH_SHORT)
                    .show()
                intent = Intent(this, IniciarSesion::class.java)
                startActivity(intent)
                finish()
            }else{
                SnackBarPersonalizada.mostrarSnack(layout, "No se ha podido realizar la baja", 2000)
            }
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