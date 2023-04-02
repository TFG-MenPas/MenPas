package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.bottomsheet.BottomSheetDialog

class MondrianColoresGrid : AppCompatActivity() {
    lateinit var colores : GridLayout
    lateinit var botonColor : ImageButton
    lateinit var arrayColores : ArrayList<String>
    lateinit var botonResolver : Button
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
            botonColor.setOnClickListener {
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.desplegable_colores, null)
                val btnColor = colores.focusedChild as ImageButton
                btnColor.requestFocus()

                val btnColorRojo = view.findViewById<ImageButton>(R.id.color00)
                btnColorRojo.setOnClickListener {
                    btnColor.background = btnColorRojo.background
                    dialog.dismiss()
                }

                val btnColorVerde = view.findViewById<ImageButton>(R.id.color01)
                btnColorVerde.setOnClickListener {
                    btnColor.background = btnColorVerde.background
                    dialog.dismiss()
                }

                val btnColorAzul = view.findViewById<ImageButton>(R.id.color02)
                btnColorAzul.setOnClickListener {
                    btnColor.background = btnColorAzul.background
                    dialog.dismiss()
                }

                val btnColorAmarillo = view.findViewById<ImageButton>(R.id.color03)
                btnColorAmarillo.setOnClickListener {
                    btnColor.background = btnColorAmarillo.background
                    dialog.dismiss()
                }

                val btnColorMarron = view.findViewById<ImageButton>(R.id.color10)
                btnColorMarron.setOnClickListener {
                    btnColor.background = btnColorMarron.background
                    dialog.dismiss()
                }

                val btnColorGris = view.findViewById<ImageButton>(R.id.color11)
                btnColorGris.setOnClickListener {
                    btnColor.background = btnColorGris.background
                    dialog.dismiss()
                }

                val btnColorRosa = view.findViewById<ImageButton>(R.id.color12)
                btnColorRosa.setOnClickListener {
                    btnColor.background = btnColorRosa.background
                    dialog.dismiss()
                }

                val btnColorVioleta = view.findViewById<ImageButton>(R.id.color13)
                btnColorVioleta.setOnClickListener {
                    btnColor.background = btnColorVioleta.background
                    dialog.dismiss()
                }

                val btnColorNegro = view.findViewById<ImageButton>(R.id.color20)
                btnColorNegro.setOnClickListener {
                    btnColor.background = btnColorNegro.background
                    dialog.dismiss()
                }

                val btnColorNaranja = view.findViewById<ImageButton>(R.id.color21)
                btnColorNaranja.setOnClickListener {
                    btnColor.background = btnColorNaranja.background
                    dialog.dismiss()
                }

                val btnColorCyan = view.findViewById<ImageButton>(R.id.color22)
                btnColorCyan.setOnClickListener {
                    btnColor.background = btnColorCyan.background
                    dialog.dismiss()
                }

                val btnColorFucsia = view.findViewById<ImageButton>(R.id.color23)
                btnColorFucsia.setOnClickListener {
                    btnColor.background = btnColorFucsia.background
                    dialog.dismiss()
                }

                val btnCerrar = view.findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
                btnCerrar.setOnClickListener{
                    dialog.dismiss()
                }

                dialog.setCancelable(false)
                dialog.setContentView(view)
                dialog.show()
            }
        }
        //Boton para it a resolver el cuestionario TODO Timer
        botonResolver = findViewById(R.id.buttonResolver)
        botonResolver.setOnClickListener {
            for (i in 0 until colores.childCount){
                botonColor = colores.getChildAt(i) as ImageButton
                botonColor.background = AppCompatResources.getDrawable(this, R.color.elipseBlue)
            }
        }

    }
}