package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
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

        lateinit var intent: Intent
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_right)
        BarraNavegacion(barraNavegacionInferior, this)

        buttonDarseDeBaja.setOnClickListener {
            alertDialogBaja()
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


    fun alertDialogBaja() {
        val alertBuilder = AlertDialog.Builder(this)
            .setTitle("¿Está seguro que desea darse de baja?")
            .setPositiveButton("Sí", null)
            .setNegativeButton("No", null)

        var mAlertDialog = alertBuilder.create()

        mAlertDialog.show()

        val mBotonConfirmar = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        mBotonConfirmar.setOnClickListener {
            mAlertDialog.cancel()
            darseDeBaja()
        }

        val mBotonCancelar = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        mBotonCancelar.setOnClickListener {
            mAlertDialog.cancel()
        }

    }

    fun darseDeBaja(){
        val layout = findViewById<RelativeLayout>(R.id.relativeBajaUsuario)
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
}