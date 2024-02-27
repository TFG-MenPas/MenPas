package com.uma.menpas.activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uma.menpas.R
import org.w3c.dom.Text

class EfectoStroopConfiguracion : AppCompatActivity() {
    lateinit var seekbar: SeekBar
    lateinit var seekbarTiempo: SeekBar
    lateinit var seekbarNumeroPresentaciones: SeekBar
    lateinit var arrayColores : ArrayList<String>
    lateinit var textOpcion: TextView
    lateinit var textTiempo: TextView
    lateinit var textNumeroPresentaciones: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_efecto_stroop_configuracion)
        botones()
        barraModalidad()
    }

    private fun barraModalidad(){
        textOpcion = findViewById(R.id.modalidad_stroop)
        textTiempo = findViewById(R.id.tiempo_stroop)
        textNumeroPresentaciones = findViewById(R.id.numero_stroop)

        seekbar = findViewById(R.id.seekbar_stroop)
        seekbarTiempo = findViewById(R.id.seekbar_tiempo_stroop)
        seekbarNumeroPresentaciones = findViewById(R.id.seekbar_numero_stroop)

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
                when(progress) {
                    0 -> textTiempo.text = "Tiempo de refresco: " + tiempos[0]
                    1 -> textTiempo.text = "Tiempo de refresco: " + tiempos[1]
                    2 -> textTiempo.text = "Tiempo de refresco: " + tiempos[2]
                    3 -> textTiempo.text = "Tiempo de refresco: " + tiempos[3]
                    4 -> textTiempo.text = "Tiempo de refresco: " + tiempos[4]
                    5 -> textTiempo.text = "Tiempo de refresco: " + tiempos[5]
                    6 -> textTiempo.text = "Tiempo de refresco: " + tiempos[6]
                    7 -> textTiempo.text = "Tiempo de refresco: " + tiempos[7]
                    8 -> textTiempo.text = "Tiempo de refresco: " + tiempos[8]
                }
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
                when(progress) {
                    0 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[0]
                    1 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[1]
                    2 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[2]
                    3 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[3]
                    4 -> textNumeroPresentaciones.text = "Número de presentaciones: "+  numeros[4]
                    5 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[5]
                    6 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[6]
                    7 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[7]
                    8 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[8]
                    9 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[9]
                    10 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[10]
                    11 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[11]
                    12 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[12]
                    13 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[13]
                    14 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[14]
                    15 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[15]
                    16 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[16]
                    17 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[17]
                    18 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[18]
                    19 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[19]
                    20 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[20]
                    21 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[21]
                    22 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[22]
                    23 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[23]
                    24 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[24]
                    25 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[25]
                    26 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[26]
                    27 -> textNumeroPresentaciones.text = "Número de presentaciones: " + numeros[27]
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun botones(){
        val checked = getDrawable(R.drawable.icon_checked)
        val backChecked = getDrawable(R.drawable.rounded_checkbox_checked)
        val unchecked = getDrawable(R.drawable.icon_unchecked)
        val backunChecked = getDrawable(R.drawable.rounded_checkbox)
        val botonComenzar: Button = findViewById(R.id.buttonSiguiente)

        val btn_fondo : Button = findViewById(R.id.button_fondo)
        var btnChecked = false

        val colores = mutableMapOf<Button, Boolean>()
        colores.put(findViewById(R.id.button_rojo) as Button, true)
        colores.put(findViewById(R.id.button_marron) as Button, true)
        colores.put(findViewById(R.id.button_verde) as Button, true)
        colores.put(findViewById(R.id.button_gris) as Button, true)
        colores.put(findViewById(R.id.button_azul) as Button, true)
        colores.put(findViewById(R.id.button_rosa) as Button, true)
        colores.put(findViewById(R.id.button_amarillo) as Button, true)
        colores.put(findViewById(R.id.button_violeta) as Button, true)
        colores.put(findViewById(R.id.button_negro) as Button, true)
        colores.put(findViewById(R.id.button_fucsia) as Button, true)
        colores.put(findViewById(R.id.button_naranja) as Button, true)
        colores.put(findViewById(R.id.button_blanco) as Button, true)
        colores.put(findViewById(R.id.button_cyan) as Button, true)

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
        btn_fondo.setOnClickListener {
            if (!btnChecked){
                btn_fondo.background = backChecked
                btn_fondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, checked, null)
                btnChecked = true
            }else{
                btn_fondo.background = backunChecked
                btn_fondo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, unchecked, null)
                btnChecked = false
            }
        }

        //Proceder
        botonComenzar.setOnClickListener {
            val intent = Intent(this@EfectoStroopConfiguracion, EfectoStroop::class.java)
            arrayColores = ArrayList()
            colores.entries.forEach { color ->
                if (color.value) {
                    val stringColor = color.key.text as String
                    arrayColores.add(stringColor.lowercase())
                }
            }
            if (arrayColores.size >= 2) {
                intent.putExtra("arrayColores", arrayColores)
                intent.putExtra("fondo", btnChecked)
                intent.putExtra("modalidad", textOpcion.text)
                intent.putExtra("numeroPresentaciones", textNumeroPresentaciones.text)
                intent.putExtra("tiempo", textTiempo.text)
                startActivity(intent)
            } else {
                showToast("Debe seleccionar al menos dos colores")
            }
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}