package com.uma.menpas.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import com.uma.menpas.R

class Boton {

    companion object {

        fun Button.cambiarNivelDeAclarado(nivelDeAclarado: Int) {
            // Define la cantidad de aclarado que deseas (0 para transparente, 255 para completamente opaco)

            // Obtiene el fondo actual del botón (puede ser un fondo complejo con varias capas)
            val fondoActual: Drawable = background

            // Crea una nueva capa con el nivel de aclarado
            val aclaradoDrawable = ShapeDrawable(RectShape())
            aclaradoDrawable.paint.color = Color.argb(nivelDeAclarado, 255, 255, 255)

            // Convierte ambos a Drawable para que tengan el mismo tipo
            val aclaradoDrawableConverted: Drawable = aclaradoDrawable

            // Combina la capa de aclarado con el fondo existente mediante LayerDrawable
            val capas = arrayOf<Drawable>(fondoActual, aclaradoDrawableConverted)
            val nuevoFondo = LayerDrawable(capas)

            // Aplica el nuevo fondo al botón
            background = nuevoFondo
        }

        fun Button.deshabilitarBoton() {
            this.cambiarNivelDeAclarado(150)
            this.isEnabled = false
        }

        fun Button.habilitarBoton() {
            this.background = AppCompatResources.getDrawable(this.context, R.drawable.button)
            this.isEnabled = true
        }

    }
}