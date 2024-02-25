package com.uma.menpas.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.R
import com.uma.menpas.utils.BarraNavegacion
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Documentacion: AppCompatActivity() {
    companion object {
        private const val JSON_RESOURCE_TYPE = "raw"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subarea)
        val intent = intent
        var subarea = intent.getStringExtra("subarea")
        var json_resource_name = intent.getStringExtra("json_resource_name")

        val linearLayout = findViewById<LinearLayout>(R.id.doc_txt_area2)
        this.drawTitle(subarea.toString())
        val json = getJSON(json_resource_name.toString())
        val content = json["content"] as JSONObject
        drawContent(content, linearLayout)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_left)
        BarraNavegacion(barraNavegacionInferior, this)
    }

    private fun drawTitle(area: String) {
        val textArea = findViewById<TextView>(R.id.textArea2)
        textArea.text = area
    }

    private fun getJSON(json_resource_name: String): JSONObject {
        val jsonString = transformJSONtoString(json_resource_name)
        return JSONObject(jsonString)
    }

    private fun drawContent(content: JSONObject, linearLayout: LinearLayout) {
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