package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.*
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.uma.menpas.R
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.CalculoResultados
import com.uma.menpas.utils.Fallos
import com.uma.menpas.utils.QueryParser
import java.util.concurrent.TimeUnit

class ModrianStroopGrid : BaseActivity() {
    lateinit var colores : GridLayout
    lateinit var botonColor : Button
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var textTiempo : TextView
    lateinit var crono : TextView
    lateinit var botonCerrar : ImageButton
    private lateinit var numeroFallosPermitidos: String
    private var limiteFallos: Int = 0
    private var cerrado: Boolean = false
    private lateinit var tamanyoTablero : String


    lateinit var cronometro: Chronometer
    private var blancos: Int = 20
    private var aciertos: Int = 0
    private var fallos: Int = 0
    private var longTiempoEspera: Long = 0
    private var longTiempoRealizacion: Long = 0
    private var filas: Int = 0
    private var columnas: Int = 4

    val usuario = UsuarioController().getUsuario(this)?.nombreUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_stroop_grid)


        cronometro = Chronometer(this)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoEspera)
        textTiempo = findViewById(R.id.textTiempoEspera)
        colores = findViewById(R.id.gridColores)
        val longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        val longTiempoEspera = intent.getLongExtra("longTiempoEspera", 10000)
        arrayColores = intent.getStringArrayListExtra("arrayColores") as ArrayList<String>
        arrayEliminar = intent.getStringArrayListExtra("arrayEliminar") as ArrayList<String>
        numeroFallosPermitidos = intent.getStringExtra("fallosPermitidos").toString()
        tamanyoTablero = intent.getStringExtra("tamanyoTablero")!!
        val tipoStroop = intent.getCharSequenceExtra("tipoStroop")
        val fondo = intent.getBooleanExtra("fondo", false)
        ajustarTablero()

        botonCerrar = findViewById(R.id.imageButtonCerrarCuestionario)
        botonCerrar.setOnClickListener{
           confirmarSalida()
        }
        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                confirmarSalida()
            }
        }
        onBackPressedDispatcher.addCallback(
            this,
            callback
        )

        for (i in 0 until colores.childCount){
            botonColor = colores.getChildAt(i) as Button
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
            botonColor.contentDescription = colorAleatorio
            botonColor.text = colorAleatorio
            when(tipoStroop){
                "Congruente" -> rellenarBotonCongruente(color, fondo)
                "Incongruente" -> rellenarBotonIncongruente(color, fondo)
                "Mixto" -> rellenarBotonMixto(color, fondo)
            }
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
                val btnColor = colores.focusedChild as Button
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
                                aciertos++
                                blancos--
                                setDrawableColor(btnColor)
                                btnColor.clearAnimation()
                                dialog.dismiss()
                                if (blancos == 0) {
                                    finalizarCuestionario(usuario.toString())
                                }
                            }else{
                                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                                val clk_rotate = AnimationUtils.loadAnimation(this, R.anim.view_shake)
                                colorSeleccion.startAnimation(clk_rotate)
                                fallos++
                                if(fallos > limiteFallos){
                                    cerrado = true
                                    finalizarCuestionario(usuario.toString())
                                    finish()
                                }
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
                if(!cerrado){
                    activarRealizacionCuestionario()
                    textTiempo.text = getString(R.string.tiempo_de_realizacion)
                    vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))

                    object : CountDownTimer(longTiempoRealizacion, 1000){
                        @SuppressLint("SetTextI18n")
                        override fun onTick(millisUntilFinished: Long) {
                            actualizarCronometro(millisUntilFinished)
                        }

                        override fun onFinish() {
                            if (!cerrado){
                                finalizarCuestionario(usuario.toString())
                                cerrado = true
                                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                                finish()
                            }
                        }
                    }.start()
                }
            }
        }.start()
    }

    private fun finalizarCuestionario(usuario: String) {
        val respuestasUsuario = ArrayList<String>()
        respuestasUsuario.add(aciertos.toString())
        respuestasUsuario.add(fallos.toString())
        respuestasUsuario.add(blancos.toString())
        respuestasUsuario.add(filas.toString())
        respuestasUsuario.add(columnas.toString())
        respuestasUsuario.add(arrayColores.size.toString())
        respuestasUsuario.add((longTiempoEspera / 1000).toString())
        respuestasUsuario.add((elapsedTime() / 1000).toString())
        respuestasUsuario.add((longTiempoRealizacion / 1000).toString())
        val calculosCuestionario: Map<String, String> = CalculoResultados().calculate("cuestionario_modrian_stroop", respuestasUsuario, usuario, this)
        val query = QueryParser().parse("cuestionario_modrian_stroop", calculosCuestionario)
        try {
            CuestionarioService().insertarCuestionario(query)
        } catch (_: Error) {

        }

        val bundle = Bundle().apply {
            for ((key, value) in calculosCuestionario) {
                putString(key, value)
            }
        }

        val intent = Intent(this, DetallesCuestionario::class.java)
        intent.putExtras(bundle)
        intent.putExtra("jsonResourceName","cuestionario_modrian_stroop")
        intent.putExtra("isResultado",true)
        startActivity(intent)
    }

    private fun ajustarTablero() {
        when (tamanyoTablero){
            "Grande" ->  limiteFallos = Fallos.calcularFallosPermitidos(colores.childCount, numeroFallosPermitidos) //Tablero por defecto
            "Mediano" -> ajustarTableroMediano()
            "Pequeño" -> ajustarTableroPequeño()
        }
    }

    private fun ajustarTableroPequeño() {
        val numeroCasillasEliminar = 12
        for (i in colores.childCount - 1  downTo  colores.childCount - numeroCasillasEliminar){
            botonColor = colores.getChildAt(i) as Button
            botonColor.isEnabled = false
            botonColor.isActivated = false
            botonColor.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(colores.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
        blancos = 12
        filas = 3
    }

    private fun ajustarTableroMediano() {
        val numeroCasillasEliminar = 8
        for (i in colores.childCount - 1  downTo  colores.childCount - numeroCasillasEliminar){
            botonColor = colores.getChildAt(i) as Button
            botonColor.isEnabled = false
            botonColor.isActivated = false
            botonColor.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(colores.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
        blancos = 16
        filas = 4
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

    private fun rellenarBotonCongruente(color: Int, fondo: Boolean) {
        botonColor.setTextColor(getColor(color))
        if (fondo) {
            val drawable = botonColor.background.mutate() as GradientDrawable
            drawable.setColor(getColor(color))
        }
    }
    private fun rellenarBotonIncongruente(colorOriginal: Int, fondo: Boolean) {
        var colorAleatorio : Int = getColorAleatorio()
        while (colorAleatorio == colorOriginal){
            colorAleatorio = getColorAleatorio()
        }
        if (fondo){
            var colorFondo : Int = getColorAleatorio()
            while (colorFondo == colorOriginal || colorFondo == colorAleatorio){
                colorFondo = getColorAleatorio()
            }
            val drawable = botonColor.background.mutate() as GradientDrawable
            drawable.setColor(getColor(colorFondo))
        }
        botonColor.setTextColor(getColor(colorAleatorio))
    }
    private fun rellenarBotonMixto(colorOriginal: Int, fondo: Boolean) {
        when ((0..1).random()) {
            0 ->  rellenarBotonCongruente(colorOriginal, fondo)
            1 ->  rellenarBotonIncongruente(colorOriginal, fondo)
        }
    }
    private fun getColorAleatorio(): Int {
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
        return color
    }
    private fun estaMarcado(gridBotonColor: ImageButton, arrayColores: ArrayList<String>): Boolean {
        for (textColor in arrayColores){
            if (textColor == gridBotonColor.contentDescription){
                return true
            }
        }
        return false
    }
    private fun activarRealizacionCuestionario(){
        for (i in 0 until colores.childCount){
            botonColor = colores.getChildAt(i) as Button
            val drawable = botonColor.background.mutate() as GradientDrawable
            drawable.setColor(getColor(R.color.azul_banner_cuestionarios))
            botonColor.text = ""
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
    private fun elapsedTime(): Long {
        return (SystemClock.elapsedRealtime() - cronometro.base) - longTiempoEspera
    }
    private fun confirmarSalida() {
        val alertBuilder = AlertDialog.Builder(this)
            .setTitle("¿Seguro que desea salir?")
            .setPositiveButton("No", null)
            .setNegativeButton("Si", null)

        val mAlertDialog = alertBuilder.create()

        mAlertDialog.show()

        val botonNo = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        botonNo.setOnClickListener {
            mAlertDialog.cancel()
        }

        val botonSi = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        botonSi.setOnClickListener {
            mAlertDialog.cancel()
            cerrado = true
            finish()
        }
    }
}