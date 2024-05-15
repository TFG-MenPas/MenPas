package com.uma.menpas.activities
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R

class EfectoStroop : BaseActivity() {
    lateinit var seekbar: SeekBar
    lateinit var seekbarTiempo: SeekBar
    lateinit var seekbarNumeroPresentaciones: SeekBar
    lateinit var arrayColores : ArrayList<String>
    lateinit var textOpcion: TextView
    lateinit var textTiempo: TextView
    lateinit var textNumeroPresentaciones: TextView
    lateinit var btnFondo : Button
    lateinit var colores : MutableMap<Button,Boolean>
    private lateinit var checked : Drawable
    private lateinit var backChecked : Drawable
    private lateinit var unchecked : Drawable
    private lateinit var backunChecked : Drawable
    lateinit var usuario: String
    private var btnFondoChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_efecto_stroop_configuracion)
        usuario = intent.getStringExtra("usuario") as String
        checked = AppCompatResources.getDrawable(this, R.drawable.icon_checked)!!
        backChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox_checked)!!
        unchecked = AppCompatResources.getDrawable(this, R.drawable.icon_unchecked)!!
        backunChecked = AppCompatResources.getDrawable(this, R.drawable.rounded_checkbox)!!
        botones()
        barraModalidad()
        val botonCerrar = findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
        botonCerrar.setOnClickListener{
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshButtonDrawables()
    }

    private fun barraModalidad(){
        textOpcion = findViewById(R.id.modalidad_stroop)
        textTiempo = findViewById(R.id.tiempo_stroop)
        textNumeroPresentaciones = findViewById(R.id.numero_stroop)

        seekbar = findViewById(R.id.seekbar_stroop)
        seekbarTiempo = findViewById(R.id.seekbar_tiempo_stroop)
        seekbarNumeroPresentaciones = findViewById(R.id.seekbar_numero_stroop)
        seekbar.progressDrawable = null
        seekbar.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
        seekbarTiempo.progressDrawable = null
        seekbarTiempo.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)
        seekbarNumeroPresentaciones.progressDrawable = null
        seekbarNumeroPresentaciones.progressDrawable = AppCompatResources.getDrawable(this, R.drawable.seekbar_multichoice)

        seekbar.progress = 0
        seekbar.max = 2
        val respuestas = arrayOf("Congruente", "Incongruente", "Mixto")
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress) {
                    0 -> textOpcion.text = respuestas[0]
                    1 -> textOpcion.text = respuestas[1]
                    2 -> textOpcion.text = respuestas[2]
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        seekbarTiempo.progress = 0
        seekbarTiempo.max = 8
        val tiempos = arrayOf("1s", "1.5s", "2s", "2.5s", "3s", "3.5s", "4s", "4.5s", "5s")
        seekbarTiempo.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbarTiempo: SeekBar?, progress: Int, fromUser: Boolean) {
                textTiempo.text = "Tiempo de refresco: " + tiempos[progress]
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        seekbarNumeroPresentaciones.progress = 0
        seekbarNumeroPresentaciones.max = 27
        val numeros = arrayOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
            "16","17","18","19","20","25","30","35","40","45","50","75","100",)
        seekbarNumeroPresentaciones.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbarTiempo: SeekBar?, progress: Int, fromUser: Boolean) {
                textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[progress]
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun botones(){
        val botonComenzar: Button = findViewById(R.id.buttonSiguiente)

        btnFondo = findViewById(R.id.button_fondo)
        btnFondoChecked = false

        colores = mutableMapOf()
        colores[findViewById(R.id.button_rojo)] = true
        colores[findViewById(R.id.button_marron)] = true
        colores[findViewById(R.id.button_verde)] = true
        colores[findViewById(R.id.button_gris)] = true
        colores[findViewById(R.id.button_azul)] = true
        colores[findViewById(R.id.button_rosa)] = true
        colores[findViewById(R.id.button_amarillo)] = true
        colores[findViewById(R.id.button_violeta)] = true
        colores[findViewById(R.id.button_negro)] = true
        colores[findViewById(R.id.button_fucsia)] = true
        colores[findViewById(R.id.button_naranja)] = true
        colores[findViewById(R.id.button_blanco)] = true
        colores[findViewById(R.id.button_cyan)] = true

        //Colores
        colores.entries.forEach{
            color ->
            color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
            color.key.background = backChecked
            color.key.setOnClickListener {
                    if (!color.value){
                        color.key.background = backChecked
                        color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                        color.setValue(true)
                    } else {
                        color.key.background = backunChecked
                        color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                        color.setValue(false)
                    }
                }
        }

        //Fondo
        btnFondo.setOnClickListener {
            if (!btnFondoChecked){
                btnFondo.background = backChecked
                btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnFondoChecked = true
            }else{
                btnFondo.background = backunChecked
                btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnFondoChecked = false
            }
        }

        //Proceder
        botonComenzar.setOnClickListener {
            val intent = Intent(this@EfectoStroop, EfectoStroopGrid::class.java)
            arrayColores = ArrayList()
            colores.entries.forEach { color ->
                if (color.value) {
                    val stringColor = color.key.text as String
                    arrayColores.add(stringColor.lowercase())
                }
            }
            if (arrayColores.size >= 2) {
                intent.putExtra("arrayColores", arrayColores)
                intent.putExtra("fondo", btnFondoChecked)
                intent.putExtra("modalidad", textOpcion.text)
                intent.putExtra("numeroPresentaciones", textNumeroPresentaciones.text
                    .substring(26).toInt())
                intent.putExtra("tiempo", convertTiempoExposicion(textTiempo.text.toString()))
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            } else {
                showToast("Debe seleccionar al menos dos colores")
            }
        }
    }

    private fun convertTiempoExposicion(intentTiempo: String): Long {
        var value: Long = 1000
        when(intentTiempo) {
            "Tiempo de refresco: 1.5s" -> value = 1500
            "Tiempo de refresco: 2s" -> value =  2000
            "Tiempo de refresco: 2.5s" -> value =  2500
            "Tiempo de refresco: 3s" -> value =  3000
            "Tiempo de refresco: 3.5s" -> value =  3500
            "Tiempo de refresco: 4s" -> value = 4000
            "Tiempo de refresco: 4.5s" -> value =  4500
            "Tiempo de refresco: 5s" -> value =  5000
        }
        return value
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    private fun refreshButtonDrawables(){
        colores.entries.forEach{
                color ->
            color.key.background = null
            if(color.value){
                color.key.background = backChecked
                color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
            }else{
                color.key.background = backunChecked
                color.key.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
            }
        }
        btnFondo.background = null
        if (btnFondoChecked){
            btnFondo.background = backChecked
            btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
        }else{
            btnFondo.background = backunChecked
            btnFondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
        }
    }
}