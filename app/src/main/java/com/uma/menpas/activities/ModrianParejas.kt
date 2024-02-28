package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.uma.menpas.R
import java.util.concurrent.TimeUnit

class ModrianParejas : AppCompatActivity() {

    lateinit var botonComenzar : Button
    lateinit var tiempoRealizacion : EditText
    lateinit var botonCerrarCuestionario: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_parejas)

        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarCuestionario)
        botonCerrarCuestionario.setOnClickListener {
            finish()
        }


        tiempoRealizacion = findViewById(R.id.editTextTiempoRealizacion)
        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, ModrianParejasGrid::class.java)

            if (esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = stringToMilis(tiempoRealizacion.text.toString())
                intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                startActivity(intent)

            }else if(!esValido(tiempoRealizacion.text.toString())){
                showToast("Introduzca un tiempo de realizacion valido")
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