package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uma.menpas.R
import com.uma.menpas.models.D2
import com.uma.menpas.models.adapters.AdaptadorD2

class CuestionarioD2 : AppCompatActivity() {
    lateinit var recyclerViewD2: RecyclerView
    lateinit var adaptadorD2: AdaptadorD2
    lateinit var listaD2: ArrayList<D2>
    lateinit var progressBar: ProgressBar
    lateinit var textProgressBar: TextView
    lateinit var crono: Chronometer
    lateinit var botonCerrarCuestionario: ImageButton
    private var contadorFilas = 0
    private val NUMERO_FILAS_D2 = 14
    private val NUMERO_D2 = 47
    private val SECS_FILA_D2 = 20000
    companion object {
        lateinit var myOnClickListener: myOnClickListener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d2)

        progressBar = findViewById(R.id.progressBarCuestionario)
        textProgressBar = findViewById(R.id.numeroPreguntaActual)
        crono = findViewById(R.id.tiempoCrono)
        botonCerrarCuestionario = findViewById(R.id.imageButtonCerrarDesplegable)

        botonCerrarCuestionario.setOnClickListener {
            finish()
        }

        myOnClickListener = myOnClickListener(this)
        listaD2 = ArrayList()
        adaptadorD2 = AdaptadorD2(listaD2)
        recyclerViewD2 = findViewById(R.id.recyclerViewD2)
        val numberOfColumns = 10
        recyclerViewD2.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerViewD2.addItemDecoration(DividerItemDecoration(this, 1))
        recyclerViewD2.adapter = adaptadorD2

        rellenarFilaD2()
        contadorFilas++
        progressBar.progress = contadorFilas
        textProgressBar.text = contadorFilas.toString()
        crono.start()
        countDownTimer()

    }

    private fun countDownTimer(){
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        object : CountDownTimer(SECS_FILA_D2.toLong(), 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if(contadorFilas == NUMERO_FILAS_D2){
                    showToast("Cuestionario D2 finalizado")
                    crono.stop()
                }else{
                    listaD2.clear()
                    rellenarFilaD2()
                    contadorFilas++
                    progressBar.progress = contadorFilas
                    textProgressBar.text = contadorFilas.toString()
                    adaptadorD2.notifyDataSetChanged()
                    countDownTimer()
                }
                vibrator.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
            }

        }.start()
    }
    private fun rellenarFilaD2(){
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
}