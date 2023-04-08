package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Spinner
import android.widget.TextView
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

class Areas : AppCompatActivity() {
    private lateinit var areasAdaptador: AreasAdaptador
    private lateinit var areasList: List<String>
    private lateinit var subareasList: HashMap<String,List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        showList()

        areasAdaptador = AreasAdaptador(this, areasList, subareasList)
        val areas_listView = findViewById<ExpandableListView>(R.id.areas_listView)
        areas_listView.setAdapter(areasAdaptador)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BarraNavegacion(barraNavegacionInferior, this)

        /*
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_FAQ)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = PreguntaFAQAdaptador(mList)
        recyclerView.adapter = adapter

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BarraNavegacion(barraNavegacionInferior, this)

         */

    }

    private fun showList() {
        areasList = ArrayList()
        subareasList = HashMap()

        (areasList as ArrayList<String>).add("Area 1")
        (areasList as ArrayList<String>).add("Area 2")

        val subarea1 : MutableList<String> = ArrayList()
        subarea1.add("Subarea1")
        subarea1.add("Subarea2")

        subareasList[areasList[0]] = subarea1
        subareasList[areasList[1]] = subarea1
    }

}