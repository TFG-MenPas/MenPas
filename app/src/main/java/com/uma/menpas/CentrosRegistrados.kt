package com.uma.menpas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.SearchView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class CentrosRegistrados : AppCompatActivity() {
    lateinit var centroRV: RecyclerView
    lateinit var adaptadorCentro: AdaptadorCentro
    lateinit var listaCentros: ArrayList<Centro>
    lateinit var barraBusqueda: SearchView
    companion object {
        lateinit var myOnclickListener: MyOnClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centros_registrados)

        myOnclickListener = MyOnClickListener(this)

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

    class MyOnClickListener(centrosRegistrados: CentrosRegistrados) : View.OnClickListener {
        val context = centrosRegistrados
        override fun onClick(v: View) {
            mostrarDetalles(v)
        }

        @SuppressLint("InflateParams")
        private fun mostrarDetalles(v: View) {
            val viewHolder : RecyclerView.ViewHolder? = context.centroRV.getChildViewHolder(v)
            val textViewNombreCentro : TextView = viewHolder!!.itemView.findViewById<TextView?>(R.id.textNombreCentro)
            val textNombreCentro: String = textViewNombreCentro.text as String

            val dialog = BottomSheetDialog(context)
            val view = context.layoutInflater.inflate(R.layout.informacion_centro, null)
            var centro : Centro? = null
            //Cuando conectemos por base de datos se puede hacer por id
            for (item in context.listaCentros){
                if (item.nombre.lowercase().equals(textNombreCentro.lowercase())){
                     centro = item
                }
            }

            if (centro != null){
                val textNombreInfoCentro = view.findViewById<TextView>(R.id.textNombreInfoCentro)
                textNombreInfoCentro.text = centro.nombre

                val textPaisInfoCentro = view.findViewById<TextView>(R.id.textPaisInfoCentro)
                textPaisInfoCentro.text = centro.pais

                val btnCerrar = view.findViewById<ImageButton>(R.id.imageButtonCerrarDesplegable)
                btnCerrar.setOnClickListener{
                    dialog.dismiss()
                }
                dialog.setCancelable(false)
                dialog.setContentView(view)
                dialog.show()
            }

        }
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

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}