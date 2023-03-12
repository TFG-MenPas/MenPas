package com.uma.menpas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog

class DesplegableDatosPersonales : AppCompatActivity() {

    private lateinit var btnMostrarDesplegableDP: Button
    private lateinit var btnMostrarDesplegableFaq: Button
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desplegable_datos_personales)

        btnMostrarDesplegableDP = findViewById(R.id.idBtnShowBottomSheet)
        btnMostrarDesplegableDP.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.desplegable_datos_personales, null)

            val btnModDatos = view.findViewById<RelativeLayout>(R.id.RLModDatos)
            btnModDatos.setOnClickListener {
                showToast("Activity: Modificar Datos")
            }
            val btnConEstadisticas = view.findViewById<RelativeLayout>(R.id.RLConEstadisticas)
            btnConEstadisticas.setOnClickListener {
                showToast("Activity: Consultar Estadisticas")
            }
            val btnRegCentro = view.findViewById<RelativeLayout>(R.id.RLRegCentro)
            btnRegCentro.setOnClickListener {
                showToast("Activity: Registrar Centro")
            }
            val btnCambiarPerfil = view.findViewById<RelativeLayout>(R.id.RLCambioPerfil)
            btnCambiarPerfil.setOnClickListener {
                showToast("Activity: Cambiar Perfil")
            }
            val btnDarBaja = view.findViewById<RelativeLayout>(R.id.RLDarBaja)
            btnDarBaja.setOnClickListener {
                showToast("Activity: Dar de Baja")
            }

            dialog.setContentView(view)
            dialog.show()
        }

        btnMostrarDesplegableFaq = findViewById(R.id.idBtnShowBottomSheet2)
        btnMostrarDesplegableFaq.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.desplegable_faq, null)

            val btnDudasSugerencias = view.findViewById<RelativeLayout>(R.id.btn_dudas_sugerencias)
            btnDudasSugerencias.setOnClickListener {
                showToast("Activity: Dudas y sugerencias")
            }
            val btnAutores = view.findViewById<RelativeLayout>(R.id.btn_autores)
            btnAutores.setOnClickListener {
                showToast("Activity: Autores")
            }
            val btnAreas = view.findViewById<RelativeLayout>(R.id.btn_areas)
            btnAreas.setOnClickListener {
                showToast("Activity: Areas")
            }
            val btnFaq = view.findViewById<RelativeLayout>(R.id.btn_faq)
            btnFaq.setOnClickListener {
                showToast("Activity: FAQ")
            }
            val btnReferencias = view.findViewById<RelativeLayout>(R.id.btn_referencias)
            btnReferencias.setOnClickListener {
                showToast("Activity: Referencias")
            }
            val btnNovedades = view.findViewById<RelativeLayout>(R.id.btn_novedades)
            btnNovedades.setOnClickListener {
                showToast("Activity: Novedades")
            }

            dialog.setContentView(view)
            dialog.show()
        }
    }
    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}