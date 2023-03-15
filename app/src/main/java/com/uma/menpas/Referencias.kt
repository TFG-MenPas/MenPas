package com.uma.menpas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class Referencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referencias)

        val textLinkReferencia1 = findViewById<TextView>(R.id.textLinkReferencia1)
        val textLinkReferencia2 = findViewById<TextView>(R.id.textLinkReferencia2)

        textLinkReferencia1.movementMethod = LinkMovementMethod.getInstance();
        textLinkReferencia2.movementMethod = LinkMovementMethod.getInstance();
    }
}