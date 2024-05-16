package com.uma.menpas.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R
import com.uma.menpas.controllers.CuestionarioController
import com.uma.menpas.models.adapters.AdaptadorCuestionario
import com.uma.menpas.models.Cuestionario
import com.uma.menpas.utils.JsonResourceName

class CuestionariosRealizados : BaseActivity() {
    lateinit var cuestionarioRV: RecyclerView
    lateinit var adaptadorCuestionario: AdaptadorCuestionario
    lateinit var listaCuestionarios: ArrayList<Cuestionario>
    lateinit var barraBusqueda: SearchView
    private val cuestionarioController = CuestionarioController()
    companion object {
        lateinit var myOnclickListener: MyOnClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionarios_realizados)

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_right)
        BarraNavegacion(barraNavegacionInferior, this)

        myOnclickListener = MyOnClickListener(this)
        cuestionarioRV = findViewById(R.id.RVCuestionario)
        listaCuestionarios = ArrayList()

        listaCuestionarios = cuestionarioController.getCuestionariosRealizados(applicationContext) as ArrayList<Cuestionario>

        adaptadorCuestionario = AdaptadorCuestionario(listaCuestionarios)

        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_right_to_left)
        cuestionarioRV.layoutAnimation = controller
        adaptadorCuestionario.notifyDataSetChanged()
        cuestionarioRV.scheduleLayoutAnimation()
        cuestionarioRV.adapter = adaptadorCuestionario

        barraBusqueda = findViewById(R.id.buscarCuestionario)
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
    class MyOnClickListener(cuestionarioRealizado: CuestionariosRealizados) : View.OnClickListener {
        val context = cuestionarioRealizado
        val cuestionarioController = CuestionarioController()
        val jsonResourceName = JsonResourceName()

        override fun onClick(v: View) {
            val viewHolder : RecyclerView.ViewHolder? = context.cuestionarioRV.getChildViewHolder(v)
            val textViewNombreCuestionario : TextView = viewHolder!!.itemView.findViewById<TextView?>(
                R.id.textNombreCuestionario
            )

            val cuestionarioSeleccionado = viewHolder.itemView.tag as Cuestionario
            val resultadosCuestionario = cuestionarioController.getCuestionarioById(cuestionarioSeleccionado.nombre, cuestionarioSeleccionado.id)

            val cuestionarioJsonResourceName = jsonResourceName.getJsonResourceName(cuestionarioSeleccionado.nombre, resultadosCuestionario)


            val bundle = Bundle().apply {
                for ((key, value) in resultadosCuestionario) {
                    putString(key, value)
                }
            }

            val intent = Intent(context, DetallesCuestionario::class.java)
            intent.putExtras(bundle)
            intent.putExtra("jsonResourceName", cuestionarioJsonResourceName)
            intent.putExtra("isResultado",false)
            context.startActivity(intent)
        }
    }
    private fun filter(text: String){
        val filteredList: ArrayList<Cuestionario> = ArrayList()

        for (item in listaCuestionarios){
            if (item.nombre.lowercase().contains(text.lowercase())){
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()){
            showToast("No se han encontrado cuestionarios")
        }else{
            adaptadorCuestionario.filterList(filteredList)
            cuestionarioRV.scheduleLayoutAnimation()
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}