package com.uma.menpas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uma.menpas.controllers.CuestionarioController
import com.uma.menpas.controllers.UsuarioController
import com.uma.menpas.services.CentroService
import com.uma.menpas.services.CuestionarioService

class TestActivity : AppCompatActivity() {
    val cuestionarioController = CuestionarioController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listaCuestionarios = cuestionarioController.listarCuestionarios()
        val cuestionarios = cuestionarioController.getCuestionariosRealizados("miguel15700", "chiqui2010")
        System.out.println("");
    }

}