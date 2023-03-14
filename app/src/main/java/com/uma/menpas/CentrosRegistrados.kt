package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

class CentrosRegistrados : AppCompatActivity() {
    lateinit var centroRV: RecyclerView
    lateinit var adaptadorCentro: AdaptadorCentro
    lateinit var listaCentros: ArrayList<Centro>
    lateinit var barraBusqueda: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centros_registrados)

        centroRV = findViewById(R.id.RVCentros)
        listaCentros = ArrayList()
        adaptadorCentro = AdaptadorCentro(listaCentros)
        centroRV.adapter = adaptadorCentro

        listaCentros.add(Centro("Club Pumas", "España"))
        listaCentros.add(Centro("Remadores", "Estados Unidos"))
        listaCentros.add(Centro("ARCS Sport", "Paraguay"))

        adaptadorCentro.notifyDataSetChanged()

        barraBusqueda = findViewById(R.id.buscarCentro)
        barraBusqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                filter(query)
                return false
            }
        })
    }

    private fun filter(text: String){
        val filteredList: ArrayList<Centro> = ArrayList()

        for (item in listaCentros){
            if (item.nombre.lowercase().contains(text.lowercase())){
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No se han encontrado centros", Toast.LENGTH_SHORT).show()
        }else{
            adaptadorCentro.filterList(filteredList)
        }
    }
}