package com.uma.menpas.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.UsuarioController
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.Normalizer
import java.util.Locale


class Area : BaseActivity() {

    companion object {
        private const val JSON_RESOURCE_NAME = "areas"
        private const val JSON_RESOURCE_TYPE = "raw"
        private const val BUTTON_TEXT_SIZE = 18f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = intent
        val usuario = intent.getStringExtra("usuario") as String
        val usuarioDB = UsuarioController().getUsuario(this)
        val area = intent.getStringExtra("area").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        val linearLayout = findViewById<LinearLayout>(R.id.botones_areas)
        drawTitle(area)
        iterateSections(area, getSections(area), linearLayout, usuario)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if(usuarioDB == null){
            barraNavegacionInferior.visibility = View.GONE
        }else{
            BarraNavegacion(barraNavegacionInferior, this)
        }
    }

    private fun drawTitle(area: String) {
        val textArea = findViewById<TextView>(R.id.textArea)
        textArea.text = area
    }

    private fun getSections(area: String): List<String> {
        val jsonString = transformJSONtoString()
        val jsonObject = JSONObject(jsonString)
        return jsonObject.toDict()[area] as List<String>
    }

    private fun iterateSections(area: String, sections: List<String>, linearLayout: LinearLayout, usuario: String) {
        for (section in sections) {
            val boton = Button(this).apply {
                text = section
                textSize = BUTTON_TEXT_SIZE
                isAllCaps = false
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.dark_blue))
                setTypeface(resources.getFont(R.font.poppins_bold))
                setBackgroundResource(R.drawable.button_white)
                val params = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(100,0,100,0)
                layoutParams = params
            }
            linearLayout.addView(boton)
            boton.setOnClickListener {
                val intent = Intent(this, Subarea::class.java)
                val json_resource_name = section.lowercase().replace(".", "").replace(" ","_").replace("-", "")
                intent.putExtra("json_resource_name", json_resource_name)
                intent.putExtra("subarea", section)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            }
        }
    }

    private fun transformJSONtoString(): String {
        val res = resources
        val resId = res.getIdentifier(JSON_RESOURCE_NAME, JSON_RESOURCE_TYPE, packageName)

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

    private fun JSONObject.toDict(): Map<String, *> = keys().asSequence().associateWith {
        when (val value = this[it])
        {
            is JSONArray ->
            {
                val map = (0 until value.length()).associate { index -> index.toString() to value[index] }
                JSONObject(map).toDict().values.toList()
            }
            is JSONObject -> value.toDict()
            JSONObject.NULL -> null
            else            -> value
        }
    }
}
