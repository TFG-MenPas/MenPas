package com.example.searchviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.uma.menpas.PreguntaFAQ
import com.uma.menpas.R

class AdaptadorPreguntaFAQ(private var mList: List<PreguntaFAQ>) :
    RecyclerView.Adapter<AdaptadorPreguntaFAQ.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text_FAQ)
        val subtext: TextView = itemView.findViewById(R.id.subtext_FAQ)
        val constraintLayout_FAQ: ConstraintLayout = itemView.findViewById(R.id.constraintLayout_FAQ)

        fun collapseExpandedView(){
            subtext.visibility = View.GONE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_faq, parent,
            false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {

        val languageData = mList[position]
        holder.text.text = languageData.text
        holder.subtext.text = languageData.subtext

        val isExpandable: Boolean = languageData.isExpandable
        holder.subtext.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout_FAQ.setOnClickListener {
            isAnyItemExpanded(position)
            languageData.isExpandable = !languageData.isExpandable
            notifyItemChanged(position , Unit)
        }

    }

    private fun isAnyItemExpanded(position: Int){
        val temp = mList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position){
            mList[temp].isExpandable = false
            notifyItemChanged(temp , 0)
        }
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {

        if(payloads.isNotEmpty() && payloads[0] == 0){
            holder.collapseExpandedView()
        }else{
            super.onBindViewHolder(holder, position, payloads)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}