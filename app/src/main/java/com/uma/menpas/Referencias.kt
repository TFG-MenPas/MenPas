package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Referencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referencias)

        val textLinkReferencia1 = findViewById<TextView>(R.id.textLinkReferencia1)
        val textLinkReferencia2 = findViewById<TextView>(R.id.textLinkReferencia2)
        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        textLinkReferencia1.movementMethod = LinkMovementMethod.getInstance();
        textLinkReferencia2.movementMethod = LinkMovementMethod.getInstance();

        barraNavegacionInferior.menu.getItem(0).isCheckable = false;
        barraNavegacionInferior.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_faq -> {
                    it.isCheckable = true; // LINEA PROVISIONAL PARA DEVOLVER EL CHECKABLE AL PRIMER ELEMENTO
                    Toast.makeText(applicationContext, "FAQ", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    Toast.makeText(applicationContext, "HOME", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_user -> {
                    Toast.makeText(applicationContext, "USER", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}