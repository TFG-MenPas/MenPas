package com.uma.menpas.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.uma.menpas.R

class LoadingDialog(context: Context) : Dialog(context) {

    private val dialog: Dialog = Dialog(context)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.loading_dialog, null)

        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val progressBar = view.findViewById<ProgressBar>(R.id.loading_spinner)
        val color = ContextCompat.getColor(context, R.color.azul_uma)
        progressBar.getIndeterminateDrawable()
            .setColorFilter(color, PorterDuff.Mode.SRC_IN);

    }

    override fun show() {
        dialog.show()
    }

    fun dismiss(seconds: Long) {
        object : CountDownTimer(seconds*1000, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                dialog.dismiss()
            }

        }.start()
    }

}