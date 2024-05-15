package com.uma.menpas.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R
import java.util.concurrent.TimeUnit

class ModrianFotos : BaseActivity() {

    lateinit var botonComenzar : Button
    lateinit var tiempoEspera : EditText
    lateinit var tiempoRealizacion : EditText
    lateinit var botonCerrarCuestionario: ImageButton
    private lateinit var textOpcionNumImg: TextView
    private lateinit var textOpcionTamanyoTablero: TextView
    private lateinit var numeroFallosPermitidos: TextView

    lateinit var usuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_fotos)
        usuario = intent.getStringExtra("usuario") as String

        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarCuestionario)
        botonCerrarCuestionario.setOnClickListener {
            finish()
        }

        seekbarFallosPermitidos()
        seekbarTamanyoTablero()
        seekbarNumImg()

        tiempoEspera = findViewById(R.id.editTextTiempoEspera)
        tiempoRealizacion = findViewById(R.id.editTextTiempoRealizacion)
        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, ModrianFotosGrid::class.java)

            if (esValido(tiempoEspera.text.toString()) && esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = stringToMilis(tiempoRealizacion.text.toString())
                val longTiempoEspera = stringToMilis(tiempoEspera.text.toString())
                intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                intent.putExtra("longTiempoEspera", longTiempoEspera)
                intent.putExtra("tamanyoTablero", textOpcionTamanyoTablero.text)
                intent.putExtra("numImg", textOpcionNumImg.text)
                intent.putExtra("fallosPermitidos", numeroFallosPermitidos.text)
                intent.putExtra("usuario", usuario)
                startActivity(intent)

            }else if(!esValido(tiempoRealizacion.text.toString())){
                showToast("Introduzca un tiempo de realizacion valido")
            }else if(!esValido(tiempoEspera.text.toString())){
                showToast("Introduzca un tiempo de espera valido")
            }

        }
    }

    private fun seekbarFallosPermitidos(){
        numeroFallosPermitidos = findViewById(R.id.numero_fallos_permitidos)
        val seekbar: SeekBar = findViewById(R.id.seekbar_fallos_permitidos)
        seekbar.progressDrawable = null
        seekbar.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
        seekbar.progress = 0
        seekbar.max = 3

        val respuestas = arrayOf("25% Matriz", "50% Matriz", "75% Matriz", "Sin control de fallos")

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    numeroFallosPermitidos.text = respuestas[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun seekbarNumImg() {
        textOpcionNumImg = findViewById(R.id.textOpcionNumImg)
        val seekbarNumImg: SeekBar = findViewById(R.id.seekbarNumImg)
        seekbarNumImg.progressDrawable = null
        seekbarNumImg.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
        seekbarNumImg.progress = 0
        seekbarNumImg.max = 5

        val respuestas = arrayOf("2", "3", "4", "5", "6", "7")

        seekbarNumImg.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                textOpcionNumImg.text = respuestas[progress]
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun seekbarTamanyoTablero() {
        textOpcionTamanyoTablero = findViewById(R.id.textOpcionTamanyoTablero)
        val seekbarTamanyoTablero: SeekBar = findViewById(R.id.seekbarTamanyoTablero)
        seekbarTamanyoTablero.progressDrawable = null
        seekbarTamanyoTablero.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
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