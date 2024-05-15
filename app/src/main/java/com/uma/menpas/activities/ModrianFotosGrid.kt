package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Chronometer
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.uma.menpas.R
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.CalculoResultados
import com.uma.menpas.utils.Fallos
import com.uma.menpas.utils.QueryParser
import java.util.concurrent.TimeUnit

class ModrianFotosGrid : BaseActivity() {

    lateinit var fotos : GridLayout
    lateinit var botonFoto : ImageButton
    lateinit var tamanyoTablero : String
    lateinit var textTiempo : TextView
    lateinit var crono : TextView
    private var limiteFallos: Int = 0
    private var cerrado: Boolean = false
    private var numeroFallosPermitidos : String = ""
    lateinit var botonCerrar : ImageButton
    lateinit var cronometro: Chronometer
    private lateinit var usuario: String

    private var aciertos: Int = 0
    private var fallos: Int = 0
    private var blancos: Int = 15
    private var filas: Int = 0
    private var columnas: Int = 3
    var numImg : Int = 0
    private var longTiempoEspera: Long = 0
    private var longTiempoRealizacion: Long = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_fotos_grid)
        usuario = intent.getStringExtra("usuario") as String
        val numTotalImg = 7 ///HARDCODE TODO
        cronometro = Chronometer(this)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoEspera)
        textTiempo = findViewById(R.id.textTiempoEspera)
        val longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        val longTiempoEspera = intent.getLongExtra("longTiempoEspera", 10000)
        tamanyoTablero = intent.getStringExtra("tamanyoTablero")!!
        numImg = intent.getStringExtra("numImg")!!.toInt()
        fotos = findViewById(R.id.gridFotos)
        numeroFallosPermitidos = intent.getStringExtra("fallosPermitidos").toString()
        ajustarTablero()

        for (i in 0 until fotos.childCount){
            botonFoto = fotos.getChildAt(i) as ImageButton
            val fotoAleatoria = (0 until numImg).random()
            val foto = when(fotoAleatoria){
                0 -> R.drawable.img0
                1 -> R.drawable.img1
                2 -> R.drawable.img2
                3 -> R.drawable.img3
                4 -> R.drawable.img4
                5 -> R.drawable.img5
                6 -> R.drawable.img6
                else -> R.drawable.menpas_logo_sombreado
            }
            botonFoto.background = AppCompatResources.getDrawable(this, foto)
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

                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.desplegable_fotos, null)


                val alphaBlink = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
                alphaBlink.interpolator = LinearInterpolator()
                btnFoto.startAnimation(alphaBlink)


                val gridFotosSeleccion = view.findViewById<GridLayout>(R.id.gridFotosSeleccion)
                for (i in 0 until gridFotosSeleccion.childCount){
                    val fotoSeleccion = gridFotosSeleccion.getChildAt(i) as ImageButton
                    if (estaMarcado(fotoSeleccion)){
                        fotoSeleccion.setOnClickListener {
                            if (btnFoto.contentDescription == fotoSeleccion.contentDescription) {
                                aciertos++
                                blancos--
                                btnFoto.background = fotoSeleccion.background
                                btnFoto.clearAnimation()
                                dialog.dismiss()
                                if (blancos == 0) {
                                    finalizarCuestionario(usuario)
                                }
                            }else{
                                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                                val clk_rotate = AnimationUtils.loadAnimation(this, R.anim.view_shake)
                                fotoSeleccion.startAnimation(clk_rotate)
                                fallos++
                                if(fallos > limiteFallos){
                                    cerrado = true
                                    finalizarCuestionario(usuario)
                                    finish()
                                }
                            }
                        }
                    }
                }

                val btnCerrar = view.findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
                btnCerrar.setOnClickListener{
                    btnFoto.isActivated = true
                    btnFoto.isEnabled = true
                    btnFoto.clearAnimation()
                    dialog.dismiss()
                }

                for(i in numImg until numTotalImg ){
                    val imageButtonFoto = when(i){
                        0 -> view.findViewById<ImageButton>(R.id.foto00)
                        1 -> view.findViewById(R.id.foto01)
                        2 -> view.findViewById(R.id.foto02)
                        3 -> view.findViewById(R.id.foto10)
                        4 -> view.findViewById(R.id.foto11)
                        5 -> view.findViewById(R.id.foto12)
                        6 -> view.findViewById(R.id.foto20)

                        else -> view.findViewById(R.id.imageButtonCerrarDesplegable)
                    }
                    gridFotosSeleccion.removeViewAt(gridFotosSeleccion.indexOfChild(imageButtonFoto))
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
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))

                    object : CountDownTimer(longTiempoRealizacion, 1000){
                        @SuppressLint("SetTextI18n")
                        override fun onTick(millisUntilFinished: Long) {
                            actualizarCronometro(millisUntilFinished)
                        }

                        override fun onFinish() {
                            if (!cerrado){
                                finalizarCuestionario(usuario)
                                cerrado = true
                                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
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
        respuestasUsuario.add(numImg.toString())
        respuestasUsuario.add((longTiempoEspera / 1000).toString())
        respuestasUsuario.add((elapsedTime() / 1000).toString())
        respuestasUsuario.add((longTiempoRealizacion / 1000).toString())
        val calculosCuestionario: Map<String, String> = CalculoResultados().calculate("cuestionario_modrian_fotos", respuestasUsuario, usuario, this)
        val query = QueryParser().parse("cuestionario_modrian_fotos", calculosCuestionario)
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
        intent.putExtra("jsonResourceName","cuestionario_modrian_fotos")
        intent.putExtra("isResultado",true)
        startActivity(intent)
    }

    private fun ajustarTablero() {
        when (tamanyoTablero){
            "Grande" ->  limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount, numeroFallosPermitidos) //Tablero por defecto
            "Mediano" -> ajustarTableroMediano()
            "Pequeño" -> ajustarTableroPequeño()
        }
    }

    private fun ajustarTableroMediano() {
        for (i in fotos.childCount - 1  downTo  fotos.childCount - 3){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount - 8, numeroFallosPermitidos)
        blancos = 12
        filas = 4
    }

    private fun ajustarTableroPequeño() {
        for (i in fotos.childCount - 1  downTo  fotos.childCount - 6){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount - 6, numeroFallosPermitidos)
        blancos = 9
        filas = 3
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

    private fun estaMarcado(gridBotonFoto: ImageButton): Boolean {
        for (i in 0 until numImg){
            if (i.toString() == gridBotonFoto.contentDescription){
                return true
            }
        }
        return false
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