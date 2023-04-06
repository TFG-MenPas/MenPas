package com.uma.menpas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorD2 (private var listaD2: ArrayList<D2>,
) : RecyclerView.Adapter<AdaptadorD2.D2ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorD2.D2ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_d2, parent, false)
        itemView.setOnClickListener(CuestionarioD2.myOnClickListener)
        return D2ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: AdaptadorD2.D2ViewHolder, position: Int) {
        holder.rayaArriba.text = listaD2[position].rayaArriba
        holder.letra.text = listaD2[position].letra
        holder.rayaAbajo.text = listaD2[position].rayaAbajo
    }

    override fun getItemCount(): Int {
        return listaD2.size
    }

    class D2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rayaArriba: TextView = itemView.findViewById(R.id.textRayaArriba)
        val letra: TextView = itemView.findViewById(R.id.textLetra)
        val rayaAbajo: TextView = itemView.findViewById(R.id.textRayaAbajo)
    }
}


