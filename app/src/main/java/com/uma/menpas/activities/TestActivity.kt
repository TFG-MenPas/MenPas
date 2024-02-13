package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uma.menpas.models.adapters.AdaptadorNuevoCentro
import com.uma.menpas.services.CentroService
import org.ksoap2.serialization.SoapObject

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var response2 = CentroService.getCenterById("0")
        var response = CentroService.listCentersDetailed()






        System.out.println("");
    }

}