package com.uma.menpas.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

open class BaseActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
            if (ev?.action == MotionEvent.ACTION_DOWN) {
                val view = currentFocus
                if (view is EditText) {
                    val outRect = android.graphics.Rect()
                    view.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        view.clearFocus()
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                    }
                }
            }
            return super.dispatchTouchEvent(ev)
        }
    }
