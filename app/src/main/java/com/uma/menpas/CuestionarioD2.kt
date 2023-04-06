package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CuestionarioD2 : AppCompatActivity() {
    lateinit var recyclerViewD2: RecyclerView
    lateinit var adaptadorD2: AdaptadorD2
    lateinit var listaD2: ArrayList<D2>
    companion object {
        lateinit var myOnClickListener: myOnClickListener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d2)

        myOnClickListener = myOnClickListener(this)
        listaD2 = ArrayList()
        adaptadorD2 = AdaptadorD2(listaD2)
        recyclerViewD2 = findViewById(R.id.recyclerViewD2)
        val numberOfColumns = 8
        recyclerViewD2.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerViewD2.addItemDecoration(DividerItemDecoration(this, 1))

        listaD2.add(D2("''", "d", "''"))
        listaD2.add(D2("'", "d", "'"))
        listaD2.add(D2("", "d", ""))
        listaD2.add(D2("''", "p", "''"))
        listaD2.add(D2("'", "p", "'"))
        listaD2.add(D2("", "p", ""))
        listaD2.add(D2("''", "d", "'"))
        listaD2.add(D2("'", "d", "'"))
        listaD2.add(D2("", "d", ""))
        listaD2.add(D2("''", "p", "''"))
        listaD2.add(D2("'", "p", "'"))
        listaD2.add(D2("", "p", ""))
        listaD2.add(D2("'", "d", "''"))
        listaD2.add(D2("'", "d", "'"))
        listaD2.add(D2("", "d", ""))
        listaD2.add(D2("'", "p", "''"))
        listaD2.add(D2("'", "p", "'"))
        listaD2.add(D2("", "p", ""))
        listaD2.add(D2("''", "d", "''"))
        listaD2.add(D2("'", "d", "'"))
        listaD2.add(D2("", "d", ""))
        listaD2.add(D2("''", "p", "''"))
        listaD2.add(D2("'", "p", "'"))
        listaD2.add(D2("", "p", ""))
        listaD2.add(D2("''", "d", "'"))
        listaD2.add(D2("'", "d", "'"))
        listaD2.add(D2("", "d", ""))
        listaD2.add(D2("''", "p", "''"))
        listaD2.add(D2("'", "p", "'"))
        listaD2.add(D2("", "p", ""))
        listaD2.add(D2("'", "d", "''"))
        listaD2.add(D2("'", "d", "'"))
        listaD2.add(D2("", "d", ""))
        listaD2.add(D2("'", "p", "''"))
        listaD2.add(D2("'", "p", "'"))
        listaD2.add(D2("", "p", ""))

        recyclerViewD2.adapter = adaptadorD2

        //TODO CountDownTimer modifique progrees bar
    }
    class myOnClickListener(cuestionarioD2 : CuestionarioD2) : View.OnClickListener{
        val context = cuestionarioD2
        val azulClaro = context.getColor(R.color.light_blue)
        val azulOscuro = context.getColor(R.color.dark_blue)
        override fun onClick(v: View) {
            val viewHolder : RecyclerView.ViewHolder = context.recyclerViewD2.getChildViewHolder(v)
            val textD2RayaArriba : TextView = viewHolder.itemView.findViewById(R.id.textRayaArriba)
            val textD2Letra : TextView = viewHolder.itemView.findViewById(R.id.textLetra)
            val textD2RayaAbajo : TextView = viewHolder.itemView.findViewById(R.id.textRayaAbajo)
            if(textD2Letra.currentTextColor == azulOscuro){
                textD2RayaArriba.setTextColor(azulClaro)
                textD2Letra.setTextColor(azulClaro)
                textD2RayaAbajo.setTextColor(azulClaro)
            }else{
                textD2RayaArriba.setTextColor(azulOscuro)
                textD2Letra.setTextColor(azulOscuro)
                textD2RayaAbajo.setTextColor(azulOscuro)
            }
        }
    }
}