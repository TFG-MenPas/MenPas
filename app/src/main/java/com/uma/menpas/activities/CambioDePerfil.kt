package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.EmailController

class CambioDePerfil : BaseActivity() {
    private val emailController = EmailController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambio_de_perfil)

        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_right)
        BarraNavegacion(barraNavegacionInferior, this)

        val editTextCuestionariosNecesarios = findViewById<EditText>(R.id.editTextCuestionariosNecesarios)
        val editTextTituloInvestigacion = findViewById<EditText>(R.id.editTexTituloInvestigacion)
        val editTextEstimacionParticipantes = findViewById<EditText>(R.id.editTextEstimacionParticipantesMuestra)
        val editTextDuracionEstimada = findViewById<EditText>(R.id.editTextDuracionEstimadaInvestigacion)
        val editTextTipoPublicacion = findViewById<EditText>(R.id.editTextTipoPublicacion)
        val editTextMotivo = findViewById<EditText>(R.id.editTextMotivo)
        val editTextobservaciones = findViewById<EditText>(R.id.editTextObservaciones)

        buttonEnviar.setOnClickListener {
            val cuestionariosNecesarios = editTextCuestionariosNecesarios.text.toString()
            val tituloInvestigacion = editTextTituloInvestigacion.text.toString()
            val estimacionParticipantes = editTextEstimacionParticipantes.text.toString()
            val duracionEstimada = editTextDuracionEstimada.text.toString()
            val tipoPublicacion = editTextTipoPublicacion.text.toString()
            val motivo = editTextMotivo.text.toString()
            val observaciones = editTextobservaciones.text.toString()
            if(sonValidosYNoVacios(listOf(cuestionariosNecesarios, tituloInvestigacion, estimacionParticipantes, duracionEstimada, tipoPublicacion, motivo, observaciones))){
                if(emailController.enviarSolicitudAdministrador(this,
                        cuestionariosNecesarios,
                        tituloInvestigacion,
                        estimacionParticipantes,
                        duracionEstimada,
                        tipoPublicacion,
                        motivo,
                        observaciones)) {
                    Toast.makeText(applicationContext, "Solicitud enviada con éxito", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }else {
                    Toast.makeText(applicationContext, "Error al enviar la solicitud", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "Uno o varios campos están incompletos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun sonValidosYNoVacios(listaString: List<String>): Boolean {
        return listaString.all { it.isNotBlank() }
    }
}