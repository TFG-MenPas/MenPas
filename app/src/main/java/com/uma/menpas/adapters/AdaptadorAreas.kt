package com.uma.menpas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.uma.menpas.R

public class AdaptadorAreas internal constructor(private val context: Context,
                                                 private val areasList: List<String>, private val subareasList: HashMap<String,List<String>>)
    : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return areasList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return this.subareasList[this.areasList[p0]]!!.size
    }

    override fun getGroup(p0: Int): Any {
        return areasList[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return this.subareasList[this.areasList[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        val titulo_area = getGroup(p0) as String
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.areas_list, null)
        }
        val areasTV = convertView!!.findViewById<TextView>(R.id.areas_tv)
        areasTV.setText(titulo_area)
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        val titulo_subarea = getChild(p0,p1) as String
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.subareas_list, null)
        }
        val subareasTV = convertView!!.findViewById<TextView>(R.id.subareas_tv)
        subareasTV.setText(titulo_subarea)

        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

}