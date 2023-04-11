package com.uma.menpas.models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uma.menpas.models.Cuestionario
import com.uma.menpas.activities.CuestionariosRealizados
import com.uma.menpas.R

class AdaptadorCuestionario(
    // on below line we are passing variables as course list and context
    private var listaCuestionarios: ArrayList<Cuestionario>,
) : RecyclerView.Adapter<AdaptadorCuestionario.CuestionarioViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CuestionarioViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_cuestionario,
            parent, false
        )
        itemView.setOnClickListener(CuestionariosRealizados.myOnclickListener)
        // at last we are returning our view holder
        // class with our item View File.
        return CuestionarioViewHolder(itemView)
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<Cuestionario>) {
        // below line is to add our filtered
        // list in our course array list.
        listaCuestionarios = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: CuestionarioViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.nombre.text = listaCuestionarios.get(position).nombre
        holder.tipo.text = listaCuestionarios.get(position).tipo
        holder.fecha.text = listaCuestionarios.get(position).fecha
        //Imagen--> holder.image.setImageResource(courseList.get(position).courseImg)
    }

    override fun getItemCount(): Int {
        // on below line we are returning
        // our size of our list
        return listaCuestionarios.size
    }

    class CuestionarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombreCuestionario)
        val tipo: TextView = itemView.findViewById(R.id.textTipoCuestionario)
        val fecha: TextView = itemView.findViewById(R.id.textFechaHoraCuestionario)
    }
}