package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.uma.menpas.R
import java.util.concurrent.TimeUnit

class ModrianParejas : AppCompatActivity() {

    lateinit var botonComenzar : Button
    lateinit var tiempoRealizacion : EditText
    lateinit var botonCerrarCuestionario: ImageButton
    lateinit var numeroFallosPermitidos: TextView
    private lateinit var textOpcionTamanyoTablero: TextView
    private lateinit var usuario: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_parejas)

        InicializarSeekbarFallosPermitidos()
        seekbarTamanyoTablero()

        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarCuestionario)
        botonCerrarCuestionario.setOnClickListener {
            finish()
        }
        usuario = intent.getStringExtra("usuario") as String

        tiempoRealizacion = findViewById(R.id.editTextTiempoRealizacion)
        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, ModrianParejasGrid::class.java)

            if (esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = stringToMilis(tiempoRealizacion.text.toString())
                intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                intent.putExtra("fallosPermitidos", numeroFallosPermitidos.text)
                intent.putExtra("tamanyoTablero", textOpcionTamanyoTablero.text)
                intent.putExtra("usuario", usuario)
                startActivity(intent)

            }else if(!esValido(tiempoRealizacion.text.toString())){
                showToast("Introduzca un tiempo de realizacion valido")
            }

        }
    }

    private fun InicializarSeekbarFallosPermitidos(){
        numeroFallosPermitidos = findViewById(R.id.numero_fallos_permitidos)
        val fallosPermitidos : SeekBar = findViewById(R.id.seekbar_fallos_permitidos)
        fallosPermitidos.progress = 0
        fallosPermitidos.max = 3

        val respuestas = arrayOf("25% Matriz", "50% Matriz", "75% Matriz", "Sin control de fallos")

        fallosPermitidos.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress) {
                    0 -> numeroFallosPermitidos.text = respuestas[0]
                    1 -> numeroFallosPermitidos.text = respuestas[1]
                    2 -> numeroFallosPermitidos.text = respuestas[2]
                    3 -> numeroFallosPermitidos.text = respuestas[3]
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun seekbarTamanyoTablero() {
        textOpcionTamanyoTablero = findViewById(R.id.textOpcionTamanyoTablero)
        val seekbarTamanyoTablero: SeekBar = findViewById(R.id.seekbarTamanyoTablero)
        seekbarTamanyoTablero.progress = 0
        seekbarTamanyoTablero.max = 2

        val respuestas = arrayOf("Pequeño", "Mediano", "Grande")

        seekbarTamanyoTablero.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOpcionTamanyoTablero.text = respuestas[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun esValido(tiempo: String): Boolean {
        if(tiempo.contains(":")){
            val minSecArray = tiempo.split(":")
            val min = minSecArray[0]
            val sec = minSecArray[1]
            return min.length == 2 && sec.length == 2
        }else{
            return false
        }
    }

    private fun stringToMilis(tiempo: String): Long {
        val minSecArray = tiempo.split(":")
        val min = minSecArray[0]
        val sec = minSecArray[1]
        val minMillis = TimeUnit.MINUTES.toMillis(min.toLong())
        val secMillis = TimeUnit.SECONDS.toMillis(sec.toLong())
        return minMillis + secMillis
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}