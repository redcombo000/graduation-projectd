package com.example.workcare.common.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object ViewUtils {

    // 키보드 숨기기
    fun hideKeyboard(context: Context, view: View?) {

        if(view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}