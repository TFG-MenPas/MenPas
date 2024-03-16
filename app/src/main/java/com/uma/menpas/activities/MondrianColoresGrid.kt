package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.uma.menpas.R
import java.util.concurrent.TimeUnit

class MondrianColoresGrid : AppCompatActivity() {
    lateinit var colores : GridLayout
    lateinit var botonColor : ImageButton
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var textTiempo : TextView
    lateinit var crono : TextView
    private lateinit var numeroFallosPermitidos: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores_grid)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoEspera)
        textTiempo = findViewById(R.id.textTiempoEspera)
        val longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        val longTiempoEspera = intent.getLongExtra("longTiempoEspera", 10000)
        arrayColores = intent.getStringArrayListExtra("arrayColores")!!
        arrayEliminar = intent.getStringArrayListExtra("arrayEliminar")!!
        numeroFallosPermitidos = intent.getStringExtra("fallosPermitidos").toString()
        colores = findViewById(R.id.gridColores)
        for (i in 0 until colores.childCount){
            botonColor = colores.getChildAt(i) as ImageButton
            val colorAleatorio = arrayColores[(0 until arrayColores.size).random()]
            val color = when(colorAleatorio){
                "rojo" -> R.color.rojo
                "marrón" -> R.color.marron
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
                "blanco" -> R.color.blanco
                else -> R.color.black
            }
            val drawable = botonColor.background.mutate() as GradientDrawable
            drawable.setColor(getColor(color))
            botonColor.contentDescription = colorAleatorio
            botonColor.isEnabled = false
            botonColor.isActivated = false
            botonColor.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    botonColor.performClick()
                } else {
                    // Do nothing when Focus is not on the EditText
                }
            }
            botonColor.setOnClickListener {
                val btnColor = colores.focusedChild as ImageButton
                btnColor.requestFocus()
                btnColor.isActivated = false
                btnColor.isEnabled = false

                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.desplegable_colores, null)


                val alphaBlink = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
                alphaBlink.interpolator = LinearInterpolator()
                btnColor.startAnimation(alphaBlink)


                val gridColoresSeleccion = view.findViewById<GridLayout>(R.id.gridColoresSeleccion)
                for (i in 0 until gridColoresSeleccion.childCount){
                   val colorSeleccion = gridColoresSeleccion.getChildAt(i) as ImageButton
                    setDrawableColor(colorSeleccion)
                    if (estaMarcado(colorSeleccion, arrayColores)){
                        colorSeleccion.setOnClickListener {
                            if (btnColor.contentDescription == colorSeleccion.contentDescription){
                                setDrawableColor(btnColor)
                                btnColor.clearAnimation()
                                dialog.dismiss()
                            }else{
                                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                                val clk_rotate = AnimationUtils.loadAnimation(this, R.anim.view_shake)
                                colorSeleccion.startAnimation(clk_rotate)
                            }
                        }
                    }
                }

                val btnCerrar = view.findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
                btnCerrar.setOnClickListener{
                    btnColor.isActivated = true
                    btnColor.isEnabled = true
                    btnColor.clearAnimation()
                    dialog.dismiss()
                }

                for (texColorEliminar in arrayEliminar){
                    val imageButtonColor = when(texColorEliminar){
                        "rojo" -> view.findViewById<ImageButton>(R.id.color00)
                        "marrón" -> view.findViewById(R.id.color10)
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
                        "blanco" -> view.findViewById(R.id.color40)
                        else -> view.findViewById(R.id.imageButtonCerrarDesplegable)
                    }
                    gridColoresSeleccion.removeViewAt(gridColoresSeleccion.indexOfChild(imageButtonColor))
                }

                dialog.setCancelable(false)
                dialog.setContentView(view)
                dialog.show()
            }
        }

        object : CountDownTimer(longTiempoEspera, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                actualizarCronometro(millisUntilFinished)
            }

            override fun onFinish() {
                activarRealizacionCuestionario()
                textTiempo.text = getString(R.string.tiempo_de_realizacion)
                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))

                object : CountDownTimer(longTiempoRealizacion, 1000){
                    @SuppressLint("SetTextI18n")
                    override fun onTick(millisUntilFinished: Long) {
                        actualizarCronometro(millisUntilFinished)
                    }

                    override fun onFinish() {
                        vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                        finish()
                    }

                }.start()
            }

        }.start()
    }

    private fun calcularFallosPermitidos(): Int {
        when(numeroFallosPermitidos){
            "25% Matriz" -> 0
            "50% Matriz" -> 1
            "75% Matriz" -> 2
            "Sin control de fallos" -> 3
        }
        return 0
    }

    private fun setDrawableColor(colorSeleccion: View) {
        val color = when(colorSeleccion.contentDescription){
            "rojo" -> R.color.rojo
            "marrón" -> R.color.marron
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
            "blanco" -> R.color.blanco
            else -> R.color.black
        }
        val drawable = colorSeleccion.background.mutate() as GradientDrawable
        drawable.setColor(getColor(color))
    }

    private fun activarRealizacionCuestionario(){
        for (i in 0 until colores.childCount){
            botonColor = colores.getChildAt(i) as ImageButton
            val drawable = botonColor.background.mutate() as GradientDrawable
            drawable.setColor(getColor(R.color.azul_banner_cuestionarios))
            botonColor.isEnabled = true
            botonColor.isActivated = true
        }
    }

    private fun actualizarCronometro(millisUntilFinished: Long) {
        val min =  TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        val secs = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - TimeUnit.MINUTES.toMillis(min))
        if (secs < 10){
            crono.text = "$min : 0$secs"
        }else{
            crono.text = "$min : $secs"
        }
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