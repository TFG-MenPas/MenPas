package com.uma.menpas

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val mostrarOcultarPassword = findViewById<ImageButton>(R.id.show_hide_password)
        val hideShowRepPassword = findViewById<ImageButton>(R.id.show_hide_rep_password)

        val usuario = findViewById<EditText>(R.id.usuario)
        val password = findViewById<EditText>(R.id.password)
        val repPassword = findViewById<EditText>(R.id.rep_password)

        val terminos = findViewById<TextView>(R.id.terminos)
        val iniSesion = findViewById<TextView>(R.id.textInicieSesion)

        val buttonRegistrar = findViewById<Button>(R.id.buttonRegistrar)

        var isPasswordVisible = false

        mostrarOcultarPassword.setOnClickListener {
            if (!isPasswordVisible) {
                password.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                password.typeface = usuario.typeface
                mostrarOcultarPassword.setImageResource(R.drawable.hide_icon_v3)
            } else {
                password.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                password.typeface = usuario.typeface
                mostrarOcultarPassword.setImageResource(R.drawable.show_icon_v3)
            }
            password.setSelection(password.text.length)
            isPasswordVisible = !isPasswordVisible

        }

        var isRepPasswordVisible = false

        hideShowRepPassword.setOnClickListener {
            if (!isRepPasswordVisible) {
                repPassword.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                repPassword.typeface = usuario.typeface
                hideShowRepPassword.setImageResource(R.drawable.hide_icon_v3)
            } else {
                repPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                repPassword.typeface = usuario.typeface
                hideShowRepPassword.setImageResource(R.drawable.show_icon_v3)
            }
            repPassword.setSelection(repPassword.text.length)
            isRepPasswordVisible = !isRepPasswordVisible
        }

        terminos.setOnClickListener {
            Toast.makeText(applicationContext, "Terminos y Condiciones de Uso", Toast.LENGTH_SHORT)
                .show()
        }

        iniSesion.setOnClickListener {
            finish()
        }

        buttonRegistrar.setOnClickListener {
            Toast.makeText(applicationContext, "Registro realizado con éxito", Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        //Spinner genero
        val generoSpinner = findViewById<Spinner>(R.id.selectGenero)
        val generoArrayAdapter = ArrayAdapter.createFromResource(this, R.array.genero_array, R.layout.spinner_list)
        generoArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        generoSpinner.adapter = generoArrayAdapter

        //Spinner deportes
        val deporteSpinner = findViewById<Spinner>(R.id.selectDeporte)
        val deporteArrayAdapter = ArrayAdapter.createFromResource(this, R.array.deportes_array, R.layout.spinner_list)
        deporteArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        deporteSpinner.adapter = deporteArrayAdapter

        //Spinner nacionalidad
        val nacionalidadSpinner = findViewById<Spinner>(R.id.selectNacionalidad)
        val nacionalidadArrayAdapter = ArrayAdapter.createFromResource(this, R.array.nacionalidad_array, R.layout.spinner_list)
        nacionalidadArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        nacionalidadSpinner.adapter = nacionalidadArrayAdapter

        //Spinner estado civil
        val estadoCivilSpinner = findViewById<Spinner>(R.id.selectEstadoCivil)
        val estadoCivilArrayAdapter = ArrayAdapter.createFromResource(this, R.array.estadoCivil_array, R.layout.spinner_list)
        estadoCivilArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        estadoCivilSpinner.adapter = estadoCivilArrayAdapter

        //Spinner nivel estudios
        val nivelEstudiosSpinner = findViewById<Spinner>(R.id.selectNivelEstudios)
        val nivelEstudiosArrayAdapter = ArrayAdapter.createFromResource(this, R.array.nivelEstudios_array, R.layout.spinner_list)
        nivelEstudiosArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        nivelEstudiosSpinner.adapter = nivelEstudiosArrayAdapter

        //Spinner horas semana deporte
        val horasSemanaDeporteSpinner = findViewById<Spinner>(R.id.selectHorasSemanaDeporte)
        val horasSemanaDeporteArrayAdapter = ArrayAdapter.createFromResource(this, R.array.horasSemanaDeporte_array, R.layout.spinner_list)
        horasSemanaDeporteArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        horasSemanaDeporteSpinner.adapter = horasSemanaDeporteArrayAdapter

        //Spinner profesion
        val profesionSpinner = findViewById<Spinner>(R.id.selectProfesion)
        val profesionArrayAdapter = ArrayAdapter.createFromResource(this, R.array.profesion_array, R.layout.spinner_list)
        profesionArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        profesionSpinner.adapter = profesionArrayAdapter

    }
}
