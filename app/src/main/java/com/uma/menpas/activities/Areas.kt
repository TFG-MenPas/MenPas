package com.uma.menpas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.R
import com.uma.menpas.adapters.AdaptadorAreas
import com.uma.menpas.utils.BarraNavegacion
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.Normalizer

class Areas : AppCompatActivity() {
    private lateinit var areasAdaptador: AdaptadorAreas
    private lateinit var areasList: List<String>
    private lateinit var subareasList: HashMap<String,List<String>>

    companion object {
        private const val JSON_RESOURCE_NAME = "areas"
        private const val JSON_RESOURCE_TYPE = "raw"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        showList()

        areasAdaptador = AdaptadorAreas(this, areasList, subareasList)
        val areas_listView = findViewById<ExpandableListView>(R.id.areas_listView)
        areas_listView.setAdapter(areasAdaptador)

        areas_listView.setOnChildClickListener(object : ExpandableListView.OnChildClickListener {
            override fun onChildClick(parent: ExpandableListView?, view: View?, groupPosition: Int, childPosition: Int, id: Long): Boolean {
                val intent = Intent(this@Areas, Documentacion::class.java)
                val area = areasList[groupPosition]
                val subarea = subareasList[areasList[groupPosition]]?.get(childPosition) as String
                val json_resource_name = subarea.lowercase().replace(".", "").replace(" ","_").replace("-", "")
                intent.putExtra("json_resource_name", json_resource_name)
                intent.putExtra("subarea", subarea)
                startActivity(intent)
                return true
            }
        })

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_left)
        BarraNavegacion(barraNavegacionInferior, this)

    }

    private fun showList() {
        val jsonObject = JSONObject(transformJSONtoString()).toDict()
        areasList = ArrayList()
        subareasList = HashMap()

        var i = 0

        for (key in jsonObject.keys) {
            (areasList as ArrayList<String>).add(key)
            var list = jsonObject[key] as List<String>
            val subarea : MutableList<String> = ArrayList()
            for (value in list) {
                subarea.add(value)
                subareasList[areasList[i]] = subarea
            }
            i++
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

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}