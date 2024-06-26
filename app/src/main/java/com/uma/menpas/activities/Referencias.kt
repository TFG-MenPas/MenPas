package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uma.menpas.utils.BarraNavegacion
import com.uma.menpas.R

class Referencias : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referencias)

        val textLinkReferencia1 = findViewById<TextView>(R.id.textLinkReferencia1)
        val textLinkReferencia4 = findViewById<TextView>(R.id.textLinkReferencia4)
        val textLinkReferencia5 = findViewById<TextView>(R.id.textLinkReferencia5)
        val textLinkReferencia6 = findViewById<TextView>(R.id.textLinkReferencia6)

        textLinkReferencia1.movementMethod = LinkMovementMethod.getInstance();
        textLinkReferencia4.movementMethod = LinkMovementMethod.getInstance();
        textLinkReferencia5.movementMethod = LinkMovementMethod.getInstance();
        textLinkReferencia6.movementMethod = LinkMovementMethod.getInstance();

        val barraNavegacionInferior = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        barraNavegacionInferior.setBackgroundResource(R.drawable.background_bottom_navigation_bar_left)
        BarraNavegacion(barraNavegacionInferior, this)
    }
}