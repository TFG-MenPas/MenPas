package com.uma.menpas
import org.json.JSONObject
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Area1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area1)

        //AQUÍ DEBERÍAMOS PASARLE EL INTENT, NO EL NOMBRE HARDCODEADO
        val area = "Calidad de vida"
        val linearLayout = findViewById<LinearLayout>(R.id.botones_areas)
        this.drawTitle(area)
        this.iterateSections(this.getSections(area), linearLayout)
    }

    private fun drawTitle(area: String) {
        val textArea = findViewById<TextView>(R.id.textArea)
        textArea.text = area
    }

    private fun getSections(area: String): ArrayList<String> {
        val jsonString = this.transformJSONtoString()
        val jsonObject = JSONObject(jsonString)
        return (jsonObject.toDict().get(area)) as ArrayList<String>
    }

    private fun iterateSections(sections: ArrayList<String>, linearLayout: LinearLayout) {
        for (section in sections) {
            val boton = Button(this)
            boton.text = section.toString()
            boton.textSize = 18f
            boton.isAllCaps = false
            boton.gravity = Gravity.CENTER
            boton.setTextColor(getResources().getColor(R.color.dark_blue))
            boton.setTypeface(resources.getFont(R.font.poppins_bold))
            boton.setBackgroundResource((R.drawable.button_white))
            linearLayout.addView(boton)
        }
    }

    private fun transformJSONtoString(): String {
        val res = resources
        val resId = res.getIdentifier("areas", "raw", packageName)

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

    fun JSONObject.toDict(): Map<String, *> = keys().asSequence().associateWith {
        when (val value = this[it])
        {
            is JSONArray ->
            {
                val map = (0 until value.length()).associate { Pair(it.toString(), value[it]) }
                JSONObject(map).toDict().values.toList()
            }
            is JSONObject -> value.toDict()
            JSONObject.NULL -> null
            else            -> value
        }
    }
}