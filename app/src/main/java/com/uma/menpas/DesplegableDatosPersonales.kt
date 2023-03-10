package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog

class DesplegableDatosPersonales : AppCompatActivity() {

    lateinit var btnMostrarDesplegable: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desplegable_datos_personales)

        btnMostrarDesplegable = findViewById(R.id.idBtnShowBottomSheet)
        btnMostrarDesplegable.setOnClickListener {
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
    }
    fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}