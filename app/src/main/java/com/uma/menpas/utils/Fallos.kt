package com.uma.menpas.utils

import android.widget.GridLayout

class Fallos {
    companion object {
        fun calcularFallosPermitidos(numeroTotalCasillas : Int ,valor: String): Int {
            val fallosPermitidos =
                when(valor){
                    "25% Matriz" -> (numeroTotalCasillas * 0.25).toInt()
                    "50% Matriz" -> (numeroTotalCasillas * 0.5).toInt()
                    "75% Matriz" -> (numeroTotalCasillas * 0.75).toInt()
                    "Sin control de fallos" -> Int.MAX_VALUE
                    else -> Int.MAX_VALUE
                }
            return fallosPermitidos
        }
    }
}