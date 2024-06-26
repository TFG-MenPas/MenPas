package com.uma.menpas.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.uma.menpas.R
import com.uma.menpas.activities.*
import com.uma.menpas.room.UsuarioDB

class BarraNavegacion(barraNavegacionInferior: BottomNavigationView, applicationContext: Context) {
    init {
        barraNavegacionInferior.menu.getItem(0).isCheckable = false;
        lateinit var intent : Intent
        val layoutInflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialog = BottomSheetDialog(applicationContext)
        barraNavegacionInferior.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_faq -> {
                    it.isCheckable = true
                    val view = layoutInflater.inflate(R.layout.desplegable_faq, null)

                    val btnDudasSugerencias = view.findViewById<RelativeLayout>(R.id.btn_dudas_sugerencias)
                    btnDudasSugerencias.setOnClickListener {
                        intent = Intent(applicationContext, DudasSugerencias::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnAutores = view.findViewById<RelativeLayout>(R.id.btn_autores)
                    btnAutores.setOnClickListener {
                        intent = Intent(applicationContext, Autores::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnAreas = view.findViewById<RelativeLayout>(R.id.btn_areas)
                    btnAreas.setOnClickListener {
                        intent = Intent(applicationContext, Areas::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnFaq = view.findViewById<RelativeLayout>(R.id.btn_faq)
                    btnFaq.setOnClickListener {
                        intent = Intent(applicationContext, FAQ::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnReferencias = view.findViewById<RelativeLayout>(R.id.btn_referencias)
                    btnReferencias.setOnClickListener {
                        intent = Intent(applicationContext, Referencias::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnNovedades = view.findViewById<RelativeLayout>(R.id.btn_novedades)
                    btnNovedades.setOnClickListener {
                        intent = Intent(applicationContext, Novedades::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    dialog.setContentView(view)
                    dialog.show()
                    barraNavegacionInferior.menu.getItem(0).isCheckable = false;

                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    it.isCheckable = true
                    intent = Intent(applicationContext, MenuPrincipal::class.java)
                    applicationContext.startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_user -> {
                    it.isCheckable = true
                    val view = layoutInflater.inflate(R.layout.desplegable_datos_personales, null)

                    val btnModDatos = view.findViewById<RelativeLayout>(R.id.RLModDatos)
                    btnModDatos.setOnClickListener {
                        intent = Intent(applicationContext, DatosPersonales::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnConEstadisticas = view.findViewById<RelativeLayout>(R.id.RLConEstadisticas)
                    btnConEstadisticas.setOnClickListener {
                        intent = Intent(applicationContext, CuestionariosRealizados::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnRegCentro = view.findViewById<RelativeLayout>(R.id.RLRegCentro)
                    btnRegCentro.setOnClickListener {
                        intent = Intent(applicationContext, CentrosRegistrados::class.java)
                        val loadingDialog = LoadingDialog(applicationContext)
                        loadingDialog.show()
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                        loadingDialog.dismiss(3)
                    }
                    val btnCambiarPerfil = view.findViewById<RelativeLayout>(R.id.RLCambioPerfil)
                    btnCambiarPerfil.setOnClickListener {
                        intent = Intent(applicationContext, CambioDePerfil::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnDarBaja = view.findViewById<RelativeLayout>(R.id.RLDarBaja)
                    btnDarBaja.setOnClickListener {
                        intent = Intent(applicationContext, BajaUsuario::class.java)
                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }
                    val btnCerrarSesion = view.findViewById<RelativeLayout>(R.id.RLLogout)
                    btnCerrarSesion.setOnClickListener {
                        intent = Intent(applicationContext, IniciarSesion::class.java)
                        intent.addFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        )
                        val usuarioDB = UsuarioDB.getDatabase(applicationContext)
                        usuarioDB?.UsuarioDAO()?.limpiarUsuario()

                        applicationContext.startActivity(intent)
                        dialog.dismiss()
                    }

                    dialog.setContentView(view)
                    dialog.show()
                    barraNavegacionInferior.menu.getItem(2).isCheckable = false;
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}