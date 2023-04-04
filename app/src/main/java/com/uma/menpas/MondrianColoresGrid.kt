package com.uma.menpas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.getSystemService
import androidx.core.view.marginEnd
import com.google.android.material.bottomsheet.BottomSheetDialog

class MondrianColoresGrid : AppCompatActivity() {
    lateinit var colores : GridLayout
    lateinit var botonColor : ImageButton
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var botonResolver : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores_grid)

        arrayColores = intent.getStringArrayListExtra("arrayColores")!!
        arrayEliminar = intent.getStringArrayListExtra("arrayEliminar")!!
        colores = findViewById(R.id.gridColores)
        for (i in 0 until colores.childCount){
            botonColor = colores.getChildAt(i) as ImageButton
            val colorAleatorio = arrayColores[(0 until arrayColores.size).random()]
            val color = when(colorAleatorio){
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
            botonColor.contentDescription = colorAleatorio
            botonColor.setOnClickListener {
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.desplegable_colores, null)
                val btnColor = colores.focusedChild as ImageButton
                btnColor.requestFocus()

                val animation = AlphaAnimation(1.toFloat(), 0.toFloat())
                animation.duration = 500
                animation.interpolator = LinearInterpolator()
                animation.repeatCount = Animation.INFINITE
                animation.repeatMode = Animation.REVERSE
                btnColor.startAnimation(animation)

                val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                val gridColoresSeleccion = view.findViewById<GridLayout>(R.id.gridColoresSeleccion)
                for (i in 0 until gridColoresSeleccion.childCount){
                   val colorSeleccion = gridColoresSeleccion.getChildAt(i) as ImageButton
                    if (estaMarcado(colorSeleccion, arrayColores)){
                        colorSeleccion.setOnClickListener {
                            if (btnColor.contentDescription == colorSeleccion.contentDescription){
                                btnColor.background = colorSeleccion.background
                                btnColor.clearAnimation()
                                btnColor.isEnabled = false
                                btnColor.isActivated = false
                                dialog.dismiss()
                            }else{
                                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                            }
                        }
                    }
                }

                val btnCerrar = view.findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
                btnCerrar.setOnClickListener{
                    btnColor.clearAnimation()
                    dialog.dismiss()
                }

                for (texColorEliminar in arrayEliminar){
                    val imageButtonColor = when(texColorEliminar){
                        "rojo" -> view.findViewById<ImageButton>(R.id.color00)
                        "marron" -> view.findViewById(R.id.color10)
                        "verde" -> view.findViewById(R.id.color01)
                        "gris" -> view.findViewById(R.id.color11)
                        "azul" -> view.findViewById(R.id.color02)
                        "rosa" -> view.findViewById(R.id.color12)
                        "amarillo" -> view.findViewById(R.id.color03)
                        "violeta" -> view.findViewById(R.id.color13)
                        "negro" -> view.findViewById(R.id.color20)
                        "fucsia" -> view.findViewById(R.id.color23)
                        "naranja" -> view.findViewById(R.id.color21)
                        "cyan" -> view.findViewById(R.id.color22)
                        else -> view.findViewById(R.id.imageButtonCerrarDesplegable)
                    }
                    gridColoresSeleccion.removeViewAt(gridColoresSeleccion.indexOfChild(imageButtonColor))
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

        Thread(Runnable {
            Thread.sleep(3000)
            runOnUiThread{botonResolver.performClick()}

        }).start()
    }

    private fun estaMarcado(gridBotonColor: ImageButton, arrayColores: ArrayList<String>): Boolean {
        for (textColor in arrayColores){
            if (textColor == gridBotonColor.contentDescription){
                return true
            }
        }
        return false
    }
}