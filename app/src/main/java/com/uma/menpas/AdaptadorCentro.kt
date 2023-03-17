package com.uma.menpas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uma.menpas.CentrosRegistrados.MyOnClickListener

class AdaptadorCentro(
    // on below line we are passing variables as course list and context
    private var listaCentros: ArrayList<Centro>,
) : RecyclerView.Adapter<AdaptadorCentro.CentroViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorCentro.CentroViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_centro,
            parent, false
        )
        itemView.setOnClickListener(CentrosRegistrados.myOnclickListener)
        // at last we are returning our view holder
        // class with our item View File.
        return CentroViewHolder(itemView)
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<Centro>) {
        // below line is to add our filtered
        // list in our course array list.
        listaCentros = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AdaptadorCentro.CentroViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.nombre.text = listaCentros.get(position).nombre
        holder.pais.text = listaCentros.get(position).pais
        //Imagen--> holder.image.setImageResource(courseList.get(position).courseImg)
    }

    override fun getItemCount(): Int {
        // on below line we are returning
        // our size of our list
        return listaCentros.size
    }

    class CentroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombreCentro)
        val pais: TextView = itemView.findViewById(R.id.textPaisCentro)
    }
}
