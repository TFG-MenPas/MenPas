package com.uma.menpas.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Subarea : AppCompatActivity() {
    companion object {
        private const val JSON_RESOURCE_TYPE = "raw"
    }

    private lateinit var subarea : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subarea)
        val intent = intent
        val usuario = intent.getStringExtra("usuario") as String
        subarea = intent.getStringExtra("subarea") as String
        var json_resource_name = intent.getStringExtra("json_resource_name")

        val linearLayout = findViewById<LinearLayout>(R.id.doc_txt_area2)
        this.drawTitle(subarea)
        val json = getJSON(json_resource_name.toString())
        val content = json["instructions"] as JSONObject
        val buttons = json["buttons"] as JSONObject
        drawContent(content, buttons, linearLayout, usuario)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BarraNavegacion(barraNavegacionInferior, this)
    }

    private fun drawTitle(area: String) {
        val textArea = findViewById<TextView>(R.id.textArea2)
        textArea.text = area
        if(area.length >= 10) textArea.textSize = 25F
    }

    private fun getJSON(json_resource_name: String): JSONObject {
        val jsonString = transformJSONtoString(json_resource_name)
        return JSONObject(jsonString)
    }

    private fun drawContent(content: JSONObject, buttons: JSONObject, linearLayout: LinearLayout, usuario: String) {
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        val keys = content.keys()
        while(keys.hasNext()) {
            val key = keys.next()
            val value = content[key]
            val titulo = TextView(this)
            titulo.setTextColor(resources.getColor(R.color.dark_blue))
            titulo.setText(key)
            titulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            titulo.setTypeface(resources.getFont(R.font.poppins_bold))
                titulo.setPadding(40,0,40,0)

            linearLayout.addView(titulo)

            val textView = TextView(this)
            val text = value as String
            textView.setTypeface(resources.getFont(R.font.poppins_light))
            textView.setPadding(40,0,40,70)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            if (text.startsWith("http://")) {
                textView.setText(resources.getString(R.string.click_aqui))
                Linkify.addLinks(textView, Linkify.WEB_URLS)
                textView.movementMethod = LinkMovementMethod.getInstance()
                textView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(text))
                    startActivity(intent)
                }
            } else {
                textView.setTextColor(resources.getColor(R.color.black))
                textView.setText(text)
            }
            linearLayout.addView(textView)
        }

        val btn_keys = buttons.keys()
        while (btn_keys.hasNext()) {
            val btn_key = btn_keys.next()
            val btn_value = buttons[btn_key]
            val button = Button(this)
            button.setBackgroundResource(R.drawable.button)
            button.setText(btn_key)
            button.setTextColor(resources.getColor(R.color.white))
            button.setTypeface(resources.getFont(R.font.poppins_bold))
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            button.gravity = Gravity.CENTER
            button.isAllCaps = false
            button.elevation = 8F
            button.setPadding(50,20,50,20)
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(150,0,150,100)
            button.layoutParams = params
            button.marginBottom
            button.setOnClickListener {
                Toast.makeText(applicationContext, button.text as String, Toast.LENGTH_SHORT).show()
            }
            linearLayout.addView(button)
            button.setOnClickListener{
                val intent = when(btn_value as String){
                    "cuestionario_stroop" -> Intent(this, EfectoStroop::class.java)
                    "cuestionario_modrian_stroop" -> Intent(this, ModrianStroop::class.java)
                    "cuestionario_modrian_colores" -> Intent(this, MondrianColores::class.java)
                    "cuestionario_modrian_fotos" -> Intent(this, ModrianFotos::class.java)
                    "cuestionario_modrian_parejas" -> Intent(this, ModrianParejas::class.java)
                    "cuestionario_modrian_simon" -> Intent(this, ModrianSimon::class.java)
                    "cuestionario_d2_original" -> Intent(this, CuestionarioD2::class.java)
                    "cuestionario_d2_aleatorio" -> Intent(this, CuestionarioD2::class.java)
                    else -> Intent(this, CuestionarioDinamico::class.java)
                }
                intent.putExtra("json_resource_name", btn_value)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            }
        }
    }



    private fun transformJSONtoString(json_resource_name: String): String {
        val res = resources
        val resId = res.getIdentifier(json_resource_name, JSON_RESOURCE_TYPE, packageName)

        val inputStream = res.openRawResource(resId)
        val reader = BufferedReader(InputStreamReader(inputStream))

        val sb = StringBuilder()
        var line: String?

        try {
            while (reader.readLine().also {line = it} != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sb.toString()
    }
}