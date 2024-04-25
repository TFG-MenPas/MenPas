package com.uma.menpas.activities

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.uma.menpas.R


class ResultadosCuestionario : AppCompatActivity() {

    lateinit var btnMenuPrincipal : Button
    lateinit var textResultadoNombreCuestionario : TextView
    lateinit var relativeLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_cuestionario)

        //val bundle = intent.extras
        //val nombreCuestionario = intent.getStringExtra("nombreCuestionario")
        //val resultadosObtenidos = bundle?.keySet()?.associateWith { bundle.getString(it) }

        val nombreCuestionario = "Resultados Cuestionario SAT"
        val aux: Map<String, String> = mapOf(
            "Claveasasas1" to "Valor1",
            "Clave2" to "Valor2",
            "Clave3" to "Valor3",
            "Clave4" to "Valor4",
            "Clave5" to "Valor5",
            "Clave6" to "Valor6",
            "Clave7" to "Valor7",
            "Clave8" to "Valor8",
            "Clave9" to "Valor9",
            "Clave10" to "Valor10",
            "Clave11" to "Valor11",
        )

        relativeLayout = findViewById(R.id.relativeLayoutResultadosCuestionario)

        btnMenuPrincipal = findViewById(R.id.buttonMenuPrincipal)
        btnMenuPrincipal.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

        textResultadoNombreCuestionario = findViewById(R.id.textResultadoNombreCuestionario)
        textResultadoNombreCuestionario.text = nombreCuestionario

        if (aux != null) {
            var lastComponentId = textResultadoNombreCuestionario.id
            var contadorId = 1
            for ((clave, valor) in aux) {
                if(clave != "nombreCuestionario"){

                    val textViewClave = TextView(this)
                    textViewClave.id = contadorId++
                    relativeLayout.addView(textViewClave)
                    setTextViewStyle(textViewClave,"$clave", lastComponentId, "clave")

                    val textViewValor = TextView(this)
                    relativeLayout.addView(textViewValor)
                    setTextViewStyle(textViewValor,"$valor", textViewClave.id, "valor")

                    lastComponentId = textViewClave.id


                }
            }
        }
    }

    private fun setTextViewStyle(textView: TextView, text: String, referenceComponentId: Int, typeComponent: String) {
        val layoutParams : RelativeLayout.LayoutParams =
            textView.getLayoutParams() as RelativeLayout.LayoutParams;
        if(typeComponent == "clave"){
            layoutParams.addRule(RelativeLayout.BELOW,referenceComponentId)
            layoutParams.setMargins(100, 50, 0, 0)
        } else if(typeComponent == "valor") {
            layoutParams.addRule(RelativeLayout.ALIGN_BASELINE, referenceComponentId)
            layoutParams.addRule(RelativeLayout.RIGHT_OF, referenceComponentId)
            layoutParams.marginStart = 50
        }

        textView.gravity = Gravity.CENTER
        textView.text = text
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
        val typeface = resources.getFont(R.font.poppins_bold)
        textView.setTypeface(typeface)
        textView.setTextColor(this.getResources().getColor(R.color.dark_blue))
    }
}