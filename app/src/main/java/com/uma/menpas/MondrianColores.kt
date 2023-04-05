package com.uma.menpas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import java.util.concurrent.TimeUnit

class MondrianColores : AppCompatActivity() {
    lateinit var botonComenzar : Button
    lateinit var colores : GridLayout
    lateinit var checkBoxColor : CheckBox
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var tiempoEspera : EditText
    lateinit var tiempoRealizacion : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores)

        tiempoEspera = findViewById(R.id.editTextTiempoEspera)
        tiempoRealizacion = findViewById(R.id.editTextTiempoRealizacion)
        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, MondrianColoresGrid::class.java)
            colores = findViewById(R.id.gridColores)
            arrayColores = ArrayList()
            arrayEliminar = ArrayList()
            for (i in 0 until colores.childCount){
                checkBoxColor = colores.getChildAt(i) as CheckBox
                var color : String
                color = checkBoxColor.text as String
                if (checkBoxColor.isChecked){
                    arrayColores.add(color.lowercase())
                }else{
                    arrayEliminar.add(color.lowercase())
                }
            }

            if (arrayColores.size >= 2 && esValido(tiempoEspera.text.toString()) && esValido(tiempoRealizacion.text.toString())){
                val longTiempoRealizacion = stringToMilis(tiempoRealizacion.text.toString())
                val longTiempoEspera = stringToMilis(tiempoEspera.text.toString())
                intent.putExtra("longTiempoRealizacion", longTiempoRealizacion)
                intent.putExtra("longTiempoEspera", longTiempoEspera)
                intent.putExtra("arrayColores", arrayColores)
                intent.putExtra("arrayEliminar", arrayEliminar)
                startActivity(intent)
            }else if(arrayColores.size < 2){
                showToast("Selecciona mínimo 2 colores")
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