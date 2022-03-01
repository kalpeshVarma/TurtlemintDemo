package com.kalpv.turtlemintdemo.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.core.content.res.use
import androidx.fragment.app.Fragment

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone(makeInvisible: Boolean = false) {
    visibility = if (makeInvisible) View.INVISIBLE else View.GONE
}

fun View.showIf(shouldShow: Boolean, isVisible: (Boolean) -> Unit) {
    visibility = if (shouldShow) View.VISIBLE else View.GONE
    isVisible(shouldShow)
}

fun Context.themeColor(
    @AttrRes colorAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(colorAttrId)
    ).use {
        it.getColor(0, Color.BLACK)
    }
}

fun Context.showToast(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(text: String?){
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}
