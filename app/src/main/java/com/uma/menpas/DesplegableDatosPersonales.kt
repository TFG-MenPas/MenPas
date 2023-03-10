package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

            dialog.setContentView(view)
            dialog.show()
        }
    }
}