package com.uma.menpas.utils

import java.util.concurrent.TimeUnit

class Tiempo {

    companion object {
        fun esValido(tiempo: String): Boolean {
            if (tiempo.contains(":")) {
                val minSecArray = tiempo.split(":")
                val min = minSecArray[0]
                val sec = minSecArray[1]
                return min.length == 2 && sec.length == 2 &&
                        ((min[1] < '5') || (min[1] == '5' && sec[0] == '0' && sec[1] == '0')) && sec[0] < '6'
            } else {
                return false
            }
        }

        fun stringToMilis(tiempo: String): Long {
            val minSecArray = tiempo.split(":")
            val min = minSecArray[0]
            val sec = minSecArray[1]
            val minMillis = TimeUnit.MINUTES.toMillis(min.toLong())
            val secMillis = TimeUnit.SECONDS.toMillis(sec.toLong())
            return minMillis + secMillis
        }
    }
}