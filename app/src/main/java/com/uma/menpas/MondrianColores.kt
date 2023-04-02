package com.uma.menpas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources

class MondrianColores : AppCompatActivity() {
    lateinit var botonComenzar : Button
    lateinit var colores : GridLayout
    lateinit var checkBoxColor : CheckBox
    lateinit var arrayColores : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondrian_colores)

        botonComenzar = findViewById(R.id.buttonComenzar)
        botonComenzar.setOnClickListener {
            val intent = Intent(this, MondrianColoresGrid::class.java)
            colores = findViewById(R.id.gridColores)
            arrayColores = ArrayList()
            for (i in 0 until colores.childCount){
                checkBoxColor = colores.getChildAt(i) as CheckBox
                var color : String
                if (checkBoxColor.isChecked){
                    color = checkBoxColor.text as String
                    arrayColores.add(color.lowercase())
                }
            }

            intent.putExtra("arrayColores", arrayColores)

            startActivity(intent)
        }


    }
}