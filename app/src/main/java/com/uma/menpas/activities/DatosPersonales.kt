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
    lateinit var checkBoxSuscripcion: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)

        val botonEditarNombreDeUsuario = findViewById<ImageButton>(R.id.botonEditarNombreDeUsuario)
        val botonEditarNombre = findViewById<ImageButton>(R.id.botonEditarNombre)
        val botonEditarApellidos = findViewById<ImageButton>(R.id.botonEditarApellidos)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        checkBoxSuscripcion = findViewById<CheckBox>(R.id.checkBoxSuscripcion)
        textNombreUsuario = findViewById<TextView>(R.id.nombreDeUsuarioActual)
        textNombre = findViewById<TextView>(R.id.nombreActual)
        textApellidos = findViewById<TextView>(R.id.apellidosActual)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_right)
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

                if(usuarioController.editarSuscripcionCorreo(this, true)){
                    Toast.makeText(applicationContext, "Suscripción activada", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    checkBoxSuscripcion.isChecked = false
                    Toast.makeText(applicationContext, "Error al suscribirse", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                if (usuarioController.editarSuscripcionCorreo(this, false)){
                    Toast.makeText(applicationContext, "Suscripción desactivada", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    checkBoxSuscripcion.isChecked = true
                    Toast.makeText(applicationContext, "Error al desuscribirse", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun crearAlertDialog(opcion: Int) {
        var editTextDialog = EditText(this)
        editTextDialog.inputType = InputType.TYPE_CLASS_TEXT

        val valorTextoEdicion = when (opcion) {
            1 -> "el nuevo nombre de usuario"
            2 -> "el nuevo nombre"
            3 -> "los nuevos apellidos"
            else -> "el nuevo valor"
        }

        val alertBuilder = AlertDialog.Builder(this)
            .setTitle("Introduza $valorTextoEdicion")
            .setPositiveButton("Guardar", null)
            .setNegativeButton("Cancelar", null)
            .setView(editTextDialog)

        val mAlertDialog = alertBuilder.create()

        mAlertDialog.show()

        val mBotonGuardar = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        mBotonGuardar.setOnClickListener {
            if(usuarioController.realizarActualizacion(this, opcion, editTextDialog.text.toString())) {
                setDatosUsuario()
                Toast.makeText(applicationContext, "Actualización realizada con éxito", Toast.LENGTH_SHORT)
                    .show()
            }else {
                Toast.makeText(applicationContext, "Error al realizar la actualización", Toast.LENGTH_SHORT)
                    .show()
            }
            mAlertDialog.cancel()
        }

        val mBotonCancelar = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        mBotonCancelar.setOnClickListener {
            mAlertDialog.cancel()
        }

    }

    private fun setDatosUsuario() {
        val usuario = usuarioController.getUsuario(this)
        if (usuario != null) {
            textNombreUsuario.text = usuario.nombreUsuario
        }
        if (usuario != null) {
            textNombre.text = usuario.nombre
        }
        if (usuario != null) {
            textApellidos.text = usuario.apellidos
        }
        if (usuario != null) {
            checkBoxSuscripcion.isChecked = usuario.infoEmail
        }
    }

}