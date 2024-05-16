package com.uma.menpas.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ParseUtils {
    companion object {
        fun formattedString(s: String): String {
            return "'$s'"
        }

        fun obtenerFechaActual(): String {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss")
            return currentDateTime.format(formatter)
        }
    }
}