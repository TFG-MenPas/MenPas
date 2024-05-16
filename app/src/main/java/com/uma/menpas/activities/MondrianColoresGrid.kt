package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.InputType
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Chronometer
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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


class MondrianColoresGrid : BaseActivity() {
    lateinit var colores : GridLayout
    lateinit var botonColor : ImageButton
    lateinit var botonCerrar : ImageButton
    lateinit var arrayColores : ArrayList<String>
    lateinit var arrayEliminar : ArrayList<String>
    lateinit var textTiempo : TextView
    lateinit var crono : TextView
    lateinit var cronometro: Chronometer
    private lateinit var numeroFallosPermitidos: String
    private var limiteFallos: Int = 0
    private var fallos: Int = 0
    private var aciertos: Int = 0
    private var blancos: Int = 0
    private var cerrado: Boolean = false
    private lateinit var tamanyoTablero : String

    private var filas: Int = 0
    private var columnas: Int = 4
    private val JSON_RESOURCE_NAME = "cuestionario_modrian_colores"
    private var longTiempoRealizacion: Long = 0
    private var longTiempoEspera: Long = 0

    val usuario = UsuarioController().getUsuario(this)?.nombreUsuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores_grid)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoEspera)
        cronometro = Chronometer(this)
        textTiempo = findViewById(R.id.textTiempoEspera)
        longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        longTiempoEspera = intent.getLongExtra("longTiempoEspera", 10000)
        arrayColores = intent.getStringArrayListExtra("arrayColores")!!
        arrayEliminar = intent.getStringArrayListExtra("arrayEliminar")!!
        numeroFallosPermitidos = intent.getStringExtra("fallosPermitidos").toString()
        colores = findViewById(R.id.gridColores)
        tamanyoTablero = intent.getStringExtra("tamanyoTablero")!!

        ajustarTablero()
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
                                aciertos++
                                blancos--
                                setDrawableColor(btnColor)
                                btnColor.clearAnimation()
                                dialog.dismiss()
                                if(blancos == 0){
                                    finalizarCuestionario(usuario.toString())
                                }
                            }else{
                                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                                val clk_rotate = AnimationUtils.loadAnimation(this, R.anim.view_shake)
                                colorSeleccion.startAnimation(clk_rotate)
                                fallos++
                                if(fallos > limiteFallos){
                                    cerrado = true
                                    showToast("Limite de Fallos Alcanzado")
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

        botonCerrar = findViewById(R.id.imageButtonCerrarDesplegable)
        botonCerrar.setOnClickListener {
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

        object : CountDownTimer(longTiempoEspera, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                actualizarCronometro(millisUntilFinished)
            }

            override fun onFinish() {
                if (!cerrado){
                    activarRealizacionCuestionario()
                    textTiempo.text = getString(R.string.tiempo_de_realizacion)
                    vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                    object : CountDownTimer(longTiempoRealizacion, 1000){
                        @SuppressLint("SetTextI18n")
                        override fun onTick(millisUntilFinished: Long) {
                            actualizarCronometro(millisUntilFinished)
                        }

                        override fun onFinish() {
                            if(!cerrado){
                                showToast("Limite de Tiempo Alcanzado")
                                finalizarCuestionario(usuario.toString())
                                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                                cerrado = true
                                finish()
                            }
                        }
                    }.start()
                }
            }
        }.start()
    }
    private fun ajustarTablero() {
        when (tamanyoTablero){
            "Grande" ->  ajustarTableroGrande()
            "Mediano" -> ajustarTableroMediano()
            "Pequeño" -> ajustarTableroPequeño()
        }
    }

    private fun ajustarTableroGrande(){
        limiteFallos = Fallos.calcularFallosPermitidos(colores.childCount, numeroFallosPermitidos)
        blancos = colores.childCount
        filas = 6
    }

    private fun ajustarTableroMediano() {
        val numeroCasillasEliminar = 8
        for (i in colores.childCount - 1  downTo  colores.childCount - numeroCasillasEliminar){
            botonColor = colores.getChildAt(i) as ImageButton
            botonColor.isEnabled = false
            botonColor.isActivated = false
            botonColor.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(colores.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
        blancos = colores.childCount - numeroCasillasEliminar
        filas = 4
    }

    private fun ajustarTableroPequeño() {
        val numeroCasillasEliminar = 12
        for (i in colores.childCount - 1  downTo  colores.childCount - numeroCasillasEliminar){
            botonColor = colores.getChildAt(i) as ImageButton
            botonColor.isEnabled = false
            botonColor.isActivated = false
            botonColor.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(colores.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
        blancos = colores.childCount - numeroCasillasEliminar
        filas = 3
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
        val calculosCuestionario: Map<String, String> = CalculoResultados().calculate(JSON_RESOURCE_NAME, respuestasUsuario, usuario, this)
        val query = QueryParser().parse(JSON_RESOURCE_NAME, calculosCuestionario)
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
        intent.putExtra("jsonResourceName",JSON_RESOURCE_NAME)
        intent.putExtra("isResultado",true)
        startActivity(intent)
    }
    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
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