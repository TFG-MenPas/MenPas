package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.ms.square.android.expandabletextview.ExpandableTextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class FAQ : AppCompatActivity() {

    companion object {
        private const val JSON_RESOURCE_NAME = "faq"
        private const val JSON_RESOURCE_TYPE = "raw"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        val linearLayout = findViewById<LinearLayout>(R.id.expandable_layout_faq)

        val json = getJSON().toDict()

        for (key in json.keys) {
            val cardView = CardView(this)
            val textView = TextView(this)
            textView.setText(key)
            textView.setTypeface(resources.getFont(R.font.poppins_bold))
            textView.setTextColor(resources.getColor(R.color.dark_blue))
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)

            textView.setPadding(0,0,300,0)
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0,0,0,10)
            cardView.layoutParams = params

            cardView.addView(textView)
            linearLayout.addView(cardView)
        }
    }

    private fun getJSON(): JSONObject {
        val jsonString = transformJSONtoString()
        return JSONObject(jsonString)
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