package com.uma.menpas.activities

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.uma.menpas.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import java.lang.Thread.sleep
import kotlin.collections.ArrayList

class ModrianSimon : AppCompatActivity() {

    lateinit var botonRojo : ImageButton
    lateinit var botonVerde : ImageButton
    lateinit var botonVioleta : ImageButton
    lateinit var botonAzul : ImageButton
    lateinit var nivelText : TextView
    lateinit var botonSeleccionado : ImageButton
    var nivel : Int = 1
    lateinit var secuencia : ArrayList<Int>
    var indiceSecuencia: Int = 0
    var turnoJugador: Boolean = false
    lateinit var sonido: MediaPlayer
    var semaforoSonido : Semaphore = Semaphore(1)
    var semaforoAnimacion : Semaphore = Semaphore(1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modrian_simon)

        nivelText = findViewById(R.id.nivel)

        botonRojo = findViewById(R.id.buttonRojo)
        botonRojo.setClickable(true);
        botonRojo.setLongClickable(false);
        botonRojo.setOnClickListener {    botonRojo.requestFocus(); comprobarBotton(0)    }

        botonVerde = findViewById(R.id.buttonVerde)
        botonVerde.setClickable(true);
        botonVerde.setLongClickable(false);
        botonVerde.setOnClickListener {   botonVerde.requestFocus(); comprobarBotton(1)     }

        botonVioleta = findViewById(R.id.buttonVioleta)
        botonVioleta.setClickable(true);
        botonVioleta.setLongClickable(false);
        botonVioleta.setOnClickListener {  botonVioleta.requestFocus();  comprobarBotton(2)    }

        botonAzul = findViewById(R.id.buttonAzul)
        botonAzul.setClickable(true);
        botonAzul.setLongClickable(false);
        botonAzul.setOnClickListener {  botonAzul.requestFocus();  comprobarBotton(3)    }

        comenzarJuego()

    }

    private fun comprobarBotton(color: Int) {
        println("Boton "+color+" pulsado, turno del jugador: "+ this.turnoJugador)
        if(this.turnoJugador){
            if(color == this.secuencia[this.indiceSecuencia]){
                this.indiceSecuencia++
                if(this.indiceSecuencia == this.secuencia.size){
                    this.nivel++
                    this.nivelText.text = this.nivel.toString()
                    comenzarJuego()
                }
            }else{
                showToast("Juego finalizado, has alcanzado el nivel: "+ this.nivel)
                this.nivel = 1
                this.nivelText.text = this.nivel.toString()
                comenzarJuego()
            }
        }
    }

    private fun comenzarJuego() {
        this.turnoJugador = false
        habilitarBotones(this.turnoJugador)
        println("Botones deshabilitados, turno del jugador: "+this.turnoJugador)
        this.secuencia = generarSecuencia(this.nivel)
        println("Secuencia: "+this.secuencia)
        mostrarSecuencia()
        this.turnoJugador = true
        habilitarBotones(this.turnoJugador)
        println("Botones habilitados, turno del jugador: "+this.turnoJugador)
    }

    private fun mostrarSecuencia() {
        for(color in this.secuencia){
            generarAnimacion(color,1000)
            reproducirSonido(this,color, 1000)
        }

    }

    private fun generarAnimacion(color: Int, tiempo: Long) {
        val alphaBlink = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
        alphaBlink.interpolator = LinearInterpolator()

        GlobalScope.launch(Dispatchers.IO) {

            semaforoAnimacion.acquire()

            when (color) {
                0 -> botonSeleccionado = botonRojo
                1 -> botonSeleccionado = botonVerde
                2 -> botonSeleccionado = botonVioleta
                3 -> botonSeleccionado = botonAzul
            }

            botonSeleccionado.startAnimation(alphaBlink)
            sleep(tiempo)
            botonSeleccionado.clearAnimation()
            semaforoAnimacion.release()
        }

    }

    private fun habilitarBotones(turnoJugador: Boolean) {
        this.botonRojo.isEnabled = turnoJugador
        this.botonVerde.isEnabled = turnoJugador
        this.botonVioleta.isEnabled = turnoJugador
        this.botonAzul.isEnabled = turnoJugador
    }

    private fun generarSecuencia(nivel: Int): ArrayList<Int> {

        val aux = ArrayList<Int>(nivel)

        for(i in 1..nivel){
            aux.add((0..3).random())
        }

        this.indiceSecuencia = 0 // tambien resetea el indice

        return aux

    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun reproducirSonido(context: Context,color: Int, tiempo: Long){

        GlobalScope.launch(Dispatchers.IO) {

            semaforoSonido.acquire()

            when(color){
                0 -> sonido = MediaPlayer.create(context, R.raw.simon_sound_rojo)
                1 -> sonido = MediaPlayer.create(context, R.raw.simon_sound_verde)
                2 -> sonido = MediaPlayer.create(context, R.raw.simon_sound_violeta)
                3 -> sonido = MediaPlayer.create(context, R.raw.simon_sound_azul)
            }

            sonido.start()
            sleep(tiempo)
            sonido.stop()
            sonido.release()

            semaforoSonido.release()
        }

    }
}