package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.CentroController
import com.uma.menpas.models.Centro
import com.uma.menpas.room.UsuarioDB

class RegistroDeCentro : AppCompatActivity() {
    private var centroController = CentroController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_de_centro)

        val buttonRegistrarCentro = findViewById<Button>(R.id.buttonRegistrarCentro)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val editTextNombreCentro = findViewById<TextView>(R.id.editTextNombreCentro)
        val editTextProvincia = findViewById<TextView>(R.id.editTextProvincia)
        val editTextLocalidad = findViewById<TextView>(R.id.editTextLocalidad)
        val editTextDireccion = findViewById<TextView>(R.id.editTextDireccion)
        val editTextTelefono = findViewById<TextView>(R.id.editTextTelefono)
        val editTextCodigoPostal = findViewById<TextView>(R.id.editTextCodigoPostal)

        BarraNavegacion(barraNavegacionInferior, this)

        //Spinner paises
        val paisesSpinner = findViewById<Spinner>(R.id.selectPais)
        val paisesArrayAdapter = ArrayAdapter.createFromResource(this,
            R.array.paises_array,
            R.layout.spinner_paises
        )
        paisesArrayAdapter.setDropDownViewResource(R.layout.spinner_paises)
        paisesSpinner.adapter = paisesArrayAdapter

        buttonRegistrarCentro.setOnClickListener {
            val usuario = UsuarioDB.getDatabase(this)?.UsuarioDAO()?.getUsuario()
            val nuevoCentro = Centro(editTextNombreCentro.text.toString(),
                usuario!!.nombreUsuario,
                editTextNombreCentro.text.toString(),
                editTextLocalidad.text.toString(),
                editTextProvincia.text.toString(),
                paisesSpinner.selectedItem.toString(),
                editTextDireccion.text.toString(),
                editTextTelefono.text.toString(),
                editTextCodigoPostal.text.toString(),
                "pruebalimitado",
                "pruebacontador"
            )
            if(centroController.crearCentro(nuevoCentro)) {
                Toast.makeText(applicationContext, "Centro registrado con éxito", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Error registrando el centro", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}