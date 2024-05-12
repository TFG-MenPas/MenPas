package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.utils.ObtenerResultados

class DetallesCuestionario : AppCompatActivity() {

    lateinit var barraNavegacionInferior : BottomNavigationView
    lateinit var buttonCerrar : ImageButton
    lateinit var textEntradaNombreCuestionario : TextView
    lateinit var textEntradaCategoria : TextView
    lateinit var textEntradaTipo : TextView
    lateinit var textEntradaFecha : TextView
    lateinit var textEntradaTiempo : TextView
    lateinit var textResultados : TextView
    lateinit var relativeLayout : RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_cuestionario)

        val bundle = intent.extras
        val calculosCuestionario = bundle?.keySet()?.associateWith { bundle.getString(it) }

        val isResultado = intent.getBooleanExtra("isResultado",false)
        val jsonResourceName = intent.getStringExtra("jsonResourceName") ?: ""

        val resultadosObtenidos: Map<String,String> = ObtenerResultados().obtenerResultados(jsonResourceName,
            calculosCuestionario as Map<String, String>
        )

        buttonCerrar = findViewById(R.id.buttonCerrar)
        barraNavegacionInferior = findViewById(R.id.bottom_navigation)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_right)
        BarraNavegacion(barraNavegacionInferior, this)

        buttonCerrar.setOnClickListener {
            if(isResultado) {
                val intent = Intent(this, MenuPrincipal::class.java)
                startActivity(intent)
            }
                else finish()
        }

        relativeLayout = findViewById(R.id.relativeLayoutDetallesCuestionario)
        textEntradaNombreCuestionario = findViewById(R.id.textEntradaNombreCuestionario)
        textEntradaCategoria = findViewById(R.id.textEntradaCategoria)
        textEntradaTipo = findViewById(R.id.textEntradaTipo)
        textEntradaFecha = findViewById(R.id.textEntradaFecha)
        textEntradaTiempo = findViewById(R.id.textEntradaTiempo)
        textResultados = findViewById(R.id.textResultados)

        textEntradaNombreCuestionario.text = resultadosObtenidos["nombreCuestionario"]
        textEntradaFecha.text = calculosCuestionario["Fecha"]?.replace("'", "")
        textEntradaTiempo.text = calculosCuestionario["Tiempo"]?.replace("'", "")

        if (resultadosObtenidos != null) {
            var lastComponentId = textResultados.id
            var contadorClaveId = 1
            var contadorValorId = 500
            for ((clave, valor) in resultadosObtenidos) {
                if(clave != "nombreCuestionario"){
                    val textViewClave = TextView(this)
                    textViewClave.id = contadorClaveId++
                    relativeLayout.addView(textViewClave)
                    setTextViewStyle(textViewClave,"$clave", lastComponentId, "clave")

                    val textViewValor = TextView(this)
                    textViewValor.id = contadorValorId++
                    relativeLayout.addView(textViewValor)
                    setTextViewStyle(textViewValor,"$valor", textViewClave.id, "valor")

                    lastComponentId = textViewValor.id

                }
            }
        }

    }

    private fun setTextViewStyle(textView: TextView, text: String, referenceComponentId: Int, typeComponent: String) {
        val layoutParams : RelativeLayout.LayoutParams =
            textView.getLayoutParams() as RelativeLayout.LayoutParams;
        var typeFont = R.font.poppins_regular
        if(typeComponent == "clave"){
            layoutParams.addRule(RelativeLayout.BELOW,referenceComponentId)
            layoutParams.addRule(RelativeLayout.ALIGN_LEFT, textResultados.id)
            layoutParams.setMargins(50, 0, 0, 0)
        } else if(typeComponent == "valor") {
            layoutParams.addRule(RelativeLayout.ALIGN_LEFT, referenceComponentId)
            layoutParams.addRule(RelativeLayout.BELOW, referenceComponentId)
            layoutParams.setMargins(0, 0, 0, 40)
            layoutParams.marginStart = 30
            layoutParams.marginEnd = 50
            typeFont = R.font.poppins_bold
        }

        textView.text = text.replace("'", "")
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
        val typeface = resources.getFont(typeFont)
        textView.setTypeface(typeface)
        textView.setTextColor(this.getResources().getColor(R.color.black))
    }
}