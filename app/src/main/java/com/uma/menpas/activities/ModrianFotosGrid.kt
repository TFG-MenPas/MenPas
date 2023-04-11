package com.uma.menpas.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.uma.menpas.R

class ModrianFotosGrid : AppCompatActivity() {

    lateinit var fotos : GridLayout
    lateinit var botonFoto : ImageButton
    lateinit var tamanyoTablero : String
    lateinit var numImg : String
    lateinit var textTiempo : TextView
    lateinit var crono : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_fotos_grid)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoEspera)
        textTiempo = findViewById(R.id.textTiempoEspera)
        val longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        val longTiempoEspera = intent.getLongExtra("longTiempoEspera", 10000)
        tamanyoTablero = intent.getStringExtra("tamanyoTablero")!!
        numImg = intent.getStringExtra("numImg")!!
    }
}