package com.uma.menpas

import android.os.Bundle
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
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Area2 : AppCompatActivity() {
    companion object {
        private const val JSON_RESOURCE_NAME = "ansiedad_csai2"
        private const val JSON_RESOURCE_TYPE = "raw"
        private const val AREA = "CSAI2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area2)

        val linearLayout = findViewById<LinearLayout>(R.id.doc_txt_area2)
        this.drawTitle(AREA)
        val json = getJSON()
        val content = json["content"] as JSONObject
        val buttons = json["buttons"] as JSONArray
        drawContent(content, buttons, linearLayout)
    }

    private fun drawTitle(area: String) {
        val textArea = findViewById<TextView>(R.id.textArea2)
        textArea.text = area
    }

    private fun getJSON(): JSONObject {
        val jsonString = transformJSONtoString()
        return JSONObject(jsonString)
    }

    private fun drawContent(content: JSONObject, buttons: JSONArray, linearLayout: LinearLayout) {
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
            textView.setTextColor(resources.getColor(R.color.black))
            textView.setText(value as String)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            textView.setTypeface(resources.getFont(R.font.poppins_light))
            textView.setPadding(40,0,40,70)
            linearLayout.addView(textView)
        }

        for (i in 0 until buttons.length()) {
            val button = Button(this)
            button.setBackgroundResource(R.drawable.button)
            button.setText(buttons[i] as String)
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