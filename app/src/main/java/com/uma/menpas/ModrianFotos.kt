package com.uma.menpas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.concurrent.TimeUnit

class ModrianFotos : AppCompatActivity() {

    lateinit var botonComenzar : Button
    lateinit var tiempoEspera : EditText
    lateinit var tiempoRealizacion : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_fotos)

        val textOpcionTamanyoTablero : TextView = findViewById(R.id.textOpcionTamanyoTablero)
        val seekbarTamanyoTablero : SeekBar = findViewById(R.id.seekbarTamanyoTablero)
        seekbarTamanyoTablero.progress = 0
        seekbarTamanyoTablero.max = 2

        seekbarTamanyoTablero.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress) {
                    0 -> textOpcionTamanyoTablero.text = "Pequeño"
                    1 -> textOpcionTamanyoTablero.text = "Mediano"
                    2 -> textOpcionTamanyoTablero.text = "Grande"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        val textOpcionNumImg : TextView = findViewById(R.id.textOpcionNumImg)
        val seekbarNumImg : SeekBar = findViewById(R.id.seekbarNumImg)
        seekbarNumImg.progress = 0
        seekbarNumImg.max = 4

        seekbarNumImg.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress) {
                    0 -> textOpcionNumImg.text = "1"
                    1 -> textOpcionNumImg.text = "2"
                    2 -> textOpcionNumImg.text = "3"
                    3 -> textOpcionNumImg.text = "4"
                    4 -> textOpcionNumImg.text = "5"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        tiempoEspera = findViewById(R.id.editTextTiempoEspera)
        tiempoRealizacion = findViewById(R.id.editTextTiempoRealizacion)
        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, MondrianColoresGrid::class.java)

            if (esValido(tiempoEspera.text.toString()) && esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = stringToMilis(tiempoRealizacion.text.toString())
                val longTiempoEspera = stringToMilis(tiempoEspera.text.toString())
                /*intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                intent.putExtra("longTiempoEspera", longTiempoEspera)
                intent.putExtra("tamanyoTablero", textOpcionTamanyoTablero.text)
                intent.putExtra("numImg", textOpcionNumImg.text)
                startActivity(intent)*/
                showToast("Realizacion: " + longTiempoRealizacion + ", Espera: " + longTiempoEspera + ", Tamaño: "
                        + textOpcionTamanyoTablero.text + ", NumImg: " + textOpcionNumImg.text)
            }else if(!esValido(tiempoRealizacion.text.toString())){
                showToast("Introduzca un tiempo de realizacion valido")
            }else if(!esValido(tiempoEspera.text.toString())){
                showToast("Introduzca un tiempo de espera valido")
            }

        }
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