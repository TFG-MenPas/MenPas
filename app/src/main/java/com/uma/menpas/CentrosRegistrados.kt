package com.uma.menpas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.SearchView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CentrosRegistrados : AppCompatActivity() {
    lateinit var centroRV: RecyclerView
    lateinit var adaptadorCentro: AdaptadorCentro
    lateinit var listaCentros: ArrayList<Centro>
    lateinit var barraBusqueda: SearchView
    lateinit var fabAñadirCentro: FloatingActionButton
    companion object {
        lateinit var myOnclickListener: MyOnClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centros_registrados)
        lateinit var intent: Intent

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        BarraNavegacion(barraNavegacionInferior, this)

        myOnclickListener = MyOnClickListener(this)

        fabAñadirCentro = findViewById(R.id.fabAñadirCentro)
        fabAñadirCentro.setOnClickListener {
            intent = Intent(this, RegistroDeCentro::class.java)
            startActivity(intent)
        }

        centroRV = findViewById(R.id.RVCentros)
        listaCentros = ArrayList()
        adaptadorCentro = AdaptadorCentro(listaCentros)


        listaCentros.add(Centro("Club Pumas", "España", "Avenida de la concepcion", 29630, 957632146))
        listaCentros.add(Centro("Remadores", "Estados Unidos", "Calle Carretera", 23894, 950238734))
        listaCentros.add(Centro("ARCS Sport", "Paraguay", "Calle Competa", 88674, 677897453))

        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_right_to_left)
        centroRV.layoutAnimation = controller
        adaptadorCentro.notifyDataSetChanged()
        centroRV.scheduleLayoutAnimation()
        centroRV.adapter = adaptadorCentro

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
            val view = context.layoutInflater.inflate(R.layout.desplegable_info_centro, null)
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

                val textDireccionInfoCentro = view.findViewById<TextView>(R.id.textDireccionInfoCentro)
                textDireccionInfoCentro.text = centro.direccion

                val textCodigoPostalInfoCentro = view.findViewById<TextView>(R.id.textCodigoPostalInfoCentro)
                textCodigoPostalInfoCentro.text = centro.codigoPostal.toString()

                val textTelefonoInfoCentro = view.findViewById<TextView>(R.id.textTelefonoInfoCentro)
                textTelefonoInfoCentro.text = centro.telefono.toString()

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
            centroRV.scheduleLayoutAnimation()
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}