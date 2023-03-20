package com.uma.menpas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class CuestionariosRealizados : AppCompatActivity() {
    lateinit var cuestionarioRV: RecyclerView
    lateinit var adaptadorCuestionario: AdaptadorCuestionario
    lateinit var listaCuestionarios: ArrayList<Cuestionario>
    lateinit var barraBusqueda: SearchView

    companion object {
        lateinit var myOnclickListener: MyOnClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionarios_realizados)

        myOnclickListener = MyOnClickListener(this)
        cuestionarioRV = findViewById(R.id.RVCuestionario)
        listaCuestionarios = ArrayList()
        adaptadorCuestionario = AdaptadorCuestionario(listaCuestionarios)


        listaCuestionarios.add(Cuestionario("Ansiedad", "CSAI2: Autoconfianza", "12/02/2023-11:07:32"))
        listaCuestionarios.add(Cuestionario("Atención", "Mondrian: Colores", "12/02/2023-11:07:32"))
        listaCuestionarios.add(Cuestionario("Autoconcepto", "AF5", "10/02/2023-12:08:00"))
        listaCuestionarios.add(Cuestionario("Autorregistro", "Comida", "10/02/2023-12:08:00"))
        listaCuestionarios.add(Cuestionario("Burnout", "BSQ", "10/02/2023-11:00:00"))

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
        override fun onClick(v: View) {
            mostrarDetalles(v)
        }

        @SuppressLint("InflateParams")
        private fun mostrarDetalles(v: View) {
            val viewHolder : RecyclerView.ViewHolder? = context.cuestionarioRV.getChildViewHolder(v)
            val textViewNombreCuestionario : TextView = viewHolder!!.itemView.findViewById<TextView?>(R.id.textNombreCuestionario)
            val textNombreCuestionario: String = textViewNombreCuestionario.text as String

            var cuestionario : Cuestionario? = null
            //Cuando conectemos por base de datos se puede hacer por id
            for (item in context.listaCuestionarios){
                if (item.nombre.lowercase().equals(textNombreCuestionario.lowercase())){
                    cuestionario = item
                }
            }

            if (cuestionario != null){
                Toast.makeText(context, "Nombre: " + cuestionario.nombre + " Tipo: " + cuestionario.tipo, Toast.LENGTH_LONG).show()
            }
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