package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources

class MondrianColoresGrid : AppCompatActivity() {
    lateinit var colores : GridLayout
    lateinit var botonColor : ImageButton
    lateinit var arrayColores : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores_grid)

        arrayColores = intent.getStringArrayListExtra("arrayColores")!!

        colores = findViewById(R.id.gridColores)
        for (i in 0 until colores.childCount){
            botonColor = colores.getChildAt(i) as ImageButton
            val color = when(arrayColores[(0 until arrayColores.size).random()]){
                "rojo" -> R.color.rojo
                "marron" -> R.color.marron
                "verde" -> R.color.verde
                "gris" -> R.color.gris
                "azul" -> R.color.azul
                "rosa" -> R.color.rosa
                "amarillo" -> R.color.amarillo
                "violeta" -> R.color.violeta
                "negro" -> R.color.black
                "fucsia" -> R.color.fucsia
                "naranja" -> R.color.naranja
                "cyan" -> R.color.cyan
                else -> R.color.black
            }
            botonColor.background = AppCompatResources.getDrawable(this, color)
        }

    }
}