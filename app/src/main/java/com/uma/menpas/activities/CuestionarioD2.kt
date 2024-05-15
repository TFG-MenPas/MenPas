package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uma.menpas.R
import com.uma.menpas.models.D2
import com.uma.menpas.models.adapters.AdaptadorD2
import com.uma.menpas.services.CuestionarioService
import com.uma.menpas.utils.CalculoResultados
import com.uma.menpas.utils.QueryParser

class CuestionarioD2 : BaseActivity() {
    lateinit var recyclerViewD2: RecyclerView
    lateinit var adaptadorD2: AdaptadorD2
    lateinit var listaD2: ArrayList<D2>
    lateinit var progressBar: ProgressBar
    lateinit var textProgressBar: TextView
    lateinit var crono: Chronometer
    lateinit var botonCerrarCuestionario: ImageButton
    lateinit var vibrator: Vibrator
    private var contadorFilas = 0
    private val NUMERO_FILAS_D2 = 14
    private val NUMERO_D2 = 47
    private val SECS_FILA_D2 = 20000
    private var d2CerradoAntesDeFinalizar = false
    private var azulClaro = 0
    private var azulOscuro = 0
    private lateinit var filasD2Original: List<List<D2>>
    private lateinit var tipoTestD2: String
    private val JSON_RESOURCE_NAME = "cuestionario_d2"
    private lateinit var respuestasUsuario: ArrayList<String>
    private lateinit var usuario: String
    private lateinit var TR1_14: String
    private lateinit var TA1_14: String
    private lateinit var O1_14: String
    private lateinit var C1_14: String

    companion object {
        lateinit var myOnClickListener: myOnClickListener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d2)

