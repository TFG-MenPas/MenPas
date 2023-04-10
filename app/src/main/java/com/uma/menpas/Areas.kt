package com.uma.menpas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchviewkotlin.PreguntaFAQAdaptador
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ms.square.android.expandabletextview.ExpandableTextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.Normalizer

class Areas : AppCompatActivity() {
    private lateinit var areasAdaptador: AreasAdaptador
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

        areasAdaptador = AreasAdaptador(this, areasList, subareasList)
        val areas_listView = findViewById<ExpandableListView>(R.id.areas_listView)
        areas_listView.setAdapter(areasAdaptador)

        areas_listView.setOnChildClickListener(object : ExpandableListView.OnChildClickListener {
            override fun onChildClick(parent: ExpandableListView?, view: View?, groupPosition: Int, childPosition: Int, id: Long): Boolean {
                val intent = Intent(this@Areas, Documentacion::class.java)
                val area = areasList[groupPosition]
                val subarea = subareasList[areasList[groupPosition]]?.get(childPosition) as String
                val json_resource = Normalizer.normalize(area.toLowerCase(), Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "").replace(" ","_") + "_" + Normalizer.normalize(subarea.toLowerCase().replace(" ", "_"), Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")
                val json_resource_name = json_resource.replace(".", "").replace("-", "")
                intent.putExtra("json_resource_name", json_resource_name)
                intent.putExtra("subarea", subarea)
                startActivity(intent)
                return true
            }
        })

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
        val resId = res.getIdentifier(Areas.JSON_RESOURCE_NAME, Areas.JSON_RESOURCE_TYPE, packageName)

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