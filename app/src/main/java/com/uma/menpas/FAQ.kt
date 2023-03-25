package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchviewkotlin.PreguntaFAQAdaptador
import com.ms.square.android.expandabletextview.ExpandableTextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class FAQ : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<PreguntaFAQ>()
    private lateinit var adapter: PreguntaFAQAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_FAQ)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = PreguntaFAQAdaptador(mList)
        recyclerView.adapter = adapter

    }

    private fun addDataToList() {
        mList.add(
            PreguntaFAQ(
                resources.getString(R.string.incidencia_rejilla_q),
                resources.getString(R.string.incidencia_rejilla_a)
            )
        )
        mList.add(
            PreguntaFAQ(
                resources.getString(R.string.acceso_plataforma_q),
                resources.getString(R.string.acceso_plataforma_a)
            )
        )
        mList.add(
            PreguntaFAQ(
                resources.getString(R.string.admin_restringido_q),
                resources.getString(R.string.admin_restringido_a)
            )
        )
        mList.add(
            PreguntaFAQ(
                resources.getString(R.string.validacion_usuarios_q),
                resources.getString(R.string.validacion_usuarios_a)
            )
        )
        mList.add(
            PreguntaFAQ(
                resources.getString(R.string.compatibilidad_macos_q),
                resources.getString(R.string.compatibilidad_macos_a)
            )
        )
    }
}