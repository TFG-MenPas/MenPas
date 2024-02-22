package com.uma.menpas.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.uma.menpas.R
import com.uma.menpas.controllers.RegistroController
import com.uma.menpas.utils.SnackBarPersonalizada

class RegistroUsuario : AppCompatActivity() {
    val registroController = RegistroController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val layout = findViewById<RelativeLayout>(R.id.relativeRegistro)

        val mostrarOcultarPassword = findViewById<ImageButton>(R.id.show_hide_password)
        val hideShowRepPassword = findViewById<ImageButton>(R.id.show_hide_rep_password)

        val editUsuario = findViewById<EditText>(R.id.usuario)
        val editPassword = findViewById<EditText>(R.id.password)
        val editRepPassword = findViewById<EditText>(R.id.rep_password)
        val editNombre = findViewById<EditText>(R.id.nombre)
        val editApellidos = findViewById<EditText>(R.id.apellidos)
        val editEdad = findViewById<EditText>(R.id.editEdad)
        val editEmail = findViewById<EditText>(R.id.email)
        val editRepEmail = findViewById<EditText>(R.id.editRepEmail)
        val editGrupo = findViewById<EditText>(R.id.editGrupo)
        val editComienzoDeporte = findViewById<EditText>(R.id.textComienzoDeporte)

        val checkBoxterminos = findViewById<CheckBox>(R.id.checkBox_terminos)
        val checkBoxSuscripcion = findViewById<CheckBox>(R.id.checkBoxRecibirInformacion)

        val iniSesion = findViewById<TextView>(R.id.textInicieSesion)
        val terminos = findViewById<TextView>(R.id.terminos)

        val buttonRegistrar = findViewById<Button>(R.id.buttonRegistrar)

        var isPasswordVisible = false

        val arrayDeportes = registroController.getListaDeportes()
        val arrayEstadoCivil = registroController.getListaEstadoCivil()
        val arrayNacionalidades = registroController.getListaNacionalidades()
        val arrayNivelEstudios = registroController.getNivelEstudios()
        val arrayPerfiles = registroController.getListaPerfiles()
        val arrayProfesion = registroController.getListaProfesion()
        val arrayGenero = registroController.getListaSexo()

        //Spinner genero
        val generoSpinner = findViewById<Spinner>(R.id.selectGenero)
        val generoArrayAdapter = ArrayAdapter(this, R.layout.spinner_list, arrayGenero)
        generoArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        generoSpinner.adapter = generoArrayAdapter

        //Spinner deportes
        val deporteSpinner = findViewById<Spinner>(R.id.selectDeporte)
        val deporteArrayAdapter = ArrayAdapter(this, R.layout.spinner_list, arrayDeportes)
        deporteArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        deporteSpinner.adapter = deporteArrayAdapter

        //Spinner nacionalidad
        val nacionalidadSpinner = findViewById<Spinner>(R.id.selectNacionalidad)
        val nacionalidadArrayAdapter =
            ArrayAdapter(this, R.layout.spinner_list, arrayNacionalidades)
        nacionalidadArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        nacionalidadSpinner.adapter = nacionalidadArrayAdapter

        //Spinner estado civil
        val estadoCivilSpinner = findViewById<Spinner>(R.id.selectEstadoCivil)
        val estadoCivilArrayAdapter = ArrayAdapter(this, R.layout.spinner_list, arrayEstadoCivil)
        estadoCivilArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        estadoCivilSpinner.adapter = estadoCivilArrayAdapter

        //Spinner nivel estudios
        val nivelEstudiosSpinner = findViewById<Spinner>(R.id.selectNivelEstudios)
        val nivelEstudiosArrayAdapter =
            ArrayAdapter(this, R.layout.spinner_list, arrayNivelEstudios)
        nivelEstudiosArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        nivelEstudiosSpinner.adapter = nivelEstudiosArrayAdapter

