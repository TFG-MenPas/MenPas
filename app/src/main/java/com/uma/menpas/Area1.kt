package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Area1 : AppCompatActivity() {

    companion object {
        private const val JSON_RESOURCE_NAME = "areas"
        private const val JSON_RESOURCE_TYPE = "raw"
        private const val BUTTON_TEXT_SIZE = 18f
        private const val AREA = "Ansiedad"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area1)

        val linearLayout = findViewById<LinearLayout>(R.id.botones_areas)
        drawTitle(AREA)
        iterateSections(getSections(AREA), linearLayout)
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

    private fun iterateSections(sections: List<String>, linearLayout: LinearLayout) {
        for (section in sections) {
            val boton = Button(this).apply {
                text = section
                textSize = BUTTON_TEXT_SIZE
                isAllCaps = false
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.dark_blue))
                setTypeface(resources.getFont(R.font.poppins_bold))
                setBackgroundResource(R.drawable.button_white)
            }
            linearLayout.addView(boton)
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
