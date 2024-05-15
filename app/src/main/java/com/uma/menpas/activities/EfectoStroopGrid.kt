package com.uma.menpas.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.uma.menpas.R
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.CalculoResultados
import com.uma.menpas.utils.QueryParser
import kotlin.random.Random

class EfectoStroopGrid : BaseActivity() {
    lateinit var timer: CountDownTimer
    var timerCurrentTime: Float = 0.0f
    var timerTotalTime: Float = 0.0f
    
    var intentTiempoExposicion: Long = 1000
    lateinit var intentColores: ArrayList<String>
    lateinit var intentTipo: String
    var intentNumeroPresentaciones: Int = 0
    var intentFondo: Boolean = true
    lateinit var intentUsuario: String
    
    lateinit var viewShowcase: View
    lateinit var viewTextShowcase: TextView
    lateinit var viewGrid: GridLayout
    lateinit var  viewTiempoEspera: TextView
    lateinit var viewTextAciertos: TextView
    lateinit var viewTextErrores: TextView
    lateinit var viewTextErroresOmision: TextView
    
    var valueRandomColor: Int = 0
    var valueAciertos: Int = 0
    var valueErrores: Int = 0
    var valueErroresOmision: Int = 0

    private fun initIntentParams() {
        intentColores = intent.getStringArrayListExtra("arrayColores")!!
        intentTipo = intent.getStringExtra("modalidad")!!
        intentTiempoExposicion = intent.getLongExtra("tiempo", 1000)
        intentNumeroPresentaciones = intent.getIntExtra("numeroPresentaciones",1)
        intentFondo = intent.getBooleanExtra("fondo", true)
        intentUsuario = intent.getStringExtra("usuario")!!
    }

    private fun initXMLElements() {
        viewShowcase = findViewById(R.id.showcase)
        viewTextShowcase = findViewById(R.id.text_showcase)
        viewGrid = findViewById(R.id.gridStroop)
        viewTextAciertos = findViewById(R.id.textNumeroAciertos)
        viewTextErrores = findViewById(R.id.textNumeroErrores)
        viewTextErroresOmision = findViewById(R.id.textNumeroErroresOmision)
        viewTiempoEspera = findViewById(R.id.tiempoEsperaStroop)
    }

    private fun colorSelect(color: String): Int {
        when (color) {
            "rojo" -> return (getColor(R.color.rojo))
            "marrón" -> return (getColor(R.color.marron))
            "verde" -> return (getColor(R.color.verde))
            "gris" -> return (getColor(R.color.gris))
            "azul" -> return (getColor(R.color.azul))
            "rosa" -> return (getColor(R.color.rosa))
            "amarillo" -> return (getColor(R.color.amarillo))
            "violeta" -> return (getColor(R.color.violeta))
            "negro" -> return (getColor(R.color.black))
            "fucsia" -> return (getColor(R.color.fucsia))
            "naranja" -> return (getColor(R.color.naranja))
            "cyan" -> return (getColor(R.color.cyan))
            else -> return (getColor(R.color.white))
        }
    }

    private fun generateviewShowcase() {
        valueRandomColor = Random.nextInt(0, intentColores.size)
        var randomText = 0
        var randomintentFondo = -1
        when (intentTipo) {
            "Congruente" -> randomText = valueRandomColor
            "Incongruente" -> do { randomText = Random.nextInt(0, intentColores.size) }
                while (randomText == valueRandomColor)
            "Mixto" -> randomText = Random.nextInt(0, intentColores.size)
        }
        viewTextShowcase.text= intentColores[randomText].toUpperCase()
        viewTextShowcase.setTextColor(colorSelect(intentColores[valueRandomColor]))
        if (intentFondo)  {
            randomintentFondo = Random.nextInt(0, intentColores.size)
            val viewShowcaseDrawable = viewShowcase.background.mutate() as GradientDrawable
            viewShowcaseDrawable.setColor(colorSelect(intentColores[randomintentFondo]))
        }
    }

    private fun generateColorButtons() {
        for (i in 0 until viewGrid.childCount) {
            val button = viewGrid.getChildAt(i) as Button
            if (i < intentColores.size) {
                button.setBackgroundColor(colorSelect(intentColores[i]))
            } else {
                button.alpha = 0.0f
                button.isEnabled = false
            }
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun restartTimer() {
        timer.cancel()
        timerCurrentTime = 0f
        viewTiempoEspera.text = "0"
        timer.start()
    }

    private fun hasFinished() {
        if (valueAciertos + valueErroresOmision >= intentNumeroPresentaciones) {
            timer.cancel()
            val dialog = AlertDialog.Builder(this)
                .setMessage("Test finalizado")
                .setPositiveButton("Aceptar") {_,_ ->}
                .create()
            dialog.show()

            val respuestasUsuario = arrayListOf(valueAciertos.toString(), valueErrores.toString(), timerTotalTime.toString(),
                intentColores.size.toString(), intentTipo,intentFondo.toString(), intentTiempoExposicion.toString(),
                intentNumeroPresentaciones.toString(), valueErroresOmision.toString()) as ArrayList<String>

            val calculosCuestionario: Map<String, String> = CalculoResultados().calculate("cuestionario_stroop", respuestasUsuario, intentUsuario, this)

            val query = QueryParser().parse("cuestionario_stroop", calculosCuestionario)

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
            intent.putExtra("jsonResourceName","cuestionario_stroop")
            intent.putExtra("isResultado",true)
            startActivity(intent)



        } else {
            generateviewShowcase()
            restartTimer()
        }
    }

    private fun startTimer() {
        timer = object: CountDownTimer(intentTiempoExposicion, 100) {
            override fun onTick(p0: Long) {
                timerCurrentTime += 0.1f
                timerTotalTime += 0.1f
                viewTiempoEspera.text = timerCurrentTime.toString().substring(0, 3)
            }
            override fun onFinish() {
                timerCurrentTime = 0f
                valueErroresOmision++
                viewTextErroresOmision.text = valueErroresOmision.toString()
                hasFinished()
            }
        }
        timer.start()
    }

    private fun clickButtons() {
        for (i in 0 until viewGrid.childCount) {
            val button = viewGrid.getChildAt(i) as Button
            button.setOnClickListener {
                if (valueRandomColor.equals(i)) {
                    valueAciertos++
                    viewTextAciertos.text = valueAciertos.toString()
                    hasFinished()
                } else {
                    valueErrores++
                    viewTextErrores.text = valueErrores.toString()
                    showToast("Color incorrecto")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_efecto_stroop)

        initIntentParams()
        initXMLElements()
        generateviewShowcase()
        generateColorButtons()
        startTimer()
        clickButtons()
        val botonCerrar = findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
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
            finish()
        }
    }
}