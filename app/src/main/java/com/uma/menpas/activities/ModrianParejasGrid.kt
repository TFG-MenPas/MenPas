package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R
import com.uma.menpas.utils.Fallos
import java.util.concurrent.TimeUnit

class ModrianParejasGrid : AppCompatActivity() {

    lateinit var fotos : GridLayout
    lateinit var botonFoto : ImageButton
    lateinit var textTiempo : TextView
    lateinit var crono : TextView
    lateinit var fotosArray : ArrayList<Int>
    val numTotalImg = 7 ///HARDCODE TODO
    lateinit var fotoSeleccionada : ImageButton
    lateinit var tamanyoTablero : String
    private lateinit var numeroFallosPermitidos: String
    private var limiteFallos: Int = 0
    private var fallos: Int = 0
    private var cerrado: Boolean = false
    lateinit var botonCerrar : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_parejas_grid)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoRealizacion)
        textTiempo = findViewById(R.id.textTiempoRealizacion)
        fotos = findViewById(R.id.gridFotos)
        val longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        numeroFallosPermitidos = intent.getStringExtra("fallosPermitidos").toString()
        tamanyoTablero = intent.getStringExtra("tamanyoTablero")!!
        ajustarTablero()

        botonCerrar = findViewById(R.id.imageButtonCerrarDesplegable)
        botonCerrar.setOnClickListener {
            cerrado = true
            finish()
        }

        fotoSeleccionada = ImageButton(this)
        fotoSeleccionada.contentDescription = "-1"
        fotosArray = generarFotosArray(fotos.childCount)

        for (i in 0 until fotos.childCount){
            botonFoto = fotos.getChildAt(i) as ImageButton
            val indiceFotoAleatoria = (0 until fotosArray.size).random()
            val fotoAleatoria = fotosArray.get(indiceFotoAleatoria)
            fotosArray.removeAt(indiceFotoAleatoria)
            botonFoto.contentDescription = fotoAleatoria.toString()
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    botonFoto.performClick()
                } else {
                    // Do nothing when Focus is not on the EditText
                }
            }
            botonFoto.setOnClickListener {
                val btnFoto = fotos.focusedChild as ImageButton
                btnFoto.requestFocus()
                btnFoto.isActivated = false
                btnFoto.isEnabled = false

                val alphaBlink = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
                alphaBlink.interpolator = LinearInterpolator()
                btnFoto.startAnimation(alphaBlink)

                destaparFoto(btnFoto)

                val handler = Handler()  //DEPRECATED??? TODO

                handler.postDelayed({
                    if (this.fotoSeleccionada.contentDescription as String == "-1") {
                        this.fotoSeleccionada = btnFoto
                    } else {
                        if (btnFoto.contentDescription as String != this.fotoSeleccionada.contentDescription as String) {
                            vibrator.vibrate(
                                VibrationEffect.createOneShot(
                                    200,
                                    VibrationEffect.DEFAULT_AMPLITUDE
                                )
                            )

                            btnFoto.background = AppCompatResources.getDrawable(
                                this,
                                R.color.azul_banner_cuestionarios
                            )
                            btnFoto.isEnabled = true
                            btnFoto.isActivated = true
                            this.fotoSeleccionada.background = AppCompatResources.getDrawable(
                                this,
                                R.color.azul_banner_cuestionarios
                            )
                            this.fotoSeleccionada.isEnabled = true
                            this.fotoSeleccionada.isActivated = true

                            fallos++
                            if(fallos > limiteFallos){
                                cerrado = true
                                finish()
                            }
                        }

                        btnFoto.clearAnimation()
                        this.fotoSeleccionada.clearAnimation()
                        this.fotoSeleccionada = ImageButton(this)
                        this.fotoSeleccionada.contentDescription = "-1"
                    }

                }, 200)

            }
        }

        activarRealizacionCuestionario()

        object : CountDownTimer(longTiempoRealizacion, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                actualizarCronometro(millisUntilFinished)
            }

            override fun onFinish() {
                if(!cerrado){
                    vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                    cerrado = true
                    finish()
                }
            }

        }.start()


    }

    private fun ajustarTablero() {
        when (tamanyoTablero){
            "Grande" ->  limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount, numeroFallosPermitidos) //Tablero por defecto
            "Mediano" -> ajustarTableroMediano()
            "Pequeño" -> ajustarTableroPequeño()
        }
    }

    private fun ajustarTableroMediano() {
        val numeroCasillasEliminar = 4
        for (i in fotos.childCount - 1  downTo  fotos.childCount - numeroCasillasEliminar){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
    }

    private fun ajustarTableroPequeño() {
        val numeroCasillasEliminar = 6
        for (i in fotos.childCount - 1  downTo  fotos.childCount - numeroCasillasEliminar){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
    }

    private fun destaparFoto(btnFoto: ImageButton) {
        val contenidoFoto = btnFoto.contentDescription as String
        val idFoto = contenidoFoto.toInt()
        val foto = when(idFoto){
            0 -> R.drawable.img0
            1 -> R.drawable.img1
            2 -> R.drawable.img2
            3 -> R.drawable.img3
            4 -> R.drawable.img4
            5 -> R.drawable.img5
            6 -> R.drawable.img6
            else -> R.drawable.menpas_logo_sombreado
        }
        btnFoto.background = AppCompatResources.getDrawable(this, foto)
    }

    private fun generarFotosArray(numImg: Int): ArrayList<Int> {
        val aux = ArrayList<Int>(numImg)
        for(i in 0 until (numImg / 2)){
            var fotoAleatoria = (0 until numTotalImg).random()
            while(aux.contains(fotoAleatoria)){
                fotoAleatoria = (0 until numTotalImg).random()
            }
            aux.add(fotoAleatoria)
            aux.add(fotoAleatoria)
        }
        return aux
    }

    private fun activarRealizacionCuestionario(){
        for (i in 0 until fotos.childCount){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.background = AppCompatResources.getDrawable(this, R.color.azul_banner_cuestionarios)
            botonFoto.isEnabled = true
            botonFoto.isActivated = true
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
}