        tipoTestD2 = intent.getStringExtra("json_resource_name").toString()
        if (tipoTestD2 == "cuestionario_d2_original"){
            filasD2Original = inicializarFilasD2Original()
        }
        respuestasUsuario = ArrayList()
        TR1_14 = ""
        TA1_14 = ""
        O1_14 = ""
        C1_14 = ""
        usuario = intent.getStringExtra("usuario") as String
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        progressBar = findViewById(R.id.progressBarCuestionario)
        textProgressBar = findViewById(R.id.numeroPreguntaActual)
        crono = findViewById(R.id.tiempoCrono)
        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarDesplegable)

        botonCerrarCuestionario.setOnClickListener {
            confirmarSalida()
            finish()
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
        azulClaro = getColor(R.color.light_blue)
        azulOscuro = getColor(R.color.dark_blue)

        myOnClickListener = myOnClickListener(this)
        listaD2 = ArrayList()
        adaptadorD2 = AdaptadorD2(listaD2)
        recyclerViewD2 = findViewById(R.id.recyclerViewD2)
        val numberOfColumns = 10
        recyclerViewD2.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerViewD2.addItemDecoration(DividerItemDecoration(this, 1))
        recyclerViewD2.adapter = adaptadorD2

        if (tipoTestD2 == "cuestionario_d2_original"){
            rellenarFilaD2Original(contadorFilas)
            respuestasUsuario.add("Original")
        }else{
            rellenarFilaD2Aleatorio()
            respuestasUsuario.add("Aleatorio")
        }
        contadorFilas++
        progressBar.progress = contadorFilas
        textProgressBar.text = contadorFilas.toString()
        crono.start()
        countDownTimer()

    }

    private fun countDownTimer(){
        object : CountDownTimer(SECS_FILA_D2.toLong(), 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if(!d2CerradoAntesDeFinalizar){
                    TR1_14 += "${tiempoReaccionPorFila()};"
                    TA1_14 += "${totalAciertosPorFila()};"
                    O1_14 += "${erroresOmisionPorFila()};"
                    C1_14 += "${erroresComisionPorFila()};"
                    if(contadorFilas == NUMERO_FILAS_D2){
                        showToast("Cuestionario D2 finalizado")
                        respuestasUsuario.add(TR1_14.dropLast(1)) //Drop last para quitar el ultimo ;
                        respuestasUsuario.add(TA1_14.dropLast(1))
                        respuestasUsuario.add(O1_14.dropLast(1))
                        respuestasUsuario.add(C1_14.dropLast(1))
                        finalizarCuestionario(usuario)
                        crono.stop()
                    }else{
                        listaD2.clear()
                        if (tipoTestD2 == "cuestionario_d2_original"){
                            rellenarFilaD2Original(contadorFilas)
                        }else{
                            rellenarFilaD2Aleatorio()
                        }
                        contadorFilas++
                        progressBar.progress = contadorFilas
                        textProgressBar.text = contadorFilas.toString()
                        adaptadorD2.notifyDataSetChanged()
                        countDownTimer()
                    }
                    vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
                }
            }
        }.start()
    }

    private fun tiempoReaccionPorFila(): Int {
        var tiempoReaccion = 0
        for (i in 0 until  recyclerViewD2.childCount){
            val d2 = recyclerViewD2.getChildAt(i) as LinearLayout
            val textD2Letra : TextView = d2.findViewById(R.id.textLetra)
            if(textD2Letra.currentTextColor == azulClaro){
                tiempoReaccion = i
            }
        }
        return tiempoReaccion
    }

    private fun erroresOmisionPorFila(): Int {
        var erroresOmisionPorFila = 0
        for (i in 0 until  recyclerViewD2.childCount){
            val d2 = recyclerViewD2.getChildAt(i) as LinearLayout
            val textD2RayaArriba : TextView = d2.findViewById(R.id.textRayaArriba)
            val textD2Letra : TextView = d2.findViewById(R.id.textLetra)
            val textD2RayaAbajo : TextView = d2.findViewById(R.id.textRayaAbajo)
            if(textD2Letra.currentTextColor == azulClaro){
                continue
            }
            if (textD2Letra.text != "d"){
                continue
            }
            if(sumaRayas(textD2RayaArriba,textD2RayaAbajo) == 2){
                erroresOmisionPorFila++
            }
        }
        return erroresOmisionPorFila
    }

    private fun erroresComisionPorFila(): Int {
        var erroresComisionPorFila = 0
        for (i in 0 until  recyclerViewD2.childCount){
            val d2 = recyclerViewD2.getChildAt(i) as LinearLayout
            val textD2RayaArriba : TextView = d2.findViewById(R.id.textRayaArriba)
            val textD2Letra : TextView = d2.findViewById(R.id.textLetra)
            val textD2RayaAbajo : TextView = d2.findViewById(R.id.textRayaAbajo)
            if(textD2Letra.currentTextColor == azulOscuro){
                continue
            }
            if (textD2Letra.text != "d"){
                erroresComisionPorFila++
                continue
            }
            if(sumaRayas(textD2RayaArriba,textD2RayaAbajo) != 2){
                erroresComisionPorFila++
            }
        }
        return erroresComisionPorFila
    }

    private fun totalAciertosPorFila(): Int {
        var totalAciertosPorFila = 0
        for (i in 0 until  recyclerViewD2.childCount){
            val d2 = recyclerViewD2.getChildAt(i) as LinearLayout
            val textD2RayaArriba : TextView = d2.findViewById(R.id.textRayaArriba)
            val textD2Letra : TextView = d2.findViewById(R.id.textLetra)
            val textD2RayaAbajo : TextView = d2.findViewById(R.id.textRayaAbajo)
            if(textD2Letra.currentTextColor == azulOscuro){
                continue
            }
            if (textD2Letra.text != "d"){
                continue
            }
            if(sumaRayas(textD2RayaArriba,textD2RayaAbajo) == 2){
                totalAciertosPorFila++
            }
        }
        return totalAciertosPorFila
    }

    private fun sumaRayas(textD2RayaArriba: TextView, textD2RayaAbajo: TextView): Int {
        val rayasArriba = when(textD2RayaArriba.text){
            "''" -> 2
            "'" -> 1
            "" -> 0
            else -> 0
        }
        val rayasAbajo = when(textD2RayaAbajo.text){
            "''" -> 2
            "'" -> 1
            "" -> 0
            else -> 0
        }
        return rayasArriba + rayasAbajo
    }

    private fun rellenarFilaD2Aleatorio(){
        var rayaArriba : String
        var letra : String
        var rayaAbajo :String
        for(j in 0 until NUMERO_D2){
            rayaArriba = when((0..2).random()){
                0 -> "''"
                1 -> "'"
                2 -> ""
                else -> {
                    ""
                }
            }
            letra = when((0..1).random()){
                0 -> "d"
                1 -> "p"
                else -> {
                    "d"
                }
            }
            rayaAbajo = when((0..2).random()){
                0 -> "''"
                1 -> "'"
                2 -> ""
                else -> {
                    ""
                }
            }
            listaD2.add(D2(rayaArriba, letra, rayaAbajo))
        }
    }

    private fun rellenarFilaD2Original(contador : Int){
        val filad2 = filasD2Original[contador]
        for (d2 in filad2){
            listaD2.add(d2)
        }
    }
    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    class myOnClickListener(cuestionarioD2 : CuestionarioD2) : View.OnClickListener{
        val context = cuestionarioD2
        val azulClaro = context.getColor(R.color.light_blue)
        val azulOscuro = context.getColor(R.color.dark_blue)
        override fun onClick(v: View) {
            val viewHolder : RecyclerView.ViewHolder = context.recyclerViewD2.getChildViewHolder(v)
            val textD2RayaArriba : TextView = viewHolder.itemView.findViewById(R.id.textRayaArriba)
            val textD2Letra : TextView = viewHolder.itemView.findViewById(R.id.textLetra)
            val textD2RayaAbajo : TextView = viewHolder.itemView.findViewById(R.id.textRayaAbajo)
            if(textD2Letra.currentTextColor == azulOscuro){
                textD2RayaArriba.setTextColor(azulClaro)
                textD2Letra.setTextColor(azulClaro)
                textD2RayaAbajo.setTextColor(azulClaro)
            }else{
                textD2RayaArriba.setTextColor(azulOscuro)
                textD2Letra.setTextColor(azulOscuro)
                textD2RayaAbajo.setTextColor(azulOscuro)
            }
        }
    }
    private fun inicializarFilasD2Original(): List<List<D2>> {
        return listOf(
            listOf(
                D2("","d","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","p","'"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("''","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","d","'"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d","''"),
                D2("","p","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("'","p",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("''","d","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("''","d","''"),
                D2("'","p","''"),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("'","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
            ),
            listOf(
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","p",""),
                D2("","d","''"),
                D2("","d","'"),
                D2("''","d","''"),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("'","d","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","d","''"),
                D2("'","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","p","'"),
                D2("","d","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("","d","''"),
                D2("''","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("''","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("''","d",""),
            ),
            listOf(
                D2("''","d","''"),
                D2("'","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("''","d",""),
                D2("","p","'"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("'","d","''"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","d","''"),
                D2("''","d",""),
                D2("'","p","'"),
                D2("''","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
            ),
            listOf(
                D2("","d","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","p","'"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("''","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","d","'"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d","''"),
                D2("","p","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("'","p",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("''","d","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("'","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
            ),
            listOf(
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","p",""),
                D2("","d","''"),
                D2("","d","'"),
                D2("''","d","''"),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("'","d","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","d","''"),
                D2("'","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","p","'"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("","d","''"),
                D2("''","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("''","d",""),
                D2("","p","''"),
                D2("'","d","'"),
            ),
            listOf(
                D2("''","d","''"),
                D2("'","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("''","d",""),
                D2("","p","'"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("'","d","''"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","d","''"),
                D2("''","d",""),
                D2("'","p","'"),
                D2("''","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
            ),
            listOf(
                D2("","d","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","p","'"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("''","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","d","'"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d","''"),
                D2("","p","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("'","p",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("''","d","''"),
                D2("'","d",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("","d","''"),
                D2("'","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","d","''"),
            ),
            listOf(
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","p",""),
                D2("","d","''"),
                D2("","d","'"),
                D2("''","d","''"),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("'","d","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","d","''"),
                D2("'","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","p","'"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("","d","''"),
                D2("''","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("''","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("''","d",""),
            ),
            listOf(
                D2("''","d","''"),
                D2("'","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("''","d",""),
                D2("","p","'"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("'","d","''"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","d","''"),
                D2("''","d",""),
                D2("'","p","'"),
                D2("''","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
            ),
            listOf(
                D2("","d","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","p","'"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("''","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","d","'"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d","''"),
                D2("","p","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("'","p",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("''","d","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("'","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
            ),
            listOf(
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","p",""),
                D2("","d","''"),
                D2("","d","'"),
                D2("''","d","''"),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("'","d","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","d","''"),
                D2("'","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","p","'"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("","d","''"),
                D2("''","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("''","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("''","d",""),
            ),
            listOf(
                D2("''","d","''"),
                D2("'","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("''","d",""),
                D2("","p","'"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("'","d","''"),
                D2("'","d","'"),
                D2("","p","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","d","''"),
                D2("''","d",""),
                D2("'","p","'"),
                D2("''","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","d","'"),
                D2("''","d",""),
                D2("''","p",""),
                D2("'","d","'"),
            ),
            listOf(
                D2("","d","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("''","d",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","p","'"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("''","d",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","d","'"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d","''"),
                D2("","p","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("'","p",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("''","d","''"),
                D2("","d","''"),
                D2("'","d",""),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("'","d","''"),
                D2("''","d",""),
                D2("''","p",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
            ),
            listOf(
                D2("''","p",""),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","p",""),
                D2("","d","''"),
                D2("","d","'"),
                D2("''","d","''"),
                D2("'","d","'"),
                D2("''","p",""),
                D2("''","d","''"),
                D2("'","p",""),
                D2("'","d","''"),
                D2("","d","''"),
                D2("","d","''"),
                D2("''","p",""),
                D2("","d","''"),
                D2("'","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","''"),
                D2("","p","'"),
                D2("''","d",""),
                D2("'","d","'"),
                D2("","p","''"),
                D2("","d","'"),
                D2("''","p",""),
                D2("''","d","'"),
                D2("","d","''"),
                D2("''","d",""),
                D2("","d","'"),
                D2("''","p",""),
                D2("","d","''"),
                D2("","p","''"),
                D2("'","d","'"),
                D2("'","p","'"),
                D2("''","d",""),
                D2("'","p",""),
                D2("''","d",""),
                D2("","d","''"),
                D2("'","d","'"),
                D2("","d","''"),
                D2("''","p",""),
                D2("''","d",""),
                D2("","p","''"),
                D2("'","d","'"),
                D2("''","d",""),
            )
        )
    }
    private fun finalizarCuestionario(usuario: String) {
        val calculosCuestionario: Map<String, String> = CalculoResultados().calculate(JSON_RESOURCE_NAME, respuestasUsuario, usuario, this)
        val query = QueryParser().parse(JSON_RESOURCE_NAME, calculosCuestionario)
        try {
            CuestionarioService().insertarCuestionario(query)
            showToast("Éxito en la petición")
        } catch (e: Error) {
            showToast("Algo salió mal realizando la petición")
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
            d2CerradoAntesDeFinalizar = true
            finish()
        }
    }
}