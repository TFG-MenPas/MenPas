package com.uma.menpas.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF


object ImageConverter {
    fun getRoundedCornerBitmap(bitmap: Bitmap, pixels: Int): Bitmap {

        //Setting the width and height of the Bitmap that will be returned                        //equal to the original Bitmap that needs round corners.
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

        //Creating canvas with output Bitmap for drawing
        val canvas = Canvas(output)

        //Setting paint and rectangles.
        val color = Color.BLACK
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = pixels.toFloat()
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)

        //SetXfermode applies PorterDuffXfermode to paint.
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}