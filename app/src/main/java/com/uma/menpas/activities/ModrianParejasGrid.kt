package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Chronometer
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.CalculoResultados
import com.uma.menpas.utils.Fallos
import com.uma.menpas.utils.QueryParser
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
    private var numeroCasillasEliminar: Int = 0
    lateinit var cronometro: Chronometer
    private var aciertos: Int = 0
    private var blancos: Int = 0
    private lateinit var usuario: String
    private var filas: Int = 0
    private var columnas: Int = 3
    private val JSON_RESOURCE_NAME = "cuestionario_modrian_parejas"
    private var longTiempoRealizacion: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_parejas_grid)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        crono = findViewById(R.id.tiempoRealizacion)
        textTiempo = findViewById(R.id.textTiempoRealizacion)
        fotos = findViewById(R.id.gridFotos)
        cronometro = Chronometer(this)
        usuario = intent.getStringExtra("usuario") as String
        longTiempoRealizacion = intent.getLongExtra("longTiempoRealizacion", 60000)
        numeroFallosPermitidos = intent.getStringExtra("fallosPermitidos").toString()
        tamanyoTablero = intent.getStringExtra("tamanyoTablero")!!
        numeroCasillasEliminar = ajustarTablero()
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

        fotoSeleccionada = ImageButton(this)
        fotoSeleccionada.contentDescription = "-1"

        for (i in 0 until fotos.childCount - numeroCasillasEliminar){
            botonFoto = fotos.getChildAt(i) as ImageButton
            val indiceFotoAleatoria = (0 until fotosArray.size).random()
            val fotoAleatoria = fotosArray[indiceFotoAleatoria]
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

                val handler = Handler()  //DEPRECATED???

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
                                showToast("Limite de Fallos Alcanzado")
                                finalizarCuestionario(usuario)
                                finish()
                            }
                        }else{
                            aciertos+=2
                            blancos-=2
                            if(blancos == 0){
                                finalizarCuestionario(usuario)
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
                    showToast("Limite de Tiempo Alcanzado")
                    finalizarCuestionario(usuario)
                    finish()
                }
            }

        }.start()
    }

    private fun ajustarTablero(): Int {
        return when (tamanyoTablero){
            "Grande" ->  ajustarTableroGrande()
            "Mediano" -> ajustarTableroMediano()
            "Pequeño" -> ajustarTableroPequeño()
            else -> 0
        }
    }

    private fun ajustarTableroGrande(): Int {
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount, numeroFallosPermitidos)
        fotosArray = generarFotosArray(fotos.childCount)
        blancos = fotos.childCount
        filas = 5
        return 0
    }

    private fun ajustarTableroMediano(): Int {
        val numeroCasillasEliminar = 4
        for (i in fotos.childCount - 1  downTo  fotos.childCount - numeroCasillasEliminar){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
        fotosArray = generarFotosArray(fotos.childCount - numeroCasillasEliminar)
        blancos = fotos.childCount - numeroCasillasEliminar
        filas = 4
        return numeroCasillasEliminar
    }

    private fun ajustarTableroPequeño(): Int {
        val numeroCasillasEliminar = 6
        for (i in fotos.childCount - 1  downTo  fotos.childCount - numeroCasillasEliminar){
            botonFoto = fotos.getChildAt(i) as ImageButton
            botonFoto.isEnabled = false
            botonFoto.isActivated = false
            botonFoto.visibility = View.GONE
        }
        limiteFallos = Fallos.calcularFallosPermitidos(fotos.childCount - numeroCasillasEliminar, numeroFallosPermitidos)
        fotosArray = generarFotosArray(fotos.childCount - numeroCasillasEliminar)
        blancos = fotos.childCount - numeroCasillasEliminar
        filas = 3
        return numeroCasillasEliminar
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
        for (i in 0 until fotos.childCount - numeroCasillasEliminar){
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
    private fun finalizarCuestionario(usuario: String) {
        val respuestasUsuario = ArrayList<String>()
        respuestasUsuario.add(aciertos.toString())
        respuestasUsuario.add(fallos.toString())
        respuestasUsuario.add(blancos.toString())
        respuestasUsuario.add(filas.toString())
        respuestasUsuario.add(columnas.toString())
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
        return (SystemClock.elapsedRealtime() - cronometro.base)
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