package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchviewkotlin.AdaptadorPreguntaFAQ
import com.google.android.material.bottomnavigation.BottomNavigationView

class FAQ : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<PreguntaFAQ>()
    private lateinit var adapter: AdaptadorPreguntaFAQ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_FAQ)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = AdaptadorPreguntaFAQ(mList)
        recyclerView.adapter = adapter

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BarraNavegacion(barraNavegacionInferior, this)

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