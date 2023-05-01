package com.uma.menpas.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.postDelayed
import com.uma.menpas.R

class ModrianSimon : AppCompatActivity() {

    lateinit var botonRojo : ImageButton
    lateinit var botonVerde : ImageButton
    lateinit var botonVioleta : ImageButton
    lateinit var botonAzul : ImageButton
    lateinit var nivelText : TextView
    var nivel : Int = 1
    lateinit var secuencia : ArrayList<Int>
    var indiceSecuencia: Int = 0
    var turnoJugador: Boolean = false

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
        for(i in this.secuencia){
            Handler().postDelayed(
                {
                    when (i) {
                        0 -> generarAnimacion(this.botonRojo)
                        1 -> generarAnimacion(this.botonVerde)
                        2 -> generarAnimacion(this.botonVioleta)
                        3 -> generarAnimacion(this.botonAzul)
                    }
                }, (i+1)*1000L

            )
        }

    }

    private fun generarAnimacion(btn: ImageButton) {
        val alphaBlink = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
        alphaBlink.interpolator = LinearInterpolator()
        btn.startAnimation(alphaBlink)

        Handler().postDelayed({btn.clearAnimation()},500L)
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

    private fun flashButton(button: ImageButton) {
        button.setColorFilter(Color.WHITE)
        Handler().postDelayed({
            button.setColorFilter(null)
        }, 500L)
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}