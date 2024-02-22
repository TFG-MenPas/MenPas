package com.uma.menpas.activities

import android.os.Bundle
import android.text.InputType
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.R
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.utils.BarraNavegacion

class DatosPersonales : AppCompatActivity() {
    val usuarioController = UsuarioController()
    lateinit var textNombreUsuario: TextView
    lateinit var textNombre: TextView
    lateinit var textApellidos: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)

        val botonEditarNombreDeUsuario = findViewById<ImageButton>(R.id.botonEditarNombreDeUsuario)
        val botonEditarNombre = findViewById<ImageButton>(R.id.botonEditarNombre)
        val botonEditarApellidos = findViewById<ImageButton>(R.id.botonEditarApellidos)
        val checkBoxSuscripcion = findViewById<CheckBox>(R.id.checkBoxSuscripcion)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        textNombreUsuario = findViewById<TextView>(R.id.nombreDeUsuarioActual)
        textNombre = findViewById<TextView>(R.id.nombreActual)
        textApellidos = findViewById<TextView>(R.id.apellidosActual)

        BarraNavegacion(barraNavegacionInferior, this)
        setDatosUsuario()

        botonEditarNombreDeUsuario.setOnClickListener {
            crearAlertDialog(1)
        }

        botonEditarNombre.setOnClickListener {
            crearAlertDialog(2)
        }

        botonEditarApellidos.setOnClickListener {
            crearAlertDialog(3)
        }

        checkBoxSuscripcion.setOnClickListener {
            if (checkBoxSuscripcion.isChecked) {
                Toast.makeText(applicationContext, "Suscripción activada", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Suscripción desactivada", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun crearAlertDialog(opcion: Int) {
        var editTextDialog = EditText(this)
        editTextDialog.inputType = InputType.TYPE_CLASS_TEXT

        val alertBuilder = AlertDialog.Builder(this)
            .setTitle("Introduza el nuevo nombre")
            .setPositiveButton("Guardar", null)
            .setNegativeButton("Cancelar", null)
            .setView(editTextDialog)

        var mAlertDialog = alertBuilder.create()

        mAlertDialog.show()

        val mBotonGuardar = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        mBotonGuardar.setOnClickListener {
            usuarioController.realizarActualizacion(this, opcion, editTextDialog.text.toString())
            mAlertDialog.cancel()
        }

        val mBotonCancelar = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        mBotonCancelar.setOnClickListener {
            mAlertDialog.cancel()
        }

    }

    private fun setDatosUsuario() {
        val usuario = usuarioController.getUsuario(this)
        textNombreUsuario.text = usuario.nombreUsuario
        textNombre.text = usuario.nombre
        textApellidos.text = usuario.apellidos
    }

}