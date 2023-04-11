package com.uma.menpas.utils

import android.graphics.Color
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


class SnackBarPersonalizada() {

    companion object{
        fun mostrarSnack(layout: RelativeLayout, texto: String, tiempo: Int){
            val snackbarPersonalizada = Snackbar.make(layout, texto, tiempo)
            snackbarPersonalizada.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                .setTextColor(
                    Color.WHITE
                )
            snackbarPersonalizada.show()
        }
    }

}