        //Spinner horas semana deporte
        val horasSemanaDeporteSpinner = findViewById<Spinner>(R.id.selectHorasSemanaDeporte)
        val horasSemanaDeporteArrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.horasSemanaDeporte_array,
            R.layout.spinner_list
        )
        horasSemanaDeporteArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        horasSemanaDeporteSpinner.adapter = horasSemanaDeporteArrayAdapter

        //Spinner profesion
        val profesionSpinner = findViewById<Spinner>(R.id.selectProfesion)
        val profesionArrayAdapter = ArrayAdapter(this, R.layout.spinner_list, arrayProfesion)
        profesionArrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        profesionSpinner.adapter = profesionArrayAdapter

        mostrarOcultarPassword.setOnClickListener {
            if (!isPasswordVisible) {
                editPassword.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                editPassword.typeface = editUsuario.typeface
                mostrarOcultarPassword.setImageResource(R.drawable.icon_ver_contrasenya)
            } else {
                editPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                editPassword.typeface = editUsuario.typeface
                mostrarOcultarPassword.setImageResource(R.drawable.icon_ocultar_contrasenya)
            }
            editPassword.setSelection(editPassword.text.length)
            isPasswordVisible = !isPasswordVisible

        }

        var isRepPasswordVisible = false

        hideShowRepPassword.setOnClickListener {
            if (!isRepPasswordVisible) {
                editRepPassword.inputType =
                    android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                editRepPassword.typeface = editUsuario.typeface
                hideShowRepPassword.setImageResource(R.drawable.icon_ver_contrasenya)
            } else {
                editRepPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                editRepPassword.typeface = editUsuario.typeface
                hideShowRepPassword.setImageResource(R.drawable.icon_ocultar_contrasenya)
            }
            editRepPassword.setSelection(editRepPassword.text.length)
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
            val usuario = editUsuario.text.toString()
            val contrasenya = editPassword.text.toString()
            val repContrasenya = editRepPassword.text.toString()
            val nombre = editNombre.text.toString()
            val apellidos = editApellidos.text.toString()
            val edad = editEdad.text.toString()
            val email = editEmail.text.toString()
            val repEmail = editRepEmail.text.toString()
            val grupo = editGrupo.text.toString()
            val anyoComienzo = editComienzoDeporte.text.toString()
            val genero = generoSpinner.selectedItem.toString()
            val deportePracticado = deporteSpinner.selectedItem.toString()
            val nacionalidad = nacionalidadSpinner.selectedItem.toString()
            val estadoCivil = estadoCivilSpinner.selectedItem.toString()
            val nivelEstudios = nivelEstudiosSpinner.selectedItem.toString()
            val horasSemanales = horasSemanaDeporteSpinner.selectedItem.toString()
            val profesion = profesionSpinner.selectedItem.toString()
            val terminoSeleccionado = checkBoxterminos.isChecked


            val resultadoComprobacion = registroController.comprobarDatos(
                usuario,
                contrasenya,
                repContrasenya,
                nombre,
                apellidos,
                edad,
                email,
                repEmail,
                grupo,
                anyoComienzo,
                genero,
                deportePracticado,
                nacionalidad,
                estadoCivil,
                nivelEstudios,
                horasSemanales,
                profesion,
                terminoSeleccionado
            )

            /*
            val usuarionew = RegistroController.registrarUsuario("pNombre",
                "pNombre",
                "nombreMuyLargoAVerComoQuedaEnLaAplicacion",
                "nombreMuyLargoAVerComoQuedaEnLaAplicacion",
                "12",
                "prueba3111111123",
                "prueba1",
                "11",
                genero,
                deportePracticado,
                nacionalidad,
                estadoCivil,
                nivelEstudios,
                horasSemanales,
                profesion)
             */

            var textoSnackbar = ""
            when (resultadoComprobacion) {
                1 -> textoSnackbar = "Todavía quedan campos sin rellenar"
                2 -> textoSnackbar = "El nombre de usuario introducido ya existe"
                3 -> textoSnackbar = "Las contraseñas no coinciden"
                4 -> textoSnackbar = "Los correos electrónicos no coinciden"
                5 -> textoSnackbar = "El correo electrónico introducido ya existe"
                6 -> textoSnackbar = "No se han aceptado los términos"
            }
            SnackBarPersonalizada.mostrarSnack(layout, textoSnackbar, 2000)
            if (resultadoComprobacion == 0) {
                val user = registroController.registrarUsuario(
                    usuario,
                    contrasenya,
                    nombre,
                    apellidos,
                    edad,
                    email,
                    grupo,
                    anyoComienzo,
                    genero,
                    deportePracticado,
                    nacionalidad,
                    estadoCivil,
                    nivelEstudios,
                    horasSemanales,
                    profesion
                )

                Toast.makeText(this, "Registro realizado con éxito", Toast.LENGTH_SHORT)
                finish()
            }
        }
    }
